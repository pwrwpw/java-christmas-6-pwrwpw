package christmas.domain;

public class User {

    private final Order order;
    private final EventBadge eventBadge;
    private final DiscountDetails discountDetails;

    public User(Order order, EventBadge eventBadge, DiscountDetails discountDetails) {
        this.order = order;
        this.eventBadge = eventBadge;
        this.discountDetails = discountDetails;
    }

    public Order getOrder() {
        return order;
    }

    public String getEventBadge() {
        return eventBadge.getBadgeName();
    }

    public DiscountDetails getDiscountDetails() {
        return discountDetails;
    }
}