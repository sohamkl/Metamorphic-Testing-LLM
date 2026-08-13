package mtllm.sut;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;
import mtllm.config.PromptConfig;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Discovers the bounded graph of public classes that can construct a target input.
 *
 * <p>Reflection follows constructors, factory methods, arrays, and generic type arguments.
 * ClassGraph supplies concrete implementations for interface and abstract nodes. JavaParser adds
 * source-level evidence for factories/builders that may not be obvious from the target signature.
 * The result is intentionally bounded so a broad application classpath cannot make Randoop scan
 * every class in the project.</p>
 */
public final class ConstructionGraphDiscoverer {

    static final int MAX_DEPTH = 5;
    static final int MAX_CLASSES = 96;
    static final int MAX_IMPLEMENTATIONS_PER_TYPE = 12;
    static final int MAX_SOURCE_FILES = 1500;

    private ConstructionGraphDiscoverer() {
    }

    public static Result discover(PromptConfig config, Class<?> rootType) {
        LinkedHashSet<String> classes = new LinkedHashSet<>();
        List<String> evidence = new ArrayList<>();
        Deque<Node> pending = new ArrayDeque<>();
        pending.add(new Node(rootType, 0, "target input"));

        try (ScanResult scan = new ClassGraph()
                .enableClassInfo()
                .ignoreClassVisibility()
                .scan()) {
            while (!pending.isEmpty() && classes.size() < MAX_CLASSES) {
                Node node = pending.removeFirst();
                visit(node, classes, evidence, pending, scan);
            }
        } catch (RuntimeException scanFailure) {
            evidence.add("ClassGraph scan unavailable: " + scanFailure.getMessage());
            while (!pending.isEmpty() && classes.size() < MAX_CLASSES) {
                visit(pending.removeFirst(), classes, evidence, pending, null);
            }
        }

        addSourceEvidence(config, rootType, classes, evidence);
        return new Result(Set.copyOf(classes), List.copyOf(evidence));
    }

    private static void visit(
            Node node,
            LinkedHashSet<String> classes,
            List<String> evidence,
            Deque<Node> pending,
            ScanResult scan) {
        Class<?> type = node.type;
        if (type == null || node.depth > MAX_DEPTH || terminal(type) || classes.size() >= MAX_CLASSES) {
            return;
        }
        if (type.isArray()) {
            pending.addFirst(new Node(type.getComponentType(), node.depth + 1, type.getTypeName()));
            return;
        }
        if (!classes.add(type.getName())) {
            return;
        }
        evidence.add(type.getName() + " <- " + node.reason);

        addKnownCollectionImplementation(type, node.depth, pending);
        if (!type.getName().startsWith("java.")
                && (type.isInterface() || Modifier.isAbstract(type.getModifiers()))
                && scan != null) {
            concreteImplementations(scan, type).stream()
                    .limit(MAX_IMPLEMENTATIONS_PER_TYPE)
                    .forEach(implementation -> pending.addLast(
                            new Node(implementation, node.depth + 1, "implementation of " + type.getName())));
        }

        try {
            for (Constructor<?> constructor : type.getConstructors()) {
                addTypes(constructor.getGenericParameterTypes(), node.depth + 1,
                        "constructor of " + type.getName(), pending);
            }
            for (Method method : type.getMethods()) {
                if (!Modifier.isPublic(method.getModifiers()) || !Modifier.isStatic(method.getModifiers())) {
                    continue;
                }
                if (!type.isAssignableFrom(method.getReturnType()) || method.getReturnType() == void.class) {
                    continue;
                }
                classes.add(method.getDeclaringClass().getName());
                addTypes(method.getGenericParameterTypes(), node.depth + 1,
                        "factory " + method.getDeclaringClass().getName() + "." + method.getName(), pending);
            }
        } catch (LinkageError unavailableOptionalDependency) {
            classes.remove(type.getName());
            evidence.add(type.getName() + " skipped: optional API dependency unavailable ("
                    + unavailableOptionalDependency.getClass().getSimpleName() + ")");
        }
    }

    private static List<Class<?>> concreteImplementations(ScanResult scan, Class<?> type) {
        ClassInfoList infos = type.isInterface()
                ? scan.getClassesImplementing(type.getName())
                : scan.getSubclasses(type.getName());
        return infos.stream()
                .filter(ConstructionGraphDiscoverer::usableImplementation)
                .sorted(Comparator.comparing(ClassInfo::getName))
                .<Class<?>>map(info -> load(info, type.getClassLoader()))
                .filter(candidate -> candidate != null)
                .toList();
    }

    private static boolean usableImplementation(ClassInfo info) {
        String name = info.getName();
        return info.isPublic()
                && !info.isAbstract()
                && !info.isInterface()
                && !info.isAnnotation()
                && !name.startsWith("java.")
                && !name.startsWith("javax.")
                && !name.startsWith("jdk.")
                && !name.startsWith("sun.");
    }

    private static Class<?> load(ClassInfo info, ClassLoader preferredLoader) {
        try {
            return Class.forName(info.getName(), false, preferredLoader);
        } catch (ClassNotFoundException | LinkageError ignored) {
            try {
                return info.loadClass(false);
            } catch (RuntimeException | LinkageError unavailable) {
                return null;
            }
        }
    }

