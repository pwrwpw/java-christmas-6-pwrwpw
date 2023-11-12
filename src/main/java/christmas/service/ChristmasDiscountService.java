package christmas.service;

import christmas.domain.DiscountDetails;
import christmas.domain.MenuItems;
import christmas.domain.User;
import christmas.policy.ChristmasPolicy;
import java.time.LocalDate;

public class ChristmasDiscountService {

    public DiscountDetails applyDiscount(User user, MenuItems menuItems) {
        LocalDate orderDate = LocalDate.of(ChristmasPolicy.EVENT_YEAR, ChristmasPolicy.DECEMBER, user.getVisitDay());
        DiscountDetails discountDetails = new DiscountDetails(user, orderDate, menuItems);

        user.salesAmount(discountDetails.getTotalDiscount());
        return discountDetails;
    }
}