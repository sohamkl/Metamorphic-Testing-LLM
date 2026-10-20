import org.junit.jupiter.api.Test;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;
import jsoupmt.NodeTraversorFilterCloneMetamorphicSpec;

public class GeneratedNodeTraversorFilterCloneMetamorphicPassingTest {
    private static final String NONE = "-";

    private static final class Policy implements NodeFilter {
        private final String headRules;
        private final String tailRules;

        private Policy(String headRules, String tailRules) {
            this.headRules = headRules;
            this.tailRules = tailRules;
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return lookup(headRules, node.nodeName());
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return lookup(tailRules, node.nodeName());
        }

        private FilterResult lookup(String rules, String nodeName) {
            if (NONE.equals(rules)) {
                return FilterResult.CONTINUE;
            }
            for (String rule : rules.split(";")) {
                int separator = rule.indexOf('=');
                if (separator > 0 && rule.substring(0, separator).equals(nodeName)) {
                    return FilterResult.valueOf(rule.substring(separator + 1));
                }
            }
            return FilterResult.CONTINUE;
        }
    }

    private static Element element(String tag) {
        return new Element(tag);
    }

    private static Element child(Element parent, String tag) {
        Element child = element(tag);
        parent.appendChild(child);
        return child;
    }

    private static Document document() {
        return new Document("");
    }

