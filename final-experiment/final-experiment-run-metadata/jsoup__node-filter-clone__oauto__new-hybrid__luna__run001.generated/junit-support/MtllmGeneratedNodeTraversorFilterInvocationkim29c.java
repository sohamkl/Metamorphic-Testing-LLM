/** Framework-generated typed invocation boundary for Randoop. */
public final class MtllmGeneratedNodeTraversorFilterInvocationkim29c {
    private MtllmGeneratedNodeTraversorFilterInvocationkim29c() {}

    public static final class Input {
        private final org.jsoup.select.NodeFilter arg0;
        private final org.jsoup.nodes.Node arg1;

        public Input(org.jsoup.select.NodeFilter arg0, org.jsoup.nodes.Node arg1) {
            this.arg0 = arg0;
            this.arg1 = arg1;
        }

        public org.jsoup.select.NodeFilter arg0() { return arg0; }

        public org.jsoup.nodes.Node arg1() { return arg1; }
    }

    public static org.jsoup.select.NodeFilter.FilterResult invoke(Input source) {
        try {
            return org.jsoup.select.NodeTraversor.filter(source.arg0(), source.arg1());
        } catch (RuntimeException | Error failure) {
            throw failure;
        } catch (Throwable failure) {
            throw new IllegalStateException("SUT invocation failed", failure);
        }
    }

    public static boolean isUsable(Input source) {
        return source.arg0() != null && source.arg1() != null;
    }
}
