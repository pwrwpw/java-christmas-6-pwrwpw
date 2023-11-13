package christmas.domain;

import christmas.policy.ChristmasPolicy;

public class Order {

    private final VisitDate visitDate;
    private final SelectMenus selectMenus;
    private final Amount totalAmount;

    public Order(VisitDate visitDate, SelectMenus selectMenus, Amount totalAmount) {
        this.visitDate = visitDate;
        this.selectMenus = selectMenus;
        this.totalAmount = totalAmount;
    }

    public boolean isEligibleForPresent() {
        return getTotalAmount() > ChristmasPolicy.PRESENT_THRESHOLD_AMOUNT;
    }
    public int calculateFinalPayment(int discountAmount) {
        return getTotalAmount() - discountAmount;
    }
    public VisitDate getVisitDate() {
        return visitDate;
    }

    public int getTotalAmount() {
        return totalAmount.value();
    }

    public SelectMenus getSelected() {
        return selectMenus;
    }

    public int getVisitDay() {
        return visitDate.getDay();
    }
}
