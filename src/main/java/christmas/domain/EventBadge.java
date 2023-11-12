package christmas.domain;

public class EventBadge {

    private final String badgeName;

    public EventBadge(int totalBenefitAmount) {
        this.badgeName = determineEventBadge(totalBenefitAmount);
    }

    private String determineEventBadge(int totalBenefitAmount) {
        if (totalBenefitAmount >= 20000) {
            return "산타";
        }
        if (totalBenefitAmount >= 10000) {
            return "트리";
        }
        if (totalBenefitAmount >= 5000) {
            return "별";
        }
        return "없음";
    }
    public String getBadgeName() {
        return badgeName;
    }
}
