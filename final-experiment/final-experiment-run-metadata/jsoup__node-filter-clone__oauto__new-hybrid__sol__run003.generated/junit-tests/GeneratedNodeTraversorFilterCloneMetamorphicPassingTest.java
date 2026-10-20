import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;

import jsoupmt.NodeTraversorFilterCloneMetamorphicSpec;

public class GeneratedNodeTraversorFilterCloneMetamorphicPassingTest {
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

    @Test
    void SINGLETON_CONTINUE_variation1() {
        exercise("<r></r>", p());
    }

    @Test
    void SINGLETON_SKIP_CHILDREN_variation1() {
        exercise("<r></r>", p(r("d:0", SKIP_CHILDREN)));
    }

    @Test
    void SINGLETON_SKIP_ENTIRELY_variation1() {
        exercise("<r></r>", p(r("d:0", SKIP_ENTIRELY)));
    }

    @Test
    void SINGLETON_HEAD_REMOVE_SENTINEL_variation1() {
        exercise("<r></r>", p(r("d:0", REMOVE)));
    }

    @Test
    void SINGLETON_HEAD_STOP_variation1() {
        exercise("<r></r>", p(r("d:0", STOP)));
    }

    @Test
    void SINGLETON_TAIL_STOP_variation1() {
        exercise("<r></r>", pt(new Rule[0], r("d:0", STOP)));
    }

    @Test
    void SINGLETON_TAIL_REMOVE_SENTINEL_variation1() {
        exercise("<r></r>", pt(new Rule[0], r("d:0", REMOVE)));
    }

    @Test
    void ONE_CHILD_NORMAL_TRAVERSAL_variation1() {
        exercise("<r><c></c></r>", p());
    }

    @Test
    void THREE_SIBLINGS_NORMAL_TRAVERSAL_variation1() {
        exercise("<r><a></a><b></b><c></c></r>", p());
    }

    @Test
    void DEEP_CHAIN_NORMAL_ASCENT_variation1() {
        exercise("<r><a><b><c><d></d></c></b></a></r>", p());
    }

    @Test
    void BRANCHING_DEPTH_FIRST_ORDER_variation1() {
        exercise("<r><a><x></x><y></y></a><b><z></z></b></r>", p());
    }

    @Test
    void ROOT_SKIP_CHILDREN_NONEMPTY_variation1() {
        exercise(
                "<r><a><x></x></a><b></b></r>",
                p(r("d:0", SKIP_CHILDREN)));
    }

    @Test
    void INTERNAL_SKIP_CHILDREN_WITH_SIBLING_variation1() {
        exercise(
                "<r><a id='prune'><x></x><y></y></a><b id='next'></b></r>",
                p(r("prune", SKIP_CHILDREN)));
    }

    @Test
    void LEAF_SKIP_CHILDREN_BOUNDARY_variation1() {
        exercise(
                "<r><a id='leaf'></a><b></b></r>",
                p(r("leaf", SKIP_CHILDREN)));
    }

    @Test
    void ROOT_SKIP_ENTIRELY_NONEMPTY_variation1() {
        exercise(
                "<r><a><x></x></a><b></b></r>",
                p(r("d:0", SKIP_ENTIRELY)));
    }

    @Test
    void INTERNAL_SKIP_ENTIRELY_WITH_SIBLING_variation1() {
        exercise(
                "<r><a id='skip'><x></x></a><b id='next'><y></y></b></r>",
                p(r("skip", SKIP_ENTIRELY)));
    }

    @Test
    void LAST_CHILD_SKIP_ENTIRELY_ASCENT_variation1() {
        exercise(
                "<r><p><a></a><b id='last'></b></p></r>",
                p(r("last", SKIP_ENTIRELY)));
    }

    @Test
    void HEAD_REMOVE_INTERNAL_WITH_NEXT_SIBLING_variation1() {
        exercise(
                "<r><a id='remove'><x><y></y></x></a><b id='next'></b></r>",
                p(r("remove", REMOVE)));
    }

    @Test
    void HEAD_REMOVE_INTERNAL_LAST_CHILD_variation1() {
        exercise(
                "<r><p><a></a><b id='remove'><x></x></b></p></r>",
                p(r("remove", REMOVE)));
    }

