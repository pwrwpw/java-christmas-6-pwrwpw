package christmas.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {

    private Order order;
    private EventBadge eventBadge;
    private DiscountDetails discountDetails;
    private User user;

    @BeforeEach
    void 설정() {
        order = mock(Order.class);
        eventBadge = mock(EventBadge.class);
        discountDetails = mock(DiscountDetails.class);
        user = new User(order, eventBadge, discountDetails);
    }

    @Test
    void 선물_대상_참_테스트() {
        when(order.isEligibleForPresent()).thenReturn(true);
        assertEquals(true, user.isOrderEligibleForPresent());
    }

    @Test
    void 선물_대상_거짓_테스트() {
        when(order.isEligibleForPresent()).thenReturn(false);
        assertEquals(false, user.isOrderEligibleForPresent());
    }

    @Test
    void 최종_결제_금액_계산_테스트() {
        when(order.calculateFinalPayment(discountDetails.getTotalDiscount())).thenReturn(9000);
        assertEquals(9000, user.calculateFinalPayment());
    }

    @Test
    void 총_주문_금액_테스트() {
        when(order.getTotalAmount()).thenReturn(10000);
        assertEquals(10000, user.getTotalOrderAmount());
    }

    @Test
    void 총_혜택_금액_테스트() {
        when(discountDetails.getTotalDiscount()).thenReturn(1000);
        when(discountDetails.getPresentMenuPrice()).thenReturn(500);
        assertEquals(1500, user.getTotalBenefit());
    }

    @Test
    void 이벤트_배지_이름_테스트() {
        when(eventBadge.getBadgeName()).thenReturn("산타");
        assertEquals("산타", user.getEventBadgeName());
    }
}
