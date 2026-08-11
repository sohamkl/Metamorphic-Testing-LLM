package jsoupmt;

import jsoupmt.NodeTraversorFilterSut.FilterAction;
import jsoupmt.NodeTraversorFilterSut.FilterCase;
import jsoupmt.NodeTraversorFilterSut.FilterPolicy;

/** Public construction paths that make meaningful DOM inputs reachable to input generators. */
public final class NodeFilterCaseFactory {
    private NodeFilterCaseFactory() {
    }

    public static FilterCase source(
            String scenarioId,
            String html,
            String rootSelector,
            FilterAction action,
            String targetNodeName) {
        return new FilterCase(
                scenarioId,
                html,
                rootSelector,
                new FilterPolicy(action, targetNodeName),
                false);
    }

    public static FilterCase deepTree(FilterAction action) {
        return source(
                "DEEP_" + action,
                "<main id='root'><section><article><div><target><em>deep</em></target></div></article></section>"
                        + "<footer>after</footer></main>",
                "#root",
                action,
                "target");
    }

    public static FilterCase wideTree(FilterAction action) {
        return source(
                "WIDE_" + action,
                "<main id='root'><p>before</p><target><span>inside</span></target><aside>middle</aside>"
                        + "<div>after</div></main>",
                "#root",
                action,
                "target");
    }

    public static FilterCase repeatedTargets(FilterAction action) {
        return source(
                "REPEATED_" + action,
                "<main id='root'><target><b>one</b></target><section><!-- marker --><target>two</target></section>"
                        + "<target><i>three</i></target><footer>after</footer></main>",
                "#root",
                action,
                "target");
    }

    public static FilterCase mixedNodes(FilterAction action) {
        return source(
                "MIXED_" + action,
                "<main id='root'>text<!-- comment --><section></section><target><span>value</span></target>tail</main>",
                "#root",
                action,
                "target");
    }

    public static FilterCase targetAtEnd(FilterAction action) {
        return source(
                "TARGET_AT_END_" + action,
                "<main id='root'><header>first</header><section><p>middle</p></section><target>last</target></main>",
                "#root",
                action,
                "target");
    }
}
