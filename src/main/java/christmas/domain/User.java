package christmas.domain;

public class User {

    private final VisitDate visitDate;
    private final SelectMenu selectMenu;
    private final Amount totalAmount;

    public User(VisitDate visitDate, SelectMenu selectMenu, Amount totalAmount) {
        this.visitDate = visitDate;
        this.selectMenu = selectMenu;
        this.totalAmount = totalAmount;
    }
}
