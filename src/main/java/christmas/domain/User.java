package christmas.domain;

import java.util.List;

public class User {

    private final Order order;
    private final EventBadge eventBadge;
    private final DiscountDetails discountDetails;

    public User(Order order, EventBadge eventBadge, DiscountDetails discountDetails) {
        this.order = order;
        this.eventBadge = eventBadge;
        this.discountDetails = discountDetails;
    }

    public boolean isOrderEligibleForPresent() {
        return order.isEligibleForPresent();
    }

    public int calculateFinalPayment() {
        return order.calculateFinalPayment(discountDetails.getTotalDiscount());
    }

    public int getTotalOrderAmount() {
        return order.getTotalAmount();
    }

    public int getTotalBenefit() {
        return discountDetails.getTotalDiscount() + discountDetails.getPresentMenuPrice();
    }

    public String getEventBadgeName() {
        return eventBadge.getBadgeName();
    }

    public DiscountDetails getDiscountDetails() {
        return discountDetails;
    }

    public VisitDate getVisitDate() {
        return order.getVisitDate();
    }

    public SelectMenus getSelected() {
        return order.getSelected();
    }
}