package christmas.view;

import christmas.domain.DiscountDetails;
import christmas.policy.ChristmasPolicy;

public class OutputView {

    public void printWelcomeMessage() {
        OutputViewMessages.WELCOME.print(ChristmasPolicy.DECEMBER);
    }

    public void printEventPreviewMessage(int userInputMonth, int userInputDay) {
        OutputViewMessages.EVENT_PREVIEW.print(userInputMonth, userInputDay);
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

    public void printTotalPriceOutputMessage(int totalPrice) {
        OutputViewMessages.TOTAL_PRICE.print();
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

    public void printOrderMenuItem(String menuName, int count) {
        System.out.println(menuName + " " + count + "개");
    }

    public void displayDiscountDetails(DiscountDetails discountDetails) {
        // 할인 정보 출력 로직
        if (discountDetails.getDateBasedDiscount() > 0) {
            System.out.println("크리스마스 디데이 할인: -" + discountDetails.getDateBasedDiscount() + "원");
        }
        if (discountDetails.getMenuBasedDiscount() > 0) {
            System.out.println("메뉴 기반 할인: -" + discountDetails.getMenuBasedDiscount() + "원");
        }
        if (discountDetails.getStarDayDiscount() > 0) {
            System.out.println("특별 할인: -" + discountDetails.getStarDayDiscount() + "원");
        }
        if (discountDetails.getPresentMenuPrice() > 0) {
            System.out.println("선물 메뉴 가격: -" + discountDetails.getPresentMenuPrice() + "원");
        }
    }

    public void printBenefitMessage() {
        OutputViewMessages.BENEFIT_DETAILS.print();
    }

    public void printTotalBenefitPriceMessage(int totalPrice) {
        OutputViewMessages.TOTAL_BENEFIT_AMOUNT.print();
        OutputViewMessages.TOTAL_BENEFIT_OUTPUT.print(totalPrice);

    }

    public void printExpectedPaymentPriceMessage(int totalPrice) {
        OutputViewMessages.EXPECTED_PAYMENT.print();
        OutputViewMessages.TOTAL_PRICE_OUTPUT.print(totalPrice);
    }

    public void printEventBadgeMessage() {
        OutputViewMessages.EVENT_BADGE.print(ChristmasPolicy.DECEMBER);
    }

    public void printNoBenefitMessage() {
        OutputViewMessages.NO_BENEFIT.print();
    }
}
