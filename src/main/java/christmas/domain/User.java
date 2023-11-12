package christmas.domain;

public class User {

    private final VisitDate visitDate;
    private final SelectMenu selectMenu;
    private Amount totalAmount;

    public User(VisitDate visitDate, SelectMenu selectMenu, Amount totalAmount) {
        this.visitDate = visitDate;
        this.selectMenu = selectMenu;
        this.totalAmount = totalAmount;
    }

    public int getVisitDay() {
        return visitDate.getDay();
    }

    public int getTotalAmount() {
        return totalAmount.value();
    }

    public void salesAmount(int discount) {
        totalAmount = new Amount(totalAmount.value() - discount);
    }
}
