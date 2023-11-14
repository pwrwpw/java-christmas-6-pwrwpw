package christmas.policy;

import java.util.Set;

public class ChristmasPolicy {
    public static final int EVENT_YEAR = 2023;
    public static final int DECEMBER = 12;
    public static final int DEC_MINIMUM_DAY = 1;
    public static final int DEC_MAXIMUM_DAY = 31;
    public static final int MINIMUM_ORDER_AMOUNT = 10_000;
    public static final int MIN_MENU_ITEMS = 1;
    public static final int MAX_MENU_ITEMS = 20;

    public static final int PRESENT_THRESHOLD_AMOUNT = 120_000;
    public static final String PRESENT_NAME = "샴페인";
    public static final int PRESENT_QUANTITY = 1;
    public static final String NO_PRESENT = "없음";
    private static final Set<Integer> STAR_DAYS = Set.of(3, 10, 17, 24, 25, 31);

    public static boolean isStarDay(int day) {
        return STAR_DAYS.contains(day);
    }
}
