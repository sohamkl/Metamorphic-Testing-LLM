import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeFilter.FilterResult;
import org.jsoup.select.NodeTraversor;
import org.junit.jupiter.api.Test;

import jsoupmt.NodeTraversorFilterCloneMetamorphicSpec;

public class GeneratedNodeTraversorFilterCloneMetamorphicFailingTest {
    private static final class Policy implements NodeFilter {
        private final String specialNodeName;
        private final FilterResult specialHead;
        private final FilterResult specialTail;
        private final FilterResult defaultHead;
        private final FilterResult defaultTail;

        private Policy(
                String specialNodeName,
                FilterResult specialHead,
                FilterResult specialTail,
                FilterResult defaultHead,
                FilterResult defaultTail) {
            this.specialNodeName = specialNodeName;
            this.specialHead = specialHead;
            this.specialTail = specialTail;
            this.defaultHead = defaultHead;
            this.defaultTail = defaultTail;
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return node.nodeName().equals(specialNodeName) ? specialHead : defaultHead;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return node.nodeName().equals(specialNodeName) ? specialTail : defaultTail;
        }
    }

    private static void run(Node root, NodeFilter filter) {
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0],
                (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

}
