package christmas.domain;

import java.util.Collections;
import java.util.List;

public class MenuItems {
    private final List<SelectMenu> items;

    public MenuItems(List<SelectMenu> items) {
        this.items = items;
    }

    public void addItem(SelectMenu item) {
        items.add(item);
    }

    public List<SelectMenu> getItems() {
        return Collections.unmodifiableList(items);
    }

}
