package christmas.domain;

import christmas.policy.BadgePolicy;

public class EventBadge {

    private final String badgeName;

    public EventBadge(int totalBenefitAmount) {
        this.badgeName = determineEventBadge(totalBenefitAmount);
    }

    private String determineEventBadge(int totalBenefitAmount) {
        if (totalBenefitAmount >= BadgePolicy.SANTA_BENEFIT_AMOUNT) {
            return BadgePolicy.SANTA;
        }
        if (totalBenefitAmount >= BadgePolicy.TREE_BENEFIT_AMOUNT) {
            return BadgePolicy.TREE;
        }
        if (totalBenefitAmount >= BadgePolicy.STAR_BENEFIT_AMOUNT) {
            return BadgePolicy.STAR;
        }
        return BadgePolicy.NO_BADGE;
    }

    public String getBadgeName() {
        return badgeName;
    }
}
