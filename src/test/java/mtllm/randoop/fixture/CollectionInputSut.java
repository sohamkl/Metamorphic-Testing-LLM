package mtllm.randoop.fixture;

import java.util.List;

public final class CollectionInputSut {
    public interface Item {
        int value();
    }

    public static final class ItemImpl implements Item {
        private final int value;

        public ItemImpl(int value) {
            this.value = value;
        }

        @Override
        public int value() {
            return value;
        }
    }

    public int sum(List<Item> items) {
        return items.stream().mapToInt(Item::value).sum();
    }
}
