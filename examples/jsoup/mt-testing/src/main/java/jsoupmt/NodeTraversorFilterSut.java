package jsoupmt;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeFilter.FilterResult;
import org.jsoup.select.NodeTraversor;

/** A deterministic, data-oriented adapter around {@link NodeTraversor#filter}. */
public final class NodeTraversorFilterSut {
    private NodeTraversorFilterSut() {
    }

    public enum FilterAction {
        CONTINUE,
        SKIP_CHILDREN,
        SKIP_ENTIRELY,
        REMOVE,
        STOP
    }

    public enum VisitPhase {
        HEAD,
        TAIL
    }

    public record FilterPolicy(FilterAction action, String targetNodeName) {
        public FilterPolicy {
            Objects.requireNonNull(action, "action");
            targetNodeName = requireText(targetNodeName, "targetNodeName").toLowerCase(Locale.ROOT);
        }
    }

    public record FilterCase(
            String scenarioId,
            String html,
            String rootSelector,
            FilterPolicy policy,
            boolean cloneRootBeforeFiltering) {
        public FilterCase {
            scenarioId = requireText(scenarioId, "scenarioId");
            html = requireText(html, "html");
            rootSelector = requireText(rootSelector, "rootSelector");
            Objects.requireNonNull(policy, "policy");
        }
    }

    public record VisitEvent(VisitPhase phase, int depth, String nodeName, String fingerprint) {
        public VisitEvent {
            Objects.requireNonNull(phase, "phase");
            nodeName = requireText(nodeName, "nodeName");
            fingerprint = Objects.requireNonNull(fingerprint, "fingerprint");
        }
    }

    public record FilterTrace(
            String scenarioId,
            FilterPolicy policy,
            FilterResult terminalResult,
            int matchedTargets,
            int appliedActions,
            List<VisitEvent> events,
            String finalHtml) {
        public FilterTrace {
            scenarioId = requireText(scenarioId, "scenarioId");
            Objects.requireNonNull(policy, "policy");
            Objects.requireNonNull(terminalResult, "terminalResult");
            events = List.copyOf(events);
            finalHtml = Objects.requireNonNull(finalHtml, "finalHtml");
        }
    }

    public static FilterTrace run(FilterCase source) {
        Objects.requireNonNull(source, "source");
        Document document = Jsoup.parseBodyFragment(source.html());
        Element selectedRoot = document.selectFirst(source.rootSelector());
        if (selectedRoot == null) {
            throw new IllegalArgumentException("rootSelector did not match an element: " + source.rootSelector());
        }

        selectedRoot.remove();
        Node root = source.cloneRootBeforeFiltering() ? selectedRoot.clone() : selectedRoot;
        List<VisitEvent> events = new ArrayList<>();
        int[] matchedTargets = {0};
        int[] appliedActions = {0};

        NodeFilter filter = new NodeFilter() {
            @Override
            public FilterResult head(Node node, int depth) {
                events.add(event(VisitPhase.HEAD, node, depth));
                if (!matches(node, source.policy().targetNodeName())) {
                    return FilterResult.CONTINUE;
                }

                matchedTargets[0]++;
                appliedActions[0]++;
                return FilterResult.valueOf(source.policy().action().name());
            }

            @Override
            public FilterResult tail(Node node, int depth) {
                events.add(event(VisitPhase.TAIL, node, depth));
                return FilterResult.CONTINUE;
            }
        };

        FilterResult terminalResult = NodeTraversor.filter(filter, root);
        return new FilterTrace(
                source.scenarioId(),
                source.policy(),
                terminalResult,
                matchedTargets[0],
                appliedActions[0],
                events,
                root.outerHtml());
    }

    private static VisitEvent event(VisitPhase phase, Node node, int depth) {
        return new VisitEvent(phase, depth, node.nodeName(), node.outerHtml());
    }

    private static boolean matches(Node node, String targetNodeName) {
        return node.nodeName().equalsIgnoreCase(targetNodeName);
    }

    private static String requireText(String value, String name) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(name + " must not be blank");
        }
        return value;
    }
}
