package mtllm.sut;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ReflectiveObjectFactoryTest {
    @Test
    void constructsReceiverThroughNestedPublicFactory() throws Exception {
        Receiver receiver = (Receiver) ReflectiveObjectFactory.create(Receiver.class);

        assertNotNull(receiver.policy);
        assertEquals("basic", receiver.policy.name);
    }

    public static final class Receiver {
        private final Policy policy;

        public Receiver(Policy policy) {
            this.policy = policy;
        }
    }

    public static final class Policy {
        private final String name;

        private Policy(String name) {
            this.name = name;
        }

        public static Policy basic() {
            return new Policy("basic");
        }
    }
}
