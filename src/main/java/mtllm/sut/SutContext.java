package mtllm.sut;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Stores the Java source code that will be shown to the LLM.
 *
 * <p>In simple terms, this is the bundle of context about the SUT: the main class plus any
 * helper files that may be needed to generate correct JUnit code.</p>
 */
public final class SutContext {
    private final Path classFile;
    private final String classSource;
    private final List<SourceFile> supportFiles;

    public SutContext(Path classFile, String classSource, List<SourceFile> supportFiles) {
        this.classFile = classFile;
        this.classSource = classSource == null ? "" : classSource;
        this.supportFiles = Collections.unmodifiableList(new ArrayList<>(supportFiles));
    }

    public Path classFile() {
        return classFile;
    }

    public String classSource() {
        return classSource;
    }

    public List<SourceFile> supportFiles() {
        return supportFiles;
    }

    /**
     * A support Java file included in the prompt, such as a first-level dependency.
     */
    public static final class SourceFile {
        private final Path path;
        private final String source;

        public SourceFile(Path path, String source) {
            this.path = path;
            this.source = source == null ? "" : source;
        }

        public Path path() {
            return path;
        }

        public String source() {
            return source;
        }
    }
}