    private static void exercise(NodeFilter filter, Node root) {
        Object[] followUp =
                NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput =
                NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter(
                        (NodeFilter) followUp[0],
                        (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(
                sourceOutput, followUpOutput);
    }

    @Test
    void SINGLETON_CONTINUE_variation1_elementLeaf() {
        Node root = element("root");
        exercise(new Policy(NONE, NONE), root);
    }

    @Test
    void SINGLETON_SKIP_CHILDREN_variation1_textRoot() {
        Node root = new TextNode("singleton");
        exercise(new Policy("#text=SKIP_CHILDREN", NONE), root);
    }

    @Test
    void SINGLETON_SKIP_ENTIRELY_variation1_elementLeaf() {
        Node root = element("root");
        exercise(new Policy("root=SKIP_ENTIRELY", NONE), root);
    }

    @Test
    void ROOT_HEAD_REMOVE_variation1_documentWithBranches() {
        Document root = document();
        Element first = child(root, "first");
        Element deep1 = child(first, "deep1");
        Element deep2 = child(deep1, "deep2");
        child(deep2, "deep3");
        child(root, "middle");
        child(root, "last");
        exercise(new Policy("#document=REMOVE", NONE), root);
    }

    @Test
    void ROOT_HEAD_STOP_variation1_rootPreemptsMixedChildren() {
        Element root = element("root");
        root.appendChild(new TextNode("text"));
        child(root, "candidate");
        exercise(new Policy("root=STOP", NONE), root);
    }

    @Test
    void ROOT_TAIL_STOP_variation1_documentRoot() {
        Document root = document();
        child(root, "leaf");
        exercise(new Policy(NONE, "#document=STOP"), root);
    }

    @Test
    void ROOT_TAIL_REMOVE_variation1_twoLevelTree() {
        Element root = element("root");
        Element branch = child(root, "branch");
        child(branch, "leaf");
        child(root, "sibling");
        exercise(new Policy(NONE, "root=REMOVE"), root);
    }

    @Test
    void ROOT_WITH_CHILDREN_SKIP_CHILDREN_variation1_documentRoot() {
        Document root = document();
        Element first = child(root, "first");
        Element nested = child(first, "nested");
        child(nested, "deep");
        child(root, "second");
        child(root, "third");
        exercise(new Policy("#document=SKIP_CHILDREN", NONE), root);
    }

    @Test
    void ONE_CHILD_FULL_TRAVERSAL_variation1_elementAndLeaf() {
        Element root = element("root");
        child(root, "only");
        exercise(new Policy(NONE, NONE), root);
    }

    @Test
    void DEEP_CHAIN_FULL_TRAVERSAL_variation1_documentChain() {
        Document root = document();
        Element one = child(root, "one");
        Element two = child(one, "two");
        Element three = child(two, "three");
        child(three, "four");
        exercise(new Policy(NONE, NONE), root);
    }

    @Test
    void THREE_SIBLINGS_FULL_TRAVERSAL_variation1_fourLeaves() {
        Element root = element("root");
        child(root, "one");
        child(root, "two");
        child(root, "three");
        child(root, "four");
        exercise(new Policy(NONE, NONE), root);
    }

    @Test
    void BRANCHED_FULL_TRAVERSAL_variation1_documentBranches() {
        Document root = document();
        Element left = child(root, "left");
        child(left, "leftleaf");
        Element right = child(root, "right");
        child(right, "rightleaf");
        child(root, "finalleaf");
        exercise(new Policy(NONE, NONE), root);
    }

    @Test
    void STOP_AT_FIRST_CHILD_HEAD_variation1_twoChildren() {
        Element root = element("root");
        child(root, "first");
        child(root, "later");
        exercise(new Policy("first=STOP", NONE), root);
    }

    @Test
    void STOP_AT_DEEPEST_HEAD_variation1_documentDepthFour() {
        Document root = document();
        Element one = child(root, "one");
        Element two = child(one, "two");
        Element three = child(two, "three");
        child(three, "stopnode");
        exercise(new Policy("stopnode=STOP", NONE), root);
    }

    @Test
    void STOP_AT_LATER_SIBLING_HEAD_variation1_thirdSibling() {
        Element root = element("root");
        child(root, "first");
        child(root, "second");
        child(root, "stopthird");
        child(root, "fourth");
        exercise(new Policy("stopthird=STOP", NONE), root);
    }

    @Test
    void STOP_AT_LEAF_TAIL_variation1_documentSiblingList() {
        Document root = document();
        child(root, "first");
        child(root, "stopleaf");
        child(root, "later");
        exercise(new Policy(NONE, "stopleaf=STOP"), root);
    }

    @Test
    void STOP_AT_INTERNAL_TAIL_variation1_completedSubtree() {
        Element root = element("root");
        Element internal = child(root, "internal");
        child(internal, "leaf");
        child(root, "later");
        exercise(new Policy(NONE, "internal=STOP"), root);
    }

    @Test
    void SKIP_CHILDREN_INTERNAL_WITH_SIBLING_variation1_documentBranch() {
        Document root = document();
        Element skipped = child(root, "skipped");
        child(skipped, "hidden");
        child(root, "later");
        exercise(new Policy("skipped=SKIP_CHILDREN", NONE), root);
    }

    @Test
    void SKIP_CHILDREN_LAST_CHILD_ASCENT_variation1_nestedLastChild() {
        Element root = element("root");
        Element parent = child(root, "parent");
        child(parent, "first");
        Element skipped = child(parent, "skipped");
        child(skipped, "hidden");
        exercise(new Policy("skipped=SKIP_CHILDREN", NONE), root);
    }

    @Test
    void SKIP_CHILDREN_LEAF_variation1_documentLeaf() {
        Document root = document();
        child(root, "skipleaf");
        child(root, "later");
        exercise(new Policy("skipleaf=SKIP_CHILDREN", NONE), root);
    }

    @Test
    void SKIP_CHILDREN_MULTIPLE_BRANCHES_variation1_twoPrunedBranches() {
        Element root = element("root");
        Element first = child(root, "firstbranch");
        child(first, "hiddenone");
        Element second = child(root, "secondbranch");
        child(second, "hiddentwo");
        exercise(
                new Policy(
                        "firstbranch=SKIP_CHILDREN;secondbranch=SKIP_CHILDREN",
                        NONE),
                root);
    }

    @Test
    void SKIP_ENTIRELY_INTERNAL_WITH_SIBLING_variation1_documentBranch() {
        Document root = document();
        Element skipped = child(root, "skipped");
        child(skipped, "hidden");
        child(root, "later");
        exercise(new Policy("skipped=SKIP_ENTIRELY", NONE), root);
    }

    @Test
    void SKIP_ENTIRELY_LAST_CHILD_ASCENT_variation1_lastNestedChild() {
        Element root = element("root");
        Element parent = child(root, "parent");
        child(parent, "first");
        Element skipped = child(parent, "skipped");
        child(skipped, "hidden");
        exercise(new Policy("skipped=SKIP_ENTIRELY", NONE), root);
    }

    @Test
    void SKIP_ENTIRELY_LEAF_variation1_documentLeaf() {
        Document root = document();
        child(root, "skipleaf");
        child(root, "later");
        exercise(new Policy("skipleaf=SKIP_ENTIRELY", NONE), root);
    }

    @Test
    void MIXED_SKIP_RESULTS_variation1_separateBranches() {
        Element root = element("root");
        Element skipChildren = child(root, "skipchildren");
        child(skipChildren, "hiddenone");
        Element skipEntirely = child(root, "skipentirely");
        child(skipEntirely, "hiddentwo");
        child(root, "continued");
        exercise(
                new Policy(
                        "skipchildren=SKIP_CHILDREN;skipentirely=SKIP_ENTIRELY",
                        NONE),
                root);
    }

    @Test
    void HEAD_REMOVE_FIRST_SUBTREE_variation1_documentBranches() {
        Document root = document();
        Element removed = child(root, "removed");
        child(removed, "hidden");
        child(root, "survivor");
        exercise(new Policy("removed=REMOVE", NONE), root);
    }

    @Test
    void HEAD_REMOVE_MIDDLE_SIBLING_variation1_threeChildren() {
        Element root = element("root");
        child(root, "first");
        Element middle = child(root, "middle");
        child(middle, "hidden");
        child(root, "last");
        exercise(new Policy("middle=REMOVE", NONE), root);
    }

    @Test
    void HEAD_REMOVE_LAST_CHILD_variation1_documentLastSubtree() {
        Document root = document();
        child(root, "first");
        child(root, "middle");
        Element last = child(root, "last");
        child(last, "hidden");
        exercise(new Policy("last=REMOVE", NONE), root);
    }

    @Test
    void HEAD_REMOVE_ONLY_CHILD_variation1_parentBecomesLeaf() {
        Element root = element("root");
        Element parent = child(root, "parent");
        Element only = child(parent, "only");
        child(only, "hidden");
        exercise(new Policy("only=REMOVE", NONE), root);
    }

    @Test
    void HEAD_REMOVE_CONSECUTIVE_SIBLINGS_variation1_documentAdjacentNodes() {
        Document root = document();
        child(root, "removea");
        child(root, "removeb");
        child(root, "survivor");
        exercise(new Policy("removea=REMOVE;removeb=REMOVE", NONE), root);
    }

    @Test
    void HEAD_REMOVE_DEEP_SUBTREE_variation1_laterAncestorBranch() {
        Element root = element("root");
        Element branch = child(root, "branch");
        Element parent = child(branch, "parent");
        Element removed = child(parent, "removed");
        child(removed, "hidden");
        child(root, "laterbranch");
        exercise(new Policy("removed=REMOVE", NONE), root);
    }

    @Test
    void TAIL_REMOVE_LEAF_WITH_SIBLING_variation1_documentChildren() {
        Document root = document();
        child(root, "removedleaf");
        child(root, "survivor");
        child(root, "last");
        exercise(new Policy(NONE, "removedleaf=REMOVE"), root);
    }

    @Test
    void TAIL_REMOVE_LAST_LEAF_variation1_lastElementChild() {
        Element root = element("root");
        child(root, "first");
        child(root, "removedleaf");
        exercise(new Policy(NONE, "removedleaf=REMOVE"), root);
    }

    @Test
    void TAIL_REMOVE_INTERNAL_WITH_SIBLING_variation1_documentSubtree() {
        Document root = document();
        Element removed = child(root, "removed");
        child(removed, "leaf");
        child(root, "survivor");
        exercise(new Policy(NONE, "removed=REMOVE"), root);
    }

    @Test
    void TAIL_REMOVE_INTERNAL_LAST_CHILD_variation1_completedLastBranch() {
        Element root = element("root");
        child(root, "first");
        Element removed = child(root, "removed");
        child(removed, "leaf");
        exercise(new Policy(NONE, "removed=REMOVE"), root);
    }

    @Test
    void CASCADING_TAIL_REMOVALS_variation1_nestedDocumentSubtree() {
        Document root = document();
        Element outer = child(root, "outer");
        Element inner = child(outer, "inner");
        child(inner, "leaf");
        child(root, "survivor");
        exercise(new Policy(NONE, "inner=REMOVE;outer=REMOVE"), root);
    }

    @Test
    void REMOVE_THEN_STOP_AT_SIBLING_variation1_headDecisions() {
        Element root = element("root");
        Element removed = child(root, "removed");
        child(removed, "hidden");
        child(root, "stopnode");
        child(root, "later");
        exercise(new Policy("removed=REMOVE;stopnode=STOP", NONE), root);
    }

    @Test
    void SKIP_THEN_STOP_AT_LATER_BRANCH_variation1_documentBranches() {
        Document root = document();
        Element skipped = child(root, "skipped");
        child(skipped, "hidden");
        Element later = child(root, "later");
        child(later, "stopnode");
        exercise(
                new Policy("skipped=SKIP_CHILDREN;stopnode=STOP", NONE),
                root);
    }

    @Test
    void TAIL_REMOVE_THEN_STOP_AT_SIBLING_variation1_removalPreserved() {
        Element root = element("root");
        child(root, "removed");
        child(root, "stopnode");
        child(root, "later");
        exercise(new Policy("stopnode=STOP", "removed=REMOVE"), root);
    }

    @Test
    void ROOT_TAIL_SKIP_ENTIRELY_RESULT_variation1_documentMixedTree() {
        Document root = document();
        Element branch = child(root, "branch");
        branch.appendChild(new TextNode("text"));
        child(branch, "leaf");
        child(root, "sibling");
        exercise(new Policy(NONE, "#document=SKIP_ENTIRELY"), root);
    }

    @Test
    void ROOT_TAIL_SKIP_CHILDREN_RESULT_variation1_elementTree() {
        Element root = element("root");
        Element branch = child(root, "branch");
        child(branch, "leaf");
        exercise(new Policy(NONE, "root=SKIP_CHILDREN"), root);
    }

    @Test
    void CHILD_TAIL_SKIP_ENTIRELY_WITH_SIBLING_variation1_documentChildren() {
        Document root = document();
        child(root, "selected");
        child(root, "later");
        exercise(new Policy(NONE, "selected=SKIP_ENTIRELY"), root);
    }

    @Test
    void CHILD_TAIL_SKIP_CHILDREN_WITH_SIBLING_variation1_elementChildren() {
        Element root = element("root");
        child(root, "selected");
        child(root, "later");
        exercise(new Policy(NONE, "selected=SKIP_CHILDREN"), root);
    }

    @Test
    void LAST_CHILD_TAIL_RESULT_RESET_variation1_nestedDocumentBranch() {
        Document root = document();
        Element parent = child(root, "parent");
        child(parent, "first");
        child(parent, "selected");
        child(root, "later");
        exercise(new Policy(NONE, "selected=SKIP_ENTIRELY"), root);
    }

    @Test
    void MIXED_NONSTOP_FILTER_RESULTS_variation1_fourBehaviors() {
        Element root = element("root");
        Element continued = child(root, "continued");
        child(continued, "continuedleaf");
        Element skipChildren = child(root, "skipchildren");
        child(skipChildren, "hiddenone");
        Element skipEntirely = child(root, "skipentirely");
        child(skipEntirely, "hiddentwo");
        Element removed = child(root, "removed");
        child(removed, "hiddenthree");
        exercise(
                new Policy(
                        "skipchildren=SKIP_CHILDREN;"
                                + "skipentirely=SKIP_ENTIRELY;"
                                + "removed=REMOVE",
                        NONE),
                root);
    }

    @Test
    void NON_ELEMENT_SINGLETON_ROOT_variation1_textNode() {
        Node root = new TextNode("detached text");
        exercise(new Policy(NONE, NONE), root);
    }

    @Test
    void MIXED_NODE_TYPE_TREE_variation1_elementsAndText() {
        Element root = element("root");
        root.appendChild(new TextNode("leading"));
        Element branch = child(root, "branch");
        branch.appendChild(new TextNode("nested"));
        child(root, "leaf");
        exercise(new Policy(NONE, NONE), root);
    }

    @Test
    void ROOT_STOP_PRECEDES_CANDIDATE_ACTIONS_variation1_documentSentinels() {
        Document root = document();
        Element removable = child(root, "removable");
        child(removable, "hidden");
        Element skippable = child(root, "skippable");
        child(skippable, "hiddenleaf");
        child(root, "later");
        exercise(
                new Policy(
                        "#document=STOP;"
                                + "removable=REMOVE;"
                                + "skippable=SKIP_CHILDREN",
                        NONE),
                root);
    }

    @Test
    void SKIP_ENTIRELY_SHIELDS_DESCENDANT_SENTINELS_variation1_branchShield() {
        Element root = element("root");
        Element shield = child(root, "shield");
        child(shield, "stopsentinel");
        child(shield, "removesentinel");
        child(root, "later");
        exercise(
                new Policy(
                        "shield=SKIP_ENTIRELY;"
                                + "stopsentinel=STOP;"
                                + "removesentinel=REMOVE",
                        NONE),
                root);
    }

    @Test
    void HEAD_REMOVE_SHIELDS_DESCENDANT_STOP_variation1_documentSubtree() {
        Document root = document();
        Element removed = child(root, "removed");
        Element nested = child(removed, "nested");
        child(nested, "stopsentinel");
        child(root, "survivor");
        exercise(
                new Policy("removed=REMOVE;stopsentinel=STOP", NONE),
                root);
    }
}
