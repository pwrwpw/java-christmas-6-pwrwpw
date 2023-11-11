package christmas.domain;

import christmas.exception.domain.visitdate.InvalidOrderException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MenuItems {
    private final List<SelectMenu> items;

    public MenuItems(List<SelectMenu> items) {
        validateDuplicate(items);
        this.items = items;
    }

    public void addItem(SelectMenu item) {
        items.add(item);
    }

    public List<SelectMenu> getItems() {
        return Collections.unmodifiableList(items);
    }

    private void validateDuplicate(List<SelectMenu> items) {
        Set<SelectMenu> uniqueItems = new HashSet<>(items);
        if (uniqueItems.size() != items.size()) {
            throw new InvalidOrderException();
        }
    }
}
