package christmas.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class EventBadgeTest {

    @Test
    void 산타_배지_발급_테스트() {
        EventBadge badge = new EventBadge(25000);
        assertEquals("산타", badge.getBadgeName(), "20000원 이상 구매 시 산타 배지가 발급되어야 합니다.");
    }

    @Test
    void 트리_배지_발급_테스트() {
        EventBadge badge = new EventBadge(19000);
        assertEquals("트리", badge.getBadgeName(), "10000원 이상 20000원 미만 구매 시 트리 배지가 발급되어야 합니다.");
    }

    @Test
    void 별_배지_발급_테스트() {
        EventBadge badge = new EventBadge(7000);
        assertEquals("별", badge.getBadgeName(), "5000원 이상 10000원 미만 구매 시 별 배지가 발급되어야 합니다.");
    }

    @Test
    void 배지_미발급_테스트() {
        EventBadge badge = new EventBadge(3000);
        assertEquals("없음", badge.getBadgeName(), "5000원 미만 구매 시 배지가 발급되지 않아야 합니다.");
    }

    @Test
    void 경계값_산타_배지_테스트() {
        EventBadge badge = new EventBadge(20000);
        assertEquals("산타", badge.getBadgeName(), "정확히 20000원 구매 시 산타 배지가 발급되어야 합니다.");
    }

    @Test
    void 경계값_트리_배지_테스트() {
        EventBadge badge = new EventBadge(10000);
        assertEquals("트리", badge.getBadgeName(), "정확히 10000원 구매 시 트리 배지가 발급되어야 합니다.");
    }

    @Test
    void 경계값_별_배지_테스트() {
        EventBadge badge = new EventBadge(5000);
        assertEquals("별", badge.getBadgeName(), "정확히 5000원 구매 시 별 배지가 발급되어야 합니다.");
    }
}
