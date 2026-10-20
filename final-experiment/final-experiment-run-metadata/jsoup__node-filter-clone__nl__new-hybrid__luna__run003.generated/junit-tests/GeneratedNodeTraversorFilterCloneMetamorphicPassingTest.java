import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GeneratedNodeTraversorFilterCloneMetamorphicPassingTest {
    private static final NodeFilter.FilterResult C = NodeFilter.FilterResult.CONTINUE;
    private static final NodeFilter.FilterResult SC = NodeFilter.FilterResult.SKIP_CHILDREN;
    private static final NodeFilter.FilterResult SE = NodeFilter.FilterResult.SKIP_ENTIRELY;
    private static final NodeFilter.FilterResult R = NodeFilter.FilterResult.REMOVE;
    private static final NodeFilter.FilterResult S = NodeFilter.FilterResult.STOP;

    private static final class Fixture {
        final Node root;
        final Map<String, NodeFilter.FilterResult> heads = new LinkedHashMap<>();
        final Map<String, NodeFilter.FilterResult> tails = new LinkedHashMap<>();

        Fixture(Node root) {
            this.root = root;
        }

        Fixture head(String key, NodeFilter.FilterResult result) {
            heads.put(key, result);
            return this;
        }

        Fixture tail(String key, NodeFilter.FilterResult result) {
            tails.put(key, result);
            return this;
        }
    }

    private static final class Observation {
        final NodeFilter.FilterResult result;
        final List<String> events;
        final String html;

        Observation(NodeFilter.FilterResult result, List<String> events, String html) {
            this.result = result;
            this.events = events;
            this.html = html;
        }
    }

    private static Element e(String id) {
        return new Element("div").attr("id", id);
    }

    private static String key(Node node) {
        if (node instanceof Element) {
            String id = ((Element) node).attr("id");
            if (!id.isEmpty()) {
                return id;
            }
        }
        if (node instanceof TextNode) {
            return "#text";
        }
        if (node instanceof Comment) {
            return "#comment";
        }
        return node.nodeName();
    }

    private static NodeFilter callback(Fixture fixture, List<String> events) {
        return new NodeFilter() {
            @Override
            public FilterResult head(Node node, int depth) {
                String key = key(node);
                FilterResult result = fixture.heads.getOrDefault(key, C);
                events.add("H:" + key + ":" + depth + ":" + result);
                return result;
            }

            @Override
            public FilterResult tail(Node node, int depth) {
                String key = key(node);
                FilterResult result = fixture.tails.getOrDefault(key, C);
                events.add("T:" + key + ":" + depth + ":" + result);
                return result;
            }
        };
    }

    private static Observation run(Fixture fixture, Node root) {
        List<String> events = new ArrayList<>();
        NodeFilter.FilterResult result = NodeTraversor.filter(callback(fixture, events), root);
        return new Observation(result, events, root.outerHtml());
    }

    private static void assertMetamorphicRelationFor(Fixture fixture) {
        Node source = fixture.root;
        Node followUp = source.clone();
        Observation sourceOutput = run(fixture, source);
        Observation followUpOutput = run(fixture, followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelation(
            Observation sourceOutput, Observation followUpOutput) {
        Assertions.assertEquals(sourceOutput.result, followUpOutput.result);
        Assertions.assertEquals(sourceOutput.events, followUpOutput.events);
        Assertions.assertEquals(sourceOutput.html, followUpOutput.html);
    }

    private static Fixture fixture(int number) {
        Element root;
        Element a;
        Element b;
        Element c;
        Element d;

        switch (number) {
            case 1:
                return new Fixture(e("root"));

            case 2:
                return new Fixture(e("root")).head("root", SC);

            case 3:
                return new Fixture(new Comment("root")).head("#comment", SE);

            case 4:
                return new Fixture(new TextNode("root")).head("#text", R);

            case 5:
                root = e("root");
                a = e("a");
                root.appendChild(a);
                return new Fixture(root).head("root", S);

            case 6:
                root = e("root");
                a = e("a");
                root.appendChild(a);
                return new Fixture(root);

            case 7:
                root = e("root");
                a = e("a");
                b = e("b");
                root.appendChild(a).appendChild(b);
                return new Fixture(root).head("a", SC).tail("a", R);

            case 8:
                root = e("root");
                a = e("a");
                b = e("b");
                c = e("c");
                root.appendChild(a).appendChild(b).appendChild(c);
                return new Fixture(root).head("b", SE);

            case 9:
                root = e("root");
                a = e("a");
                b = e("b");
                c = e("c");
                root.appendChild(a);
                a.appendChild(b);
                b.appendChild(c);
                return new Fixture(root);

            case 10:
                root = e("root");
                a = e("a");
                b = e("b");
                root.appendChild(a).appendChild(b);
                a.appendChild(e("deep"));
                return new Fixture(root).head("a", SC);

            case 11:
                root = e("root");
                a = e("a");
                b = e("b");
                root.appendChild(a).appendChild(b);
                a.appendChild(e("deep"));
                return new Fixture(root).head("a", SE);

            case 12:
                root = e("root");
                a = e("a");
                b = e("b");
                root.appendChild(a).appendChild(b);
                return new Fixture(root).head("a", R);

            case 13:
                root = e("root");
                a = e("a");
                b = e("b");
                root.appendChild(a).appendChild(b);
                return new Fixture(root).head("b", R);

            case 14:
                root = e("root");
                a = e("a");
                b = e("b");
                root.appendChild(a).appendChild(b);
                a.appendChild(e("deep"));
                return new Fixture(root).head("a", R);

            case 15:
                root = e("root");
                a = e("a");
                root.appendChild(a);
                a.appendChild(e("deep"));
                return new Fixture(root).head("a", R);

            case 16:
                root = e("root");
                a = e("a");
                b = e("b");
                c = e("c");
                root.appendChild(a).appendChild(b).appendChild(c);
                return new Fixture(root).head("b", R);

            case 17:
                root = e("root");
                a = e("a");
                root.appendChild(a);
                a.appendChild(e("deep"));
                return new Fixture(root).head("a", S);

            case 18:
                root = e("root");
                a = e("a");
                b = e("b");
                root.appendChild(a).appendChild(b);
                return new Fixture(root).tail("a", S);

            case 19:
                root = e("root");
                a = e("a");
                root.appendChild(a);
                return new Fixture(root).tail("root", S);

            case 20:
                root = e("root");
                a = e("a");
                b = e("b");
                root.appendChild(a).appendChild(b);
                a.appendChild(e("deep"));
                b.appendChild(e("nested"));
                return new Fixture(root).head("root", SC);

            case 21:
                root = e("root");
                root.appendChild(e("a"));
                return new Fixture(root).head("root", SE);

            case 22:
                root = e("root");
                root.appendChild(e("a"));
                return new Fixture(root).head("root", R);

            case 23:
                root = e("root");
                a = e("a");
                b = e("b");
                c = e("c");
                d = e("d");
                root.appendChild(a).appendChild(b).appendChild(c).appendChild(d);
                a.appendChild(e("deepA"));
                return new Fixture(root)
                        .head("a", SC)
                        .head("b", SE)
                        .head("c", R)
                        .tail("d", S);

            case 24:
                root = e("root");
                a = e("a");
                b = e("b");
                c = e("c");
                d = e("d");
                root.appendChild(a).appendChild(b).appendChild(c).appendChild(d);
                a.appendChild(e("deepA"));
                b.appendChild(e("deepB"));
                return new Fixture(root)
                        .head("a", SC)
                        .head("b", SE)
                        .head("c", R)
                        .tail("d", S);

            case 25:
                return new Fixture(new TextNode("text")).head("#text", S);

            case 26:
                return new Fixture(new Comment("comment")).head("#comment", S);

            case 27:
                root = e("root");
                a = e("a");
                root.appendChild(new TextNode("text"));
                root.appendChild(new Comment("comment"));
                root.appendChild(a);
                a.appendChild(new TextNode("nested"));
                return new Fixture(root);

            case 28:
                return new Fixture(e("root"));

            case 29:
                root = e("root");
                a = e("a");
                b = e("b");
                c = e("c");
                d = e("d");
                root.appendChild(a).appendChild(b).appendChild(c).appendChild(d);
                a.appendChild(e("a1"));
                a.appendChild(e("a2"));
                b.appendChild(e("b1"));
                c.appendChild(e("c1"));
                return new Fixture(root);

            case 30:
                root = e("root");
                a = e("a");
                b = e("b");
                root.appendChild(a).appendChild(b);
                b.appendChild(e("deep"));
                return new Fixture(root).head("b", SC);

            case 31:
                root = e("root");
                a = e("a");
                root.appendChild(a);
                return new Fixture(root).head("a", R);

            case 32:
                root = e("root");
                a = e("a");
                b = e("b");
                root.appendChild(a).appendChild(b);
                return new Fixture(root).head("a", R);

            default:
                throw new IllegalArgumentException("unknown fixture");
        }
    }

    @Test
    public void ROOT_LEAF_CONTINUE_1() {
        assertMetamorphicRelationFor(fixture(1));
    }

    @Test
    public void ROOT_LEAF_SKIP_CHILDREN_1() {
        assertMetamorphicRelationFor(fixture(2));
    }

    @Test
    public void ROOT_LEAF_SKIP_ENTIRE_1() {
        assertMetamorphicRelationFor(fixture(3));
    }

    @Test
    public void ROOT_LEAF_REMOVE_1() {
        assertMetamorphicRelationFor(fixture(4));
    }

    @Test
    public void ROOT_LEAF_STOP_1() {
        assertMetamorphicRelationFor(fixture(5));
    }

    @Test
    public void ROOT_WITH_ONE_CHILD_CONTINUE_1() {
        assertMetamorphicRelationFor(fixture(6));
    }

    @Test
    public void ROOT_WITH_TWO_LEAF_SIBLINGS_1() {
        assertMetamorphicRelationFor(fixture(7));
    }

    @Test
    public void ROOT_WITH_THREE_SIBLINGS_1() {
        assertMetamorphicRelationFor(fixture(8));
    }

    @Test
    public void SINGLE_CHILD_CHAIN_DEPTH_THREE_1() {
        assertMetamorphicRelationFor(fixture(9));
    }

    @Test
    public void INTERNAL_SKIP_CHILDREN_WITH_DESCENDANTS_1() {
        assertMetamorphicRelationFor(fixture(10));
    }

    @Test
    public void INTERNAL_SKIP_ENTIRE_WITH_SIBLING_1() {
        assertMetamorphicRelationFor(fixture(11));
    }

    @Test
    public void REMOVE_FIRST_LEAF_WITH_SIBLING_1() {
        assertMetamorphicRelationFor(fixture(12));
    }

    @Test
    public void REMOVE_LAST_LEAF_CHILD_1() {
        assertMetamorphicRelationFor(fixture(13));
    }

    @Test
    public void REMOVE_INTERNAL_SUBTREE_WITH_SIBLING_1() {
        assertMetamorphicRelationFor(fixture(14));
    }

    @Test
    public void REMOVE_LAST_INTERNAL_SUBTREE_1() {
        assertMetamorphicRelationFor(fixture(15));
    }

    @Test
    public void REMOVE_FROM_MIDDLE_OF_THREE_1() {
        assertMetamorphicRelationFor(fixture(16));
    }

    @Test
    public void STOP_AT_INTERNAL_HEAD_1() {
        assertMetamorphicRelationFor(fixture(17));
    }

    @Test
    public void STOP_AT_LEAF_TAIL_1() {
        assertMetamorphicRelationFor(fixture(18));
    }

    @Test
    public void STOP_AT_ROOT_TAIL_1() {
        assertMetamorphicRelationFor(fixture(19));
    }

    @Test
    public void ROOT_SKIP_CHILDREN_WITH_LARGE_SUBTREE_1() {
        assertMetamorphicRelationFor(fixture(20));
    }

    @Test
    public void ROOT_SKIP_ENTIRE_WITH_SUBTREE_1() {
        assertMetamorphicRelationFor(fixture(21));
    }

    @Test
    public void ROOT_REMOVE_WITH_SUBTREE_1() {
        assertMetamorphicRelationFor(fixture(22));
    }

    @Test
    public void MIXED_HEAD_ACTIONS_SINGLE_RUN_1() {
        assertMetamorphicRelationFor(fixture(23));
    }

    @Test
    public void MIXED_ALL_NONCONTINUE_ACTIONS_1() {
        assertMetamorphicRelationFor(fixture(24));
    }

    @Test
    public void TEXT_LEAF_ROOT_1() {
        assertMetamorphicRelationFor(fixture(25));
    }

    @Test
    public void COMMENT_LEAF_ROOT_1() {
        assertMetamorphicRelationFor(fixture(26));
    }

    @Test
    public void MIXED_NODE_KINDS_DEPTH_FIRST_1() {
        assertMetamorphicRelationFor(fixture(27));
    }

    @Test
    public void EMPTY_ELEMENT_ROOT_1() {
        assertMetamorphicRelationFor(fixture(28));
    }

    @Test
    public void DEEP_BRANCHING_TREE_1() {
        assertMetamorphicRelationFor(fixture(29));
    }

    @Test
    public void SKIP_CHILDREN_ON_LAST_SIBLING_1() {
        assertMetamorphicRelationFor(fixture(30));
    }

    @Test
    public void REMOVE_CHILD_WITHOUT_NEXT_SIBLING_1() {
        assertMetamorphicRelationFor(fixture(31));
    }

    @Test
    public void CONTINUE_AFTER_REMOVAL_TO_SIBLING_1() {
        assertMetamorphicRelationFor(fixture(32));
    }
}
