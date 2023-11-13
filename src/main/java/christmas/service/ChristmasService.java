package christmas.service;

import christmas.domain.Amount;
import christmas.domain.DiscountDetails;
import christmas.domain.EventBadge;
import christmas.domain.Order;
import christmas.domain.SelectMenus;
import christmas.domain.User;
import christmas.domain.VisitDate;

public class ChristmasService {

    private final ChristmasDiscountService christmasDiscountService;
    private final OrderService orderService;

    public ChristmasService(ChristmasDiscountService christmasDiscountService, OrderService orderService) {
        this.christmasDiscountService = christmasDiscountService;
        this.orderService = orderService;
    }

    public User processChristmasEvent(VisitDate visitDate, SelectMenus selectMenus) {
        Amount totalAmount = orderService.calculateTotalPrice(selectMenus);
        Order order = orderService.createOrder(visitDate, selectMenus, totalAmount);
        DiscountDetails discountDetails = christmasDiscountService.applyDiscount(order, selectMenus);

        int totalDiscountPrice = discountDetails.getTotalDiscount();
        int benefitPrice = totalDiscountPrice + discountDetails.getPresentMenuPrice();

        EventBadge eventBadge = new EventBadge(benefitPrice);
        return new User(order, eventBadge, discountDetails);
    }
}
