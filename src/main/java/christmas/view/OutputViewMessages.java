package christmas.view;

public enum OutputViewMessages {
    WELCOME("안녕하세요! 우테코 식당 %d월 이벤트 플래너 입니다"),
    EVENT_PREVIEW("%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    EVENT_GUIDELINES("주문시 이벤트 주의사항"),
    MINIMUM_ORDER_AMOUNT("총주문 금액 %,d원 이상부터 이벤트가 적용됩니다."),
    BEVERAGE_ORDER_RESTRICTION("음료만 주문 시, 주문할 수 없습니다."),
    MAX_MENU_ITEMS("메뉴는 한 번에 최대 %d개까지만 주문할 수 있습니다."),
    ORDER_MENU("<주문 메뉴>"),
    TOTAL_PRICE("<할인 전 총주문 금액>"),
    PRESENT_MENU("<증정 메뉴>"),
    BENEFIT_DETAILS("<혜택 내역>"),
    TOTAL_BENEFIT_AMOUNT("<총혜택 금액>"),
    EXPECTED_PAYMENT("<할인 후 예상 결제 금액>"),
    EVENT_BADGE("<%d월 이벤트 배지>"),
    NO_BENEFIT("없음");

    private final String text;

    OutputViewMessages(String text) {
        this.text = text;
    }

    public void print(Object... args) {
        System.out.printf((text) + "%n", args);
    }
}
