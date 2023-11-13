package christmas.domain;

import christmas.exception.domain.visitdate.InvalidOrderException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public record SelectMenus(List<SelectMenu> items) {
    public SelectMenus {
        validateDuplicate(items);
    }

    @Override
    public List<SelectMenu> items() {
        return Collections.unmodifiableList(items);
    }

    private void validateDuplicate(List<SelectMenu> items) {
        Set<SelectMenu> uniqueItems = new HashSet<>(items);
        if (uniqueItems.size() != items.size()) {
            throw new InvalidOrderException();
        }
    }
}
