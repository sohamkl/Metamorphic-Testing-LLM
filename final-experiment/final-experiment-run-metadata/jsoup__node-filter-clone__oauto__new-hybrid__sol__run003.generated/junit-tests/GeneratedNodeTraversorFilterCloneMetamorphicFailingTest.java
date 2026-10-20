import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;

import jsoupmt.NodeTraversorFilterCloneMetamorphicSpec;

public class GeneratedNodeTraversorFilterCloneMetamorphicFailingTest {
    private static final NodeFilter.FilterResult CONTINUE =
            NodeFilter.FilterResult.CONTINUE;
    private static final NodeFilter.FilterResult SKIP_CHILDREN =
            NodeFilter.FilterResult.SKIP_CHILDREN;
    private static final NodeFilter.FilterResult SKIP_ENTIRELY =
            NodeFilter.FilterResult.SKIP_ENTIRELY;
    private static final NodeFilter.FilterResult REMOVE =
            NodeFilter.FilterResult.REMOVE;
    private static final NodeFilter.FilterResult STOP =
            NodeFilter.FilterResult.STOP;

    private static final class Rule {
        private final String selector;
        private final NodeFilter.FilterResult result;

        private Rule(String selector, NodeFilter.FilterResult result) {
            this.selector = selector;
            this.result = result;
        }

        private boolean matches(Node node, int depth) {
            if (selector.startsWith("d:")) {
                return depth == Integer.parseInt(selector.substring(2));
            }
            return node instanceof Element
                    && selector.equals(((Element) node).id());
        }
    }

    private static final class RulePolicy implements NodeFilter {
        private final Rule[] headRules;
        private final Rule[] tailRules;

        private RulePolicy(Rule[] headRules, Rule[] tailRules) {
            this.headRules = headRules.clone();
            this.tailRules = tailRules.clone();
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return select(headRules, node, depth);
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return select(tailRules, node, depth);
        }

        private FilterResult select(Rule[] rules, Node node, int depth) {
            for (Rule rule : rules) {
                if (rule.matches(node, depth)) {
                    return rule.result;
                }
            }
            return CONTINUE;
        }

        private RulePolicy fresh() {
            return new RulePolicy(headRules, tailRules);
        }
    }

    private static Rule r(String selector, NodeFilter.FilterResult result) {
        return new Rule(selector, result);
    }

    private static RulePolicy p(Rule... headRules) {
        return new RulePolicy(headRules, new Rule[0]);
    }

    private static RulePolicy pt(Rule[] headRules, Rule... tailRules) {
        return new RulePolicy(headRules, tailRules);
    }

    private static Element tree(String html) {
        Element root = Jsoup.parseBodyFragment(html).body().child(0);
        root.remove();
        return root;
    }

    private static Object[] generateFollowUp(NodeFilter filter, Node root) {
        if (filter == null) {
            throw new NullPointerException("filter");
        }
        if (root == null) {
            throw new NullPointerException("root");
        }
        if (root.parentNode() != null) {
            throw new IllegalArgumentException(
                    "The source DOM root must be detached");
        }
        if (!(filter instanceof RulePolicy)) {
            throw new IllegalArgumentException(
                    "Unsupported filter type: " + filter.getClass().getName());
        }

        Node clonedRoot = root.clone();
        if (clonedRoot.parentNode() != null) {
            throw new IllegalStateException(
                    "The cloned DOM root must be detached");
        }
        RulePolicy clonedFilter = ((RulePolicy) filter).fresh();

        try {
            Class<?> rootsType = Class.forName(
                    "jsoupmt.NodeTraversorFilterCloneMetamorphicSpec$ExecutionRoots");
            Constructor<?> constructor =
                    rootsType.getDeclaredConstructor(Node.class, Node.class);
            constructor.setAccessible(true);
            Object executionRoots = constructor.newInstance(root, clonedRoot);

            Field field = NodeTraversorFilterCloneMetamorphicSpec.class
                    .getDeclaredField("EXECUTION_ROOTS");
            field.setAccessible(true);
            @SuppressWarnings("unchecked")
            ThreadLocal<Object> holder = (ThreadLocal<Object>) field.get(null);
            holder.set(executionRoots);
        } catch (ReflectiveOperationException failure) {
            throw new IllegalStateException(
                    "Unable to register metamorphic execution roots", failure);
        }

        return new Object[]{clonedFilter, clonedRoot};
    }

    private static void exercise(String html, RulePolicy sourceFilter) {
        Node sourceRoot = tree(html);
        Object[] followUp = generateFollowUp(sourceFilter, sourceRoot);

        NodeFilter.FilterResult sourceOutput =
                NodeTraversor.filter(sourceFilter, sourceRoot);
        NodeFilter.FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);

        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(
                sourceOutput, followUpOutput);
    }

}
