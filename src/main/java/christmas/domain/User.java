package christmas.domain;

public class User {

    private final Order order;
    private final EventBadge eventBadge;

    public User(Order order, EventBadge eventBadge) {
        this.order = order;
        this.eventBadge = eventBadge;
    }

    public Order getOrder() {
        return order;
    }

    public String getEventBadge() {
        return eventBadge.getBadgeName();
    }
}