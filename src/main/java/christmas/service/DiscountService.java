package christmas.service;

import christmas.domain.DiscountDetails;
import christmas.domain.Order;
import christmas.domain.SelectMenus;

public interface DiscountService {

    DiscountDetails applyDiscount(Order order, SelectMenus menuItems);
}
