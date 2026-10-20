package mtllm.sut;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TargetMethodResolverTest {
    @Test
    void resolvesCompleteSignatureWithGenericParameter() throws Exception {
        Method method = TargetMethodResolver.resolve(OverloadedSut.class,
                "public String detect(List<String> values, int threshold)");

        assertEquals("detect(java.util.List, int)", TargetMethodResolver.signature(method));
    }

    @Test
    void rejectsAmbiguousMethodName() {
        assertThrows(NoSuchMethodException.class,
                () -> TargetMethodResolver.resolve(OverloadedSut.class, "detect"));
    }

    @Test
    void resolvesExplicitZeroArgumentOverload() throws Exception {
        Method method = TargetMethodResolver.resolve(OverloadedSut.class, "detect()");

        assertEquals(0, method.getParameterCount());
    }

    public static final class OverloadedSut {
        public String detect() {
            return "none";
        }

        public String detect(String value) {
            return value;
        }

        public String detect(List<String> values, int threshold) {
            return values.size() >= threshold ? "yes" : "no";
        }
    }
}
