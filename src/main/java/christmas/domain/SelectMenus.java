package christmas.domain;

import christmas.exception.domain.visitdate.InvalidOrderException;
import christmas.utils.Parser;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SelectMenus {
    private final List<SelectMenu> items;

    public SelectMenus(List<SelectMenu> items) {
        validateDuplicate(items);
        this.items = items;
    }

    public List<SelectMenu> items() {
        return items;
    }
    public static SelectMenus from(List<String> menuItemsStrings) {
        List<SelectMenu> parsedItems = new ArrayList<>();
        for (String itemString : menuItemsStrings) {
            parsedItems.add(parseMenuItem(itemString));
        }
        return new SelectMenus(parsedItems);
    }

    private static SelectMenu parseMenuItem(String itemString) {
        String[] parts = itemString.split("-");
        if (parts.length != 2) {
            throw new InvalidOrderException();
        }

        String name = parts[0].trim();
        int quantity = Parser.parseInteger(parts[1].trim());
        return new SelectMenu(name, quantity);
    }

    private void validateDuplicate(List<SelectMenu> items) {
        boolean hasDuplicate = items.stream()
                .map(SelectMenu::menuName)
                .collect(Collectors.toSet())
                .size() != items.size();
        if (hasDuplicate) {
            throw new InvalidOrderException();
        }
    }
}