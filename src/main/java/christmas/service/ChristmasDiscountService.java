package christmas.service;

import christmas.domain.DiscountDetails;
import christmas.domain.SelectMenus;
import christmas.domain.Order;
import christmas.policy.ChristmasPolicy;
import java.time.LocalDate;

public class ChristmasDiscountService implements DiscountService {

    @Override
    public DiscountDetails applyDiscount(Order order, SelectMenus menuItems) {
        LocalDate orderDate = LocalDate.of(ChristmasPolicy.EVENT_YEAR, ChristmasPolicy.DECEMBER, order.getVisitDay());

        return new DiscountDetails(order, orderDate, menuItems);
    }
}