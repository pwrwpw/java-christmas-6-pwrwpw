package christmas.service;

import christmas.domain.DiscountDetails;
import christmas.domain.MenuItems;
import christmas.domain.Order;
import christmas.policy.ChristmasPolicy;
import java.time.LocalDate;

public class ChristmasDiscountService {

    public DiscountDetails applyDiscount(Order order, MenuItems menuItems) {
        LocalDate orderDate = LocalDate.of(ChristmasPolicy.EVENT_YEAR, ChristmasPolicy.DECEMBER, order.getVisitDay());

        return new DiscountDetails(order, orderDate, menuItems);
    }
}