/** Framework-generated deterministic callback policy. */
public final class MtllmGeneratedNodeFilterCallback15ar4v1Policy13 implements org.jsoup.select.NodeFilter {
    private final int threshold;

    public MtllmGeneratedNodeFilterCallback15ar4v1Policy13(int threshold) {
        this.threshold = threshold;
    }

    @Override
    public org.jsoup.select.NodeFilter.FilterResult head(org.jsoup.nodes.Node arg0, int arg1) {
        return org.jsoup.select.NodeFilter.FilterResult.CONTINUE;
    }

    @Override
    public org.jsoup.select.NodeFilter.FilterResult tail(org.jsoup.nodes.Node arg0, int arg1) {
        return arg1 >= threshold ? org.jsoup.select.NodeFilter.FilterResult.SKIP_CHILDREN : org.jsoup.select.NodeFilter.FilterResult.CONTINUE;
    }

}
