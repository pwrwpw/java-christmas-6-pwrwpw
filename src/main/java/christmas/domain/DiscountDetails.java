package christmas.domain;

import christmas.policy.ChristmasPolicy;
import christmas.policy.MenuPolicy;
import java.time.LocalDate;
import java.time.DayOfWeek;

public class DiscountDetails {
    private final int dateBasedDiscount;
    private final int menuBasedDiscount;
    private final int starDayDiscount;
    private final int presentMenuPrice;
    private boolean isWeek;

    public DiscountDetails(Order order, LocalDate orderDate, SelectMenus menuName) {
        this.dateBasedDiscount = calculateDateBasedDiscount(orderDate);
        this.menuBasedDiscount = calculateMenuBasedDiscount(orderDate, menuName);
        this.starDayDiscount = calculateStarDayDiscount(orderDate);
        this.presentMenuPrice = calculatePresentMenuPrice(order.getTotalAmount());
    }

    public int getDateBasedDiscount() {
        return dateBasedDiscount;
    }

    public int getMenuBasedDiscount() {
        return menuBasedDiscount;
    }

    public int getStarDayDiscount() {
        return starDayDiscount;
    }

    public int getPresentMenuPrice() {
        return presentMenuPrice;
    }

    public int getTotalDiscount() {
        return dateBasedDiscount + menuBasedDiscount + starDayDiscount;
    }

    public boolean getIsWeek() {
        return isWeek;
    }

    private int calculatePresentMenuPrice(int totalAmount) {
        String presentMenuName = determinePresentMenuName(totalAmount);
        if (!presentMenuName.equals(ChristmasPolicy.NO_PRESENT)) {
            return MenuPolicy.getMenuPrice(presentMenuName);
        }
        return 0;
    }

    private String determinePresentMenuName(int totalAmount) {
        if (totalAmount > ChristmasPolicy.PRESENT_THRESHOLD_AMOUNT) {
            return ChristmasPolicy.PRESENT_NAME;
        }
        return ChristmasPolicy.NO_PRESENT;
    }


    private int calculateDateBasedDiscount(LocalDate orderDate) {
        int dayOfMonth = orderDate.getDayOfMonth();
        if (dayOfMonth <= 25) {
            return 1000 + (dayOfMonth - 1) * 100; // 날짜 기반 할인 계산
        }
        return 0;
    }

    private int calculateMenuBasedDiscount(LocalDate orderDate, SelectMenus menuItems) {
        int menuDiscount = 0;
        DayOfWeek dayOfWeek = orderDate.getDayOfWeek();

        if (!isWeekend(dayOfWeek)) {
            menuDiscount += calculateWeekDiscount(menuItems);
            isWeek = true;
        } else {
            menuDiscount += calculateWeekendDiscount(menuItems);
            isWeek = false;
        }

        return menuDiscount;
    }

    private int calculateWeekDiscount(SelectMenus menuItems) {
        int weekDiscount = 0;

        for (SelectMenu menuItem : menuItems.items()) {
            String menuName = menuItem.menuName();
            int count = menuItem.menuCount();

            if (MenuPolicy.isMenuItemInCategory(menuName, MenuPolicy.DESSERT)) {
                weekDiscount += 2023 * count;
            }
        }

        return weekDiscount;
    }

    private int calculateWeekendDiscount(SelectMenus menuItems) {
        int weekendDiscount = 0;

        for (SelectMenu menuItem : menuItems.items()) {
            String menuName = menuItem.menuName();
            int count = menuItem.menuCount();

            if (MenuPolicy.isMenuItemInCategory(menuName, MenuPolicy.MAIN)) {
                weekendDiscount += 2023 * count;
            }
        }

        return weekendDiscount;
    }


    private boolean isWeekend(DayOfWeek day) {
        return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
    }

    private int calculateStarDayDiscount(LocalDate orderDate) {
        int day = orderDate.getDayOfMonth();
        if (isStarDay(day)) {
            return 1000;
        }
        return 0;
    }

    private boolean isStarDay(int day) {
        return ChristmasPolicy.isStarDay(day);
    }
}