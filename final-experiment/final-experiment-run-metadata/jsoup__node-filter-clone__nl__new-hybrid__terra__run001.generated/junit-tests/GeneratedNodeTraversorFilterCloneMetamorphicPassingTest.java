import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GeneratedNodeTraversorFilterCloneMetamorphicPassingTest {
    private static final class SourceCase {
        final Element root;
        final Map<String, NodeFilter.FilterResult> decisions;

        SourceCase(Element root, Map<String, NodeFilter.FilterResult> decisions) {
            this.root = root;
            this.decisions = decisions;
        }
    }

    private static final class FollowUpCase {
        final Element root;
        final Map<String, NodeFilter.FilterResult> decisions;

        FollowUpCase(Element root, Map<String, NodeFilter.FilterResult> decisions) {
            this.root = root;
            this.decisions = decisions;
        }
    }

    private static final class RunResult {
        final NodeFilter.FilterResult result;
        final List<String> callbacks;
        final String outerHtml;

        RunResult(NodeFilter.FilterResult result, List<String> callbacks, String outerHtml) {
            this.result = result;
            this.callbacks = callbacks;
            this.outerHtml = outerHtml;
        }
    }

    private static final class RecordingFilter implements NodeFilter {
        private final Map<String, NodeFilter.FilterResult> decisions;
        private final List<String> callbacks = new ArrayList<>();

        RecordingFilter(Map<String, NodeFilter.FilterResult> decisions) {
            this.decisions = new LinkedHashMap<>(decisions);
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return record("H", node, depth);
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return record("T", node, depth);
        }

        private FilterResult record(String phase, Node node, int depth) {
            String role = ((Element) node).attr("data-role");
            FilterResult result = decisions.getOrDefault(
                phase + ":" + role, FilterResult.CONTINUE);
            callbacks.add(phase + ":" + role + ":" + depth + ":" + result);
            return result;
        }
    }

    private static Element node(String role) {
        Element element = new Element("n");
        element.attr("data-role", role);
        return element;
    }

    private static Element oneChild() {
        Element root = node("root");
        root.appendChild(node("only"));
        return root;
    }

    private static Element depthTwoChain() {
        Element root = node("root");
        Element internal = node("internal");
        internal.appendChild(node("leaf2"));
        root.appendChild(internal);
        return root;
    }

    private static Element branching() {
        Element root = node("root");
        Element first = node("first");
        first.appendChild(node("nested"));
        root.appendChild(first);
        root.appendChild(node("second"));
        return root;
    }

    private static Element twoLeaves() {
        Element root = node("root");
        root.appendChild(node("first"));
        root.appendChild(node("second"));
        return root;
    }

    private static Element threeLeaves() {
        Element root = node("root");
        root.appendChild(node("first"));
        root.appendChild(node("middle"));
        root.appendChild(node("last"));
        return root;
    }

    private static Map<String, NodeFilter.FilterResult> decisions() {
        return new LinkedHashMap<>();
    }

    private static Map<String, NodeFilter.FilterResult> decisions(
        String key, NodeFilter.FilterResult value) {
        Map<String, NodeFilter.FilterResult> map = decisions();
        map.put(key, value);
        return map;
    }

    private static Map<String, NodeFilter.FilterResult> decisions(
        String firstKey, NodeFilter.FilterResult firstValue,
        String secondKey, NodeFilter.FilterResult secondValue) {
        Map<String, NodeFilter.FilterResult> map = decisions(firstKey, firstValue);
        map.put(secondKey, secondValue);
        return map;
    }

    private static FollowUpCase generateFollowUp(SourceCase source) {
        return new FollowUpCase((Element) source.root.clone(), new LinkedHashMap<>(source.decisions));
    }

    private static RunResult run(Element root, Map<String, NodeFilter.FilterResult> policy) {
        RecordingFilter filter = new RecordingFilter(policy);
        NodeFilter.FilterResult result = NodeTraversor.filter(filter, root);
        return new RunResult(result, filter.callbacks, root.outerHtml());
    }

    private static void assertMetamorphicRelation(RunResult source, RunResult followUp) {
        Assertions.assertEquals(source.result, followUp.result);
        Assertions.assertEquals(source.callbacks, followUp.callbacks);
        Assertions.assertEquals(source.outerHtml, followUp.outerHtml);
    }

    private static void assertMetamorphicRelationFor(SourceCase source) {
        Assertions.assertNull(source.root.parentNode());
        FollowUpCase followUp = generateFollowUp(source);
        Assertions.assertNotNull(followUp.root);
        Assertions.assertNull(followUp.root.parentNode());
        Assertions.assertEquals(source.root.outerHtml(), followUp.root.outerHtml());

        RunResult sourceResult = run(source.root, source.decisions);
        RunResult followUpResult = run(followUp.root, followUp.decisions);

        if (!source.decisions.isEmpty()) {
            boolean actionObserved = false;
            for (NodeFilter.FilterResult configured : source.decisions.values()) {
                if (configured != NodeFilter.FilterResult.CONTINUE) {
                    for (String callback : sourceResult.callbacks) {
                        if (callback.endsWith(":" + configured)) {
                            actionObserved = true;
                            break;
                        }
                    }
                }
            }
            Assertions.assertTrue(actionObserved);
        }

        assertMetamorphicRelation(sourceResult, followUpResult);
    }

    @Test
    public void LEAF_ALL_CONTINUE_leafRoot() {
        assertMetamorphicRelationFor(new SourceCase(node("root"), decisions()));
    }

    @Test
    public void ONE_CHILD_ALL_CONTINUE_singleLeafChild() {
        assertMetamorphicRelationFor(new SourceCase(oneChild(), decisions()));
    }

    @Test
    public void DEPTH_TWO_CHAIN_CONTINUE_threeLevels() {
        assertMetamorphicRelationFor(new SourceCase(depthTwoChain(), decisions()));
    }

    @Test
    public void BRANCHING_DEPTH_FIRST_CONTINUE_nestedFirstChild() {
        assertMetamorphicRelationFor(new SourceCase(branching(), decisions()));
    }

    @Test
    public void THREE_LEAF_SIBLINGS_CONTINUE_orderedLeaves() {
        assertMetamorphicRelationFor(new SourceCase(threeLeaves(), decisions()));
    }

    @Test
    public void HEAD_SKIP_CHILDREN_AT_ROOT_nestedRoot() {
        assertMetamorphicRelationFor(new SourceCase(
            depthTwoChain(),
            decisions("H:root", NodeFilter.FilterResult.SKIP_CHILDREN)));
    }

    @Test
    public void HEAD_SKIP_CHILDREN_AT_INTERNAL_NODE_laterSibling() {
        assertMetamorphicRelationFor(new SourceCase(
            branching(),
            decisions("H:first", NodeFilter.FilterResult.SKIP_CHILDREN)));
    }

    @Test
    public void HEAD_SKIP_CHILDREN_AT_LEAF_onlyChild() {
        assertMetamorphicRelationFor(new SourceCase(
            oneChild(),
            decisions("H:only", NodeFilter.FilterResult.SKIP_CHILDREN)));
    }

    @Test
    public void HEAD_SKIP_ENTIRELY_AT_ROOT_withDescendants() {
        assertMetamorphicRelationFor(new SourceCase(
            depthTwoChain(),
            decisions("H:root", NodeFilter.FilterResult.SKIP_ENTIRELY)));
    }

    @Test
    public void HEAD_SKIP_ENTIRELY_FIRST_INTERNAL_SIBLING_subtreePruned() {
        assertMetamorphicRelationFor(new SourceCase(
            branching(),
            decisions("H:first", NodeFilter.FilterResult.SKIP_ENTIRELY)));
    }

    @Test
    public void HEAD_SKIP_ENTIRELY_LAST_LEAF_secondSibling() {
        assertMetamorphicRelationFor(new SourceCase(
            twoLeaves(),
            decisions("H:second", NodeFilter.FilterResult.SKIP_ENTIRELY)));
    }

    @Test
    public void HEAD_REMOVE_ROOT_detachedSubtree() {
        assertMetamorphicRelationFor(new SourceCase(
            depthTwoChain(),
            decisions("H:root", NodeFilter.FilterResult.REMOVE)));
    }

    @Test
    public void HEAD_REMOVE_FIRST_LEAF_WITH_NEXT_SIBLING_twoLeaves() {
        assertMetamorphicRelationFor(new SourceCase(
            twoLeaves(),
            decisions("H:first", NodeFilter.FilterResult.REMOVE)));
    }

    @Test
    public void HEAD_REMOVE_MIDDLE_LEAF_threeLeaves() {
        assertMetamorphicRelationFor(new SourceCase(
            threeLeaves(),
            decisions("H:middle", NodeFilter.FilterResult.REMOVE)));
    }

    @Test
    public void HEAD_REMOVE_LAST_ONLY_CHILD_ascendToParent() {
        assertMetamorphicRelationFor(new SourceCase(
            oneChild(),
            decisions("H:only", NodeFilter.FilterResult.REMOVE)));
    }

    @Test
    public void HEAD_REMOVE_INTERNAL_SUBTREE_laterSiblingRemains() {
        assertMetamorphicRelationFor(new SourceCase(
            branching(),
            decisions("H:first", NodeFilter.FilterResult.REMOVE)));
    }

    @Test
    public void HEAD_STOP_AT_ROOT_immediateStop() {
        assertMetamorphicRelationFor(new SourceCase(
            oneChild(),
            decisions("H:root", NodeFilter.FilterResult.STOP)));
    }

    @Test
    public void HEAD_STOP_AT_DEPTH_TWO_LEAF_chainLeaf() {
        assertMetamorphicRelationFor(new SourceCase(
            depthTwoChain(),
            decisions("H:leaf2", NodeFilter.FilterResult.STOP)));
    }

    @Test
    public void HEAD_STOP_AT_LATER_SIBLING_afterFirstSubtree() {
        assertMetamorphicRelationFor(new SourceCase(
            branching(),
            decisions("H:second", NodeFilter.FilterResult.STOP)));
    }

    @Test
    public void TAIL_STOP_AT_LEAF_onlyChild() {
        assertMetamorphicRelationFor(new SourceCase(
            oneChild(),
            decisions("T:only", NodeFilter.FilterResult.STOP)));
    }

    @Test
    public void TAIL_STOP_AT_INTERNAL_NODE_afterNestedLeaf() {
        assertMetamorphicRelationFor(new SourceCase(
            depthTwoChain(),
            decisions("T:internal", NodeFilter.FilterResult.STOP)));
    }

    @Test
    public void TAIL_STOP_AT_ROOT_afterCompleteTraversal() {
        assertMetamorphicRelationFor(new SourceCase(
            branching(),
            decisions("T:root", NodeFilter.FilterResult.STOP)));
    }

    @Test
    public void TAIL_REMOVE_FIRST_LEAF_WITH_NEXT_SIBLING_twoLeaves() {
        assertMetamorphicRelationFor(new SourceCase(
            twoLeaves(),
            decisions("T:first", NodeFilter.FilterResult.REMOVE)));
    }

    @Test
    public void TAIL_REMOVE_LAST_ONLY_CHILD_ascendAfterRemoval() {
        assertMetamorphicRelationFor(new SourceCase(
            oneChild(),
            decisions("T:only", NodeFilter.FilterResult.REMOVE)));
    }

    @Test
    public void TAIL_REMOVE_INTERNAL_SUBTREE_afterDescendants() {
        assertMetamorphicRelationFor(new SourceCase(
            branching(),
            decisions("T:first", NodeFilter.FilterResult.REMOVE)));
    }

    @Test
    public void TAIL_REMOVE_ROOT_terminalResult() {
        assertMetamorphicRelationFor(new SourceCase(
            depthTwoChain(),
            decisions("T:root", NodeFilter.FilterResult.REMOVE)));
    }

    @Test
    public void TAIL_SKIP_CHILDREN_AT_ROOT_afterDescent() {
        assertMetamorphicRelationFor(new SourceCase(
            branching(),
            decisions("T:root", NodeFilter.FilterResult.SKIP_CHILDREN)));
    }

    @Test
    public void TAIL_SKIP_ENTIRELY_AT_ROOT_afterDescent() {
        assertMetamorphicRelationFor(new SourceCase(
            depthTwoChain(),
            decisions("T:root", NodeFilter.FilterResult.SKIP_ENTIRELY)));
    }

    @Test
    public void TAIL_SKIP_ENTIRELY_FIRST_CHILD_secondChildContinues() {
        assertMetamorphicRelationFor(new SourceCase(
            twoLeaves(),
            decisions("T:first", NodeFilter.FilterResult.SKIP_ENTIRELY)));
    }

    @Test
    public void MIXED_SKIP_REMOVE_CONTINUE_threeDistinctChildren() {
        Element root = node("root");
        Element skipped = node("first");
        skipped.appendChild(node("hidden"));
        root.appendChild(skipped);
        root.appendChild(node("middle"));
        root.appendChild(node("last"));

        assertMetamorphicRelationFor(new SourceCase(
            root,
            decisions(
                "H:first", NodeFilter.FilterResult.SKIP_CHILDREN,
                "H:middle", NodeFilter.FilterResult.REMOVE)));
    }
}
