package christmas.domain;

import christmas.exception.domain.visitdate.InvalidOrderException;
import christmas.exception.domain.visitdate.InvalidPriceException;
import christmas.policy.ChristmasPolicy;

public class Order {

    private final VisitDate visitDate;
    private final SelectMenus selectMenus;
    private final Amount totalAmount;

    public Order(VisitDate visitDate, SelectMenus selectMenus, Amount totalAmount) {
        validateOrder(visitDate, selectMenus, totalAmount);
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

    private void validateTotalAmount(Amount totalAmount) {
        if (totalAmount.value() < 0) {
            throw new InvalidPriceException();
        }
    }

    private void validateSelectMenus(SelectMenus selectMenus) {
        if (selectMenus.items().isEmpty()) {
            throw new InvalidOrderException();
        }
    }

    private void validateOrder(VisitDate visitDate, SelectMenus selectMenus, Amount totalAmount) {
        validateTotalAmount(totalAmount);
        validateSelectMenus(selectMenus);
    }
}
