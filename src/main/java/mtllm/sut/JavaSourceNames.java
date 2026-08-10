package mtllm.sut;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Derives Java type names from source files without loading their classes. */
public final class JavaSourceNames {
    private static final Pattern PACKAGE = Pattern.compile("(?m)^\\s*package\\s+([\\w.]+)\\s*;");

    private JavaSourceNames() {
    }

    public static String qualifiedName(Path sourceFile) {
        if (sourceFile == null) {
            return "";
        }
        String fileName = sourceFile.getFileName().toString();
        String simpleName = fileName.endsWith(".java")
                ? fileName.substring(0, fileName.length() - ".java".length())
                : fileName;
        try {
            Matcher matcher = PACKAGE.matcher(Files.readString(sourceFile, StandardCharsets.UTF_8));
            return matcher.find() ? matcher.group(1) + "." + simpleName : simpleName;
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot read SUT source file: " + sourceFile, e);
        }
    }
}