    @Test
    void HEAD_REMOVE_MIDDLE_SIBLING_variation1() {
        exercise(
                "<r><a><x></x></a><b id='middle'><y></y></b>"
                        + "<c><z></z></c></r>",
                p(r("middle", REMOVE)));
    }

    @Test
    void HEAD_REMOVE_ALL_DIRECT_CHILDREN_variation1() {
        exercise(
                "<r><a><x></x></a><b></b><c><y></y></c><d></d></r>",
                p(r("d:1", REMOVE)));
    }

    @Test
    void TAIL_REMOVE_LEAF_WITH_NEXT_SIBLING_variation1() {
        exercise(
                "<r><a id='remove'></a><b></b></r>",
                pt(new Rule[0], r("remove", REMOVE)));
    }

    @Test
    void TAIL_REMOVE_LAST_LEAF_variation1() {
        exercise(
                "<r><a></a><b id='remove'></b></r>",
                pt(new Rule[0], r("remove", REMOVE)));
    }

    @Test
    void TAIL_REMOVE_INTERNAL_WITH_NEXT_SIBLING_variation1() {
        exercise(
                "<r><a id='remove'><x><y></y></x></a><b></b></r>",
                pt(new Rule[0], r("remove", REMOVE)));
    }

    @Test
    void TAIL_REMOVE_INTERNAL_LAST_CHILD_variation1() {
        exercise(
                "<r><a></a><b id='remove'><x><y></y></x></b></r>",
                pt(new Rule[0], r("remove", REMOVE)));
    }

    @Test
    void ROOT_TAIL_REMOVE_SENTINEL_NONEMPTY_variation1() {
        exercise(
                "<r><a><x></x></a><b></b></r>",
                pt(new Rule[0], r("d:0", REMOVE)));
    }

    @Test
    void ROOT_HEAD_STOP_NONEMPTY_variation1() {
        exercise(
                "<r><a><x></x></a><b></b></r>",
                p(r("d:0", STOP)));
    }

    @Test
    void FIRST_CHILD_HEAD_STOP_variation1() {
        exercise(
                "<r><a id='stop'><x></x></a><b></b></r>",
                p(r("stop", STOP)));
    }

    @Test
    void DEEP_DESCENDANT_HEAD_STOP_variation1() {
        exercise(
                "<r><a><b><c id='stop'><d></d></c></b></a></r>",
                p(r("stop", STOP)));
    }

    @Test
    void LATER_SIBLING_HEAD_STOP_variation1() {
        exercise(
                "<r><a><x></x></a><b id='stop'><y></y></b><c></c></r>",
                p(r("stop", STOP)));
    }

    @Test
    void LEAF_TAIL_STOP_WITH_NEXT_SIBLING_variation1() {
        exercise(
                "<r><a id='stop'></a><b></b></r>",
                pt(new Rule[0], r("stop", STOP)));
    }

    @Test
    void LAST_LEAF_TAIL_STOP_variation1() {
        exercise(
                "<r><a></a><b id='stop'></b></r>",
                pt(new Rule[0], r("stop", STOP)));
    }

    @Test
    void INTERNAL_TAIL_STOP_AFTER_DESCENDANTS_variation1() {
        exercise(
                "<r><a id='stop'><x><y></y></x></a><b></b></r>",
                pt(new Rule[0], r("stop", STOP)));
    }

    @Test
    void ROOT_TAIL_STOP_AFTER_FULL_TRAVERSAL_variation1() {
        exercise(
                "<r><a><x></x></a><b><y></y></b></r>",
                pt(new Rule[0], r("d:0", STOP)));
    }

    @Test
    void REMOVE_THEN_LATER_HEAD_STOP_variation1() {
        exercise(
                "<r><a id='remove'><x></x></a><b><y></y></b>"
                        + "<c id='stop'><z></z></c><d></d></r>",
                p(r("remove", REMOVE), r("stop", STOP)));
    }

    @Test
    void SKIP_CHILDREN_THEN_VISIT_SIBLING_variation1() {
        exercise(
                "<r><a id='prune'><x><y></y></x></a>"
                        + "<b><m><n></n></m></b></r>",
                p(r("prune", SKIP_CHILDREN)));
    }

