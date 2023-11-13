package christmas.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import christmas.exception.domain.visitdate.InvalidDayException;
import christmas.policy.ChristmasPolicy;
import org.junit.jupiter.api.Test;

class VisitDateTest {

    @Test
    void 유효한_날짜_테스트() {
        assertDoesNotThrow(() -> new VisitDate(ChristmasPolicy.EVENT_YEAR, ChristmasPolicy.DECEMBER, "20"));
    }

    @Test
    void 유효하지_않은_날짜_테스트_이른_일자() {
        assertThrows(InvalidDayException.class, () -> new VisitDate(ChristmasPolicy.EVENT_YEAR, ChristmasPolicy.DECEMBER, "0"));
    }

    @Test
    void 유효하지_않은_날짜_테스트_늦은_일자() {
        assertThrows(InvalidDayException.class, () -> new VisitDate(ChristmasPolicy.EVENT_YEAR, ChristmasPolicy.DECEMBER, "32"));
    }
}
