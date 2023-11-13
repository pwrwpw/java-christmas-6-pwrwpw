package christmas.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderTest {

    private Order order;
    private VisitDate visitDate;
    private SelectMenus selectMenus;
    private Amount totalAmount;

    @BeforeEach
    void setUp() {
        visitDate = new VisitDate(2023, 12, "25");
        selectMenus = new SelectMenus(List.of(new SelectMenu("티본스테이크", 2), new SelectMenu("바비큐립", 1)));
        totalAmount = new Amount(164000);
        order = new Order(visitDate, selectMenus, totalAmount);
    }

    @Test
    void 선물_자격_확인_테스트() {
        assertTrue(order.isEligibleForPresent(), "주문 금액이 선물 자격 금액 이상일 때 true를 반환해야 합니다.");
    }

    @Test
    void 최종_결제_금액_계산_테스트() {
        int discountAmount = 5000;
        int expectedFinalPayment = totalAmount.value() - discountAmount;
        assertEquals(expectedFinalPayment, order.calculateFinalPayment(discountAmount),
                "할인 금액을 적용한 최종 결제 금액이 올바르게 계산되어야 합니다.");
    }

    @Test
    void 음수_주문_금액_예외_테스트() {
        VisitDate visitDate = new VisitDate(2023, 12, "15");
        SelectMenus selectMenus = new SelectMenus(List.of(new SelectMenu("티본스테이크", 1)));
        Amount totalAmount = new Amount(-15000);

        assertThrows(IllegalArgumentException.class, () -> new Order(visitDate, selectMenus, totalAmount));
    }

    @Test
    void 비어있는_메뉴_목록_예외_테스트() {
        VisitDate visitDate = new VisitDate(2023, 12, "15");
        SelectMenus selectMenus = new SelectMenus(List.of());
        Amount totalAmount = new Amount(15000);

        assertThrows(IllegalArgumentException.class, () -> new Order(visitDate, selectMenus, totalAmount));
    }

    @Test
    void 방문_날짜_정보_제공_테스트() {
        assertEquals(visitDate, order.getVisitDate(), "주문한 방문 날짜 정보가 올바르게 제공되어야 합니다.");
    }

    @Test
    void 주문_내역_정보_제공_테스트() {
        assertEquals(selectMenus, order.getSelected(), "주문한 메뉴 정보가 올바르게 제공되어야 합니다.");
    }

    @Test
    void 총_금액_정보_제공_테스트() {
        assertEquals(totalAmount.value(), order.getTotalAmount(), "주문의 총 금액 정보가 올바르게 제공되어야 합니다.");
    }

    @Test
    void 방문_일자_정보_제공_테스트() {
        assertEquals(visitDate.getDay(), order.getVisitDay(), "주문한 방문 일자 정보가 올바르게 제공되어야 합니다.");
    }
}
