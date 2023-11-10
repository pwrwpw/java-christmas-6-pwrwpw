package christmas.policy;

import christmas.domain.MenuItem;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum MenuPolicy {
    APPETIZER(Arrays.asList(
            new MenuItem("양송이수프", 6000),
            new MenuItem("타파스", 5500),
            new MenuItem("시저샐러드", 8000))),
    MAIN(Arrays.asList(
            new MenuItem("티본스테이크", 55000),
            new MenuItem("바비큐립", 54000),
            new MenuItem("해산물파스타", 35000),
            new MenuItem("크리스마스파스타", 25000))),
    DESSERT(Arrays.asList(
            new MenuItem("초코케이크", 15000),
            new MenuItem("아이스크림", 5000))),
    DRINK(Arrays.asList(
            new MenuItem("제로콜라", 3000),
            new MenuItem("레드와인", 60000),
            new MenuItem("샴페인", 25000)));

    private final List<MenuItem> menuItems;

    MenuPolicy(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    // Getters
    public List<MenuItem> getMenuItems() {
        return Collections.unmodifiableList(menuItems);
    }
}