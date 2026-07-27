import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Order {
  private final List<LineItem> items;

  public Order(List<LineItem> items) {
    if (items == null) {
      throw new IllegalArgumentException("items must not be null");
    }
    this.items = Collections.unmodifiableList(new ArrayList<>(items));
  }

  public List<LineItem> getItems() {
    return items;
  }
}

Order source_input =
    new Order(List.of(new LineItem("Book", 2, 15.00), new LineItem("Pen", 3, 2.50)));

Order follow_input =
    new Order(List.of(new LineItem("Book", 4, 15.00), new LineItem("Pen", 6, 2.50)));
