package christmas.service;

import christmas.domain.Amount;
import christmas.domain.Order;
import christmas.domain.SelectMenus;
import christmas.domain.VisitDate;

public interface OrderService {

    Order createOrder(VisitDate visitDate, SelectMenus selectMenus, Amount totalAmount);

    Amount calculateTotalPrice(SelectMenus selectMenus);
}
