package christmas.view;

import christmas.policy.ChristmasPolicy;

public class OutputView {

    public void printWelcomeMessage() {
        OutputViewMessages.WELCOME.print(ChristmasPolicy.DECEMBER);
    }

    public void printEventPreviewMessage(int userInputMonth, int userInputDay) {
        OutputViewMessages.EVENT_PREVIEW.print(userInputDay, userInputMonth);
        System.out.println();
    }

    public void printEventGuidelines() {
        OutputViewMessages.EVENT_GUIDELINES.print();
    }

    public void printMinimumOrderAmountNotice() {
        OutputViewMessages.MINIMUM_ORDER_AMOUNT.print(ChristmasPolicy.MINIMUM_ORDER_AMOUNT);
    }

    public void printBeverageOrderRestrictionNotice() {
        OutputViewMessages.BEVERAGE_ORDER_RESTRICTION.print();
    }

    public void printMaxMenuItemsNotice() {
        OutputViewMessages.MAX_MENU_ITEMS.print(ChristmasPolicy.MAX_MENU_ITEMS);
        OutputViewMessages.MAX_MENU_ITEMS_EX.print();
    }

    public void printOrderMenuMessage() {
        OutputViewMessages.ORDER_MENU.print();
    }

    public void printTotalPriceMessage() {
        OutputViewMessages.TOTAL_PRICE.print();
    }

    public void printTotalPriceOutputMessage(int totalPrice) {
        OutputViewMessages.TOTAL_PRICE_OUTPUT.print(totalPrice);
    }

    public void printPresentMenuMessage() {
        OutputViewMessages.PRESENT_MENU.print();
    }
    public void printPresentMenuOutputMessage(String menuName, int menuCount) {
        System.out.println(menuName + " " + menuCount + "개");
    }
    public void printPresentMenuOutputMessage(String value) {
        System.out.println(value);
    }

    public void printBenefitMessage() {
        OutputViewMessages.BENEFIT_DETAILS.print();
    }

    public void printTotalBenefitPriceMessage() {
        OutputViewMessages.TOTAL_BENEFIT_AMOUNT.print();
    }

    public void printExpectedPaymentPriceMessage() {
        OutputViewMessages.EXPECTED_PAYMENT.print();
    }

    public void printEventBadgeMessage() {
        OutputViewMessages.EVENT_BADGE.print(ChristmasPolicy.DECEMBER);
    }

    public void printNoBenefitMessage() {
        OutputViewMessages.NO_BENEFIT.print();
    }
}
