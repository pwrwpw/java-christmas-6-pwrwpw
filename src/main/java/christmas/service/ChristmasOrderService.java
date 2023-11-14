package christmas.service;

import christmas.domain.Amount;
import christmas.domain.Order;
import christmas.domain.SelectMenus;
import christmas.domain.VisitDate;
import christmas.policy.MenuPolicy;

public class ChristmasOrderService implements OrderService {

    @Override
    public Order createOrder(VisitDate visitDate, SelectMenus selectMenus, Amount totalAmount) {
        return new Order(visitDate, selectMenus, totalAmount);
    }

    @Override
    public Amount calculateTotalPrice(SelectMenus selectMenus) {
        int totalPrice = selectMenus.items().stream()
                .mapToInt(item -> item.menuCount() * MenuPolicy.getMenuPrice(item.menuName()))
                .sum();
        return new Amount(totalPrice);
    }
}