    private static void addTypes(Type[] types, int depth, String reason, Deque<Node> pending) {
        for (Type type : types) {
            addType(type, depth, reason, pending);
        }
    }

    private static void addType(Type type, int depth, String reason, Deque<Node> pending) {
        if (type instanceof Class<?> raw) {
            pending.addLast(new Node(raw, depth, reason));
            return;
        }
        if (type instanceof ParameterizedType parameterized) {
            addType(parameterized.getRawType(), depth, reason, pending);
            for (Type argument : parameterized.getActualTypeArguments()) {
                addType(argument, depth + 1, "generic argument of " + parameterized.getTypeName(), pending);
            }
        }
    }

    private static void addKnownCollectionImplementation(Class<?> type, int depth, Deque<Node> pending) {
        if (type == java.util.List.class || type == java.util.Collection.class || type == Iterable.class) {
            pending.addLast(new Node(java.util.ArrayList.class, depth + 1, "default collection implementation"));
        } else if (type == java.util.Set.class) {
            pending.addLast(new Node(java.util.LinkedHashSet.class, depth + 1, "default set implementation"));
        } else if (type == java.util.Map.class) {
            pending.addLast(new Node(java.util.LinkedHashMap.class, depth + 1, "default map implementation"));
        }
    }

    private static boolean terminal(Class<?> type) {
        return type.isPrimitive()
                || type.isEnum()
                || type == String.class
                || type == Class.class
                || type.getName().startsWith("java.time.")
                || type.getName().startsWith("java.math.")
                || Number.class.isAssignableFrom(type)
                || type == Boolean.class
                || type == Character.class;
    }

    private static void addSourceEvidence(
            PromptConfig config,
            Class<?> targetType,
            LinkedHashSet<String> classes,
            List<String> evidence) {
        Path root = config.projectRoot();
        if (root == null || !Files.isDirectory(root)) {
            return;
        }
        Set<String> targetNames = new LinkedHashSet<>();
        targetNames.add(targetType.getSimpleName());
        for (String name : classes) {
            if (name.startsWith("java.")) {
                continue;
            }
            int separator = Math.max(name.lastIndexOf('.'), name.lastIndexOf('$'));
            targetNames.add(separator >= 0 ? name.substring(separator + 1) : name);
        }

        try (Stream<Path> files = Files.walk(root)) {
            for (Path file : files.filter(path -> path.toString().endsWith(".java"))
                    .limit(MAX_SOURCE_FILES)
                    .toList()) {
                if (classes.size() >= MAX_CLASSES) {
                    break;
                }
                inspectSource(file, targetNames, classes, evidence);
            }
        } catch (IOException ignored) {
            evidence.add("JavaParser source scan unavailable under " + root);
        }
    }

    private static void inspectSource(
            Path file,
            Set<String> targetNames,
            LinkedHashSet<String> classes,
            List<String> evidence) {
        try {
            CompilationUnit unit = StaticJavaParser.parse(file);
            String packageName = unit.getPackageDeclaration()
                    .map(declaration -> declaration.getNameAsString() + ".")
                    .orElse("");
            for (ClassOrInterfaceDeclaration declaration : unit.findAll(ClassOrInterfaceDeclaration.class)) {
                String className = packageName + declaration.getNameAsString();
                boolean implementsTarget = Stream.concat(
                                declaration.getExtendedTypes().stream(), declaration.getImplementedTypes().stream())
                        .map(type -> simple(type.getNameAsString()))
                        .anyMatch(targetNames::contains);
                boolean hasFactory = declaration.getMethods().stream()
                        .filter(MethodDeclaration::isPublic)
                        .filter(MethodDeclaration::isStatic)
                        .map(method -> simple(method.getTypeAsString()))
                        .anyMatch(targetNames::contains);
                boolean hasBuilder = declaration.getMethods().stream()
                        .filter(MethodDeclaration::isPublic)
                        .anyMatch(method -> method.getNameAsString().toLowerCase(Locale.ROOT).equals("build")
                                && targetNames.contains(simple(method.getTypeAsString())));
                if ((implementsTarget || hasFactory || hasBuilder) && classes.add(className)) {
                    evidence.add(className + " <- JavaParser "
                            + (implementsTarget ? "implementation" : hasBuilder ? "builder" : "factory")
                            + " in " + file);
                }
            }
        } catch (RuntimeException | IOException ignored) {
            // Source analysis is best-effort; reflection remains authoritative for loadable APIs.
        }
    }

    private static String simple(String typeName) {
        String noGenerics = typeName.replaceAll("<.*>", "").replace("[]", "");
        int dot = Math.max(noGenerics.lastIndexOf('.'), noGenerics.lastIndexOf('$'));
        return dot >= 0 ? noGenerics.substring(dot + 1) : noGenerics;
    }

    private record Node(Class<?> type, int depth, String reason) {
    }

    public record Result(Set<String> classNames, List<String> evidence) {
    }
}
