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
        Amount totalAmount = calculateTotalAmount(selectMenus);
        Order order = createOrder(visitDate, selectMenus, totalAmount);
        DiscountDetails discountDetails = applyDiscounts(order, selectMenus);

        int totalBenefitAmount = calculateTotalBenefit(discountDetails);
        EventBadge eventBadge = createEventBadge(totalBenefitAmount);

        return new User(order, eventBadge, discountDetails);
    }

    private Amount calculateTotalAmount(SelectMenus selectMenus) {
        return orderService.calculateTotalPrice(selectMenus);
    }

    private Order createOrder(VisitDate visitDate, SelectMenus selectMenus, Amount totalAmount) {
        return orderService.createOrder(visitDate, selectMenus, totalAmount);
    }

    private DiscountDetails applyDiscounts(Order order, SelectMenus selectMenus) {
        return christmasDiscountService.applyDiscount(order, selectMenus);
    }

    private int calculateTotalBenefit(DiscountDetails discountDetails) {
        return discountDetails.getTotalDiscount() + discountDetails.getPresentMenuPrice();
    }

    private EventBadge createEventBadge(int totalBenefitAmount) {
        return new EventBadge(totalBenefitAmount);
    }
}