    @Test
    void SKIP_ENTIRELY_THEN_REMOVE_SIBLING_variation1() {
        exercise(
                "<r><a id='skip'><x></x></a>"
                        + "<b id='remove'><y></y></b><c></c></r>",
                p(r("skip", SKIP_ENTIRELY), r("remove", REMOVE)));
    }

    @Test
    void REMOVE_FIRST_SIBLING_THEN_VISIT_SHIFTED_SIBLING_variation1() {
        exercise(
                "<r><a id='remove'><x></x></a>"
                        + "<b><y></y></b><c></c></r>",
                p(r("remove", REMOVE)));
    }

    @Test
    void MIXED_HEAD_REMOVAL_ROUTES_variation1() {
        exercise(
                "<r><a><x id='nextRoute'><u></u></x><y></y></a>"
                        + "<b><m></m><n id='upRoute'><v></v></n></b></r>",
                p(r("nextRoute", REMOVE), r("upRoute", REMOVE)));
    }

    @Test
    void MIXED_ZERO_ONE_MANY_CHILD_COUNTS_variation1() {
        exercise(
                "<r><leaf></leaf><one><only></only></one>"
                        + "<many><a></a><b></b><c></c></many></r>",
                p());
    }

    @Test
    void MIXED_NODE_SUBCLASSES_NORMAL_variation1() {
        exercise(
                "<r>alpha<a></a><!--marker--><b>beta</b></r>",
                p());
    }

    @Test
    void REMOVE_ONLY_CHILD_THEN_TAIL_PARENT_variation1() {
        exercise(
                "<r><p id='parent'><c id='remove'></c></p></r>",
                p(r("remove", REMOVE)));
    }

    @Test
    void TAIL_REMOVE_CHILD_AND_PARENT_variation1() {
        exercise(
                "<r><p id='parent'><c id='child'></c></p>"
                        + "<after></after></r>",
                pt(new Rule[0],
                        r("child", REMOVE),
                        r("parent", REMOVE)));
    }

    @Test
    void HEAD_REMOVE_LAST_CHILD_RESULT_RESET_variation1() {
        exercise(
                "<r><p><a></a><b id='remove'></b></p>"
                        + "<s><x></x></s></r>",
                p(r("remove", REMOVE)));
    }

    @Test
    void SKIP_ENTIRELY_LAST_CHILD_RESULT_RESET_variation1() {
        exercise(
                "<r><p><a></a><b id='skip'><x></x></b></p>"
                        + "<s><y></y></s></r>",
                p(r("skip", SKIP_ENTIRELY)));
    }

    @Test
    void TAIL_REMOVE_ALL_DIRECT_CHILDREN_variation1() {
        exercise(
                "<r><a><x></x></a><b></b>"
                        + "<c><y><z></z></y></c></r>",
                pt(new Rule[0], r("d:1", REMOVE)));
    }

    @Test
    void MULTILEVEL_PRUNE_REMOVE_AND_COMPLETE_variation1() {
        exercise(
                "<r><a id='prune'><a1><a2></a2></a1></a>"
                        + "<b id='skip'><b1></b1></b>"
                        + "<c id='remove'><c1></c1></c>"
                        + "<d><d1><d2></d2></d1></d></r>",
                p(r("prune", SKIP_CHILDREN),
                        r("skip", SKIP_ENTIRELY),
                        r("remove", REMOVE)));
    }

    @Test
    void DEEP_REMOVAL_FOLLOWED_BY_ANCESTOR_COMPLETION_variation1() {
        exercise(
                "<r><a><b><c><d id='remove'></d></c></b></a></r>",
                p(r("remove", REMOVE)));
    }

    @Test
    void STOP_PRESERVES_UNVISITED_SUBTREES_variation1() {
        exercise(
                "<r><a><x id='stop'><y></y></x></a>"
                        + "<b><m></m></b><c><n></n></c><d></d></r>",
                p(r("stop", STOP)));
    }

    @Test
    void CLONE_INVARIANT_STRUCTURAL_SELECTION_variation1() {
        exercise(
                "<r id='root'><a id='branch'>"
                        + "<x id='target'><deep></deep></x><y></y></a>"
                        + "<b id='normal'><z></z></b></r>",
                p(r("target", SKIP_CHILDREN)));
    }
}
