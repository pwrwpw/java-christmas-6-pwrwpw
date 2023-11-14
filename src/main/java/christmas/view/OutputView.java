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
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public void printWelcomeMessage() {
        WELCOME.print(ChristmasPolicy.DECEMBER);
    }

    public void printEventPreviewMessage(int userInputMonth, int userInputDay) {
        EVENT_PREVIEW.print(userInputMonth, userInputDay);
        System.out.print(LINE_SEPARATOR);
    }

    public void printEventGuidelines() {
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
    }

    public void printOrderMenuMessage() {
        ORDER_MENU.print();
    }

    public void printTotalPriceOutputMessage(int totalPrice) {
        TOTAL_PRICE.print();
        TOTAL_PRICE_OUTPUT.print(totalPrice);
    }

    public void printPresentMenuMessage() {
        PRESENT_MENU.print();
    }

    public void printPresentMenuOutputMessage(String menuName, int menuCount) {
        System.out.println(menuName + " " + menuCount + ITEM_UNIT);
    }

    public void printPresentMenuOutputMessage(String value) {
        System.out.println(value);
    }

    public void printOrderMenuItem(String menuName, int count) {
        System.out.println(menuName + " " + count + ITEM_UNIT);
    }

    public void displayDiscountDetails(DiscountDetails discountDetails) {
        boolean isDiscounted = false;

        if (discountDetails.getDateBasedDiscount() > 0) {
            CHRISTMAS_DISCOUNT.print(discountDetails.getDateBasedDiscount());
            isDiscounted = true;
        }

        if (discountDetails.getMenuBasedDiscount() > 0 && discountDetails.getIsWeek()) {
            WEEKDAY_DISCOUNT.print(discountDetails.getMenuBasedDiscount());
            isDiscounted = true;
        }

        if (discountDetails.getMenuBasedDiscount() > 0 && !discountDetails.getIsWeek()) {
            WEEKEND_DISCOUNT.print(discountDetails.getMenuBasedDiscount());
            isDiscounted = true;
        }

        if (discountDetails.getStarDayDiscount() > 0) {
            SPECIAL_DISCOUNT.print(discountDetails.getStarDayDiscount());
            isDiscounted = true;
        }

        if (discountDetails.getPresentMenuPrice() > 0) {
            PRESENT_MENU_PRICE.print(discountDetails.getPresentMenuPrice());
            isDiscounted = true;
        }

        if (!isDiscounted) {
            NO_BENEFIT.print();
        }
    }

    public void printBenefitMessage() {
        BENEFIT_DETAILS.print();
    }

    public void printTotalBenefitPriceMessage(int totalPrice) {
        TOTAL_BENEFIT_AMOUNT.print();
        if (totalPrice == 0) {
            TOTAL_PRICE_OUTPUT.print(totalPrice);
            return;
        }
        TOTAL_BENEFIT_OUTPUT.print(totalPrice);

    }

    public void printExpectedPaymentPriceMessage(int totalPrice) {
        EXPECTED_PAYMENT.print();
        TOTAL_PRICE_OUTPUT.print(totalPrice);
    }

    public void printEventBadgeMessage(String eventBadge) {
        EVENT_BADGE.print(ChristmasPolicy.DECEMBER);
        System.out.println(eventBadge);
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }
}
