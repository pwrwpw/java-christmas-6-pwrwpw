package christmas.view;

import static christmas.view.OutputViewMessages.BENEFIT_DETAILS;
import static christmas.view.OutputViewMessages.BEVERAGE_ORDER_RESTRICTION;
import static christmas.view.OutputViewMessages.CHRISTMAS_DISCOUNT;
import static christmas.view.OutputViewMessages.EVENT_BADGE;
import static christmas.view.OutputViewMessages.EVENT_GUIDELINES;
import static christmas.view.OutputViewMessages.EVENT_PREVIEW;
import static christmas.view.OutputViewMessages.EXPECTED_PAYMENT;
import static christmas.view.OutputViewMessages.MAX_MENU_ITEMS;
import static christmas.view.OutputViewMessages.MAX_MENU_ITEMS_EX;
import static christmas.view.OutputViewMessages.MINIMUM_ORDER_AMOUNT;
import static christmas.view.OutputViewMessages.NO_BENEFIT;
import static christmas.view.OutputViewMessages.ORDER_MENU;
import static christmas.view.OutputViewMessages.PRESENT_MENU;
import static christmas.view.OutputViewMessages.PRESENT_MENU_PRICE;
import static christmas.view.OutputViewMessages.SPECIAL_DISCOUNT;
import static christmas.view.OutputViewMessages.TOTAL_BENEFIT_AMOUNT;
import static christmas.view.OutputViewMessages.TOTAL_BENEFIT_OUTPUT;
import static christmas.view.OutputViewMessages.TOTAL_PRICE;
import static christmas.view.OutputViewMessages.TOTAL_PRICE_OUTPUT;
import static christmas.view.OutputViewMessages.WEEKDAY_DISCOUNT;
import static christmas.view.OutputViewMessages.WEEKEND_DISCOUNT;
import static christmas.view.OutputViewMessages.WELCOME;

import christmas.domain.DiscountDetails;
import christmas.policy.ChristmasPolicy;

public class OutputView {

    private static final String ITEM_UNIT = "개";
    private static final String SPACE = " ";
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public void printWelcomeMessage() {
        WELCOME.print(ChristmasPolicy.DECEMBER);
    }

    public void printEventPreviewMessage(int userInputMonth, int userInputDay) {
        EVENT_PREVIEW.print(userInputMonth, userInputDay);
    }

    public void printEventGuidelines() {
        printLineSeparator();
        EVENT_GUIDELINES.print();
    }

    public void printMinimumOrderAmountNotice() {
        MINIMUM_ORDER_AMOUNT.print(ChristmasPolicy.MINIMUM_ORDER_AMOUNT);
    }

    public void printBeverageOrderRestrictionNotice() {
        BEVERAGE_ORDER_RESTRICTION.print();
    }

    public void printMaxMenuItemsNotice() {
        MAX_MENU_ITEMS.print(ChristmasPolicy.MAX_MENU_ITEMS);
        MAX_MENU_ITEMS_EX.print();
        printLineSeparator();
    }

    public void printOrderMenuMessage() {
        printLineSeparator();
        ORDER_MENU.print();
    }

    public void printTotalPriceOutputMessage(int totalPrice) {
        printLineSeparator();
        TOTAL_PRICE.print();
        TOTAL_PRICE_OUTPUT.print(totalPrice);
    }

    public void printPresentMenuMessage() {
        printLineSeparator();
        PRESENT_MENU.print();
    }

    public void printPresentMenuOutputMessage(String menuName, int menuCount) {
        System.out.println(menuName + SPACE + menuCount + ITEM_UNIT);
    }

    public void printPresentMenuOutputMessage(String value) {
        System.out.println(value);
    }

    public void printOrderMenuItem(String menuName, int count) {
        System.out.println(menuName + SPACE + count + ITEM_UNIT);
    }

    public void displayDiscountDetails(DiscountDetails discountDetails) {
        boolean isDiscounted = false;

        isDiscounted |= displayDateBasedDiscount(discountDetails);
        isDiscounted |= displayMenuBasedDiscount(discountDetails);
        isDiscounted |= displayStarDayDiscount(discountDetails);
        isDiscounted |= displayPresentMenuPriceDiscount(discountDetails);

        if (!isDiscounted) {
            NO_BENEFIT.print();
        }
    }

    private boolean displayDateBasedDiscount(DiscountDetails discountDetails) {
        if (discountDetails.getDateBasedDiscount() > 0) {
            CHRISTMAS_DISCOUNT.print(discountDetails.getDateBasedDiscount());
            return true;
        }
        return false;
    }

    private boolean displayMenuBasedDiscount(DiscountDetails discountDetails) {
        if (discountDetails.getMenuBasedDiscount() <= 0) {
            return false;
        }
        if (discountDetails.getIsWeek()) {
            WEEKDAY_DISCOUNT.print(discountDetails.getMenuBasedDiscount());
            return true;
        }
        WEEKEND_DISCOUNT.print(discountDetails.getMenuBasedDiscount());
        return true;
    }


    private boolean displayStarDayDiscount(DiscountDetails discountDetails) {
        if (discountDetails.getStarDayDiscount() > 0) {
            SPECIAL_DISCOUNT.print(discountDetails.getStarDayDiscount());
            return true;
        }
        return false;
    }

    private boolean displayPresentMenuPriceDiscount(DiscountDetails discountDetails) {
        if (discountDetails.getPresentMenuPrice() > 0) {
            PRESENT_MENU_PRICE.print(discountDetails.getPresentMenuPrice());
            return true;
        }
        return false;
    }

    public void printBenefitMessage() {
        printLineSeparator();
        BENEFIT_DETAILS.print();
    }

    public void printTotalBenefitPriceMessage(int totalPrice) {
        printLineSeparator();
        TOTAL_BENEFIT_AMOUNT.print();
        if (totalPrice == 0) {
            TOTAL_PRICE_OUTPUT.print(totalPrice);
            return;
        }
        TOTAL_BENEFIT_OUTPUT.print(totalPrice);

    }

    public void printExpectedPaymentPriceMessage(int totalPrice) {
        printLineSeparator();
        EXPECTED_PAYMENT.print();
        TOTAL_PRICE_OUTPUT.print(totalPrice);
    }

    public void printEventBadgeMessage(String eventBadge) {
        printLineSeparator();
        EVENT_BADGE.print(ChristmasPolicy.DECEMBER);
        System.out.println(eventBadge);
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    private void printLineSeparator() {
        System.out.print(LINE_SEPARATOR);
    }
}
