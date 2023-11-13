package christmas.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.policy.ChristmasPolicy;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DiscountDetailsTest {

    private Order order;
    private DiscountDetails discountDetails;

    @BeforeEach
    void setUp() {
        VisitDate visitDate = new VisitDate(ChristmasPolicy.EVENT_YEAR, ChristmasPolicy.DECEMBER, "25");
        List<SelectMenu> selectMenu = List.of(
                new SelectMenu("티본스테이크", 1),
                new SelectMenu("바비큐립", 1),
                new SelectMenu("초코케이크", 2),
                new SelectMenu("제로콜라", 1),
                new SelectMenu("아이스크림", 3)
        );
        Amount totalAmount = new Amount(157000);
        SelectMenus selectMenus = new SelectMenus(selectMenu);

        order = new Order(visitDate, selectMenus, totalAmount);
        LocalDate orderDate = LocalDate.of(ChristmasPolicy.EVENT_YEAR, ChristmasPolicy.DECEMBER, order.getVisitDay());

        discountDetails = new DiscountDetails(order, orderDate, selectMenus);
    }

    @Test
    void 날짜_기반_할인_계산_테스트() {
        int expectedDateBasedDiscount = 1000 + (25 - 1) * 100;
        assertEquals(expectedDateBasedDiscount, discountDetails.getDateBasedDiscount(), "날짜 기반 할인 계산이 올바르지 않습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"26", "27", "28", "29", "30", "31"})
    void 날짜_기반_할인_테스트_25일_이후(String day) {
        설정_변경(day);
        int expectedDateBasedDiscount = 0;
        assertEquals(expectedDateBasedDiscount, discountDetails.getDateBasedDiscount(),
                "날짜 기반 할인 계산이 올바르지 않습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "4", "5", "6", "7", "8"})
    void 평일_일자_할인_계산_테스트(String day) {
        설정_변경(day);
        int expectedWeekDayDiscount = 10115; // 평일 일자에 따른 예상 할인액
        assertEquals(expectedWeekDayDiscount, discountDetails.getMenuBasedDiscount(), "평일 일자 할인 계산이 올바르지 않습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"2", "3", "9", "10", "16", "17", "23", "24", "30", "31"})
    void 주말_일자_할인_계산_테스트(String day) {
        설정_변경(day);
        int expectedWeekendDiscount = 4046; // 주말 일자에 따른 예상 할인액
        assertEquals(expectedWeekendDiscount, discountDetails.getMenuBasedDiscount(), "주말 일자 할인 계산이 올바르지 않습니다.");
    }

    @Test
    void 별점_일자_할인_계산_테스트() {
        int expectedStarDayDiscount = 1000; // 별점 일자에 따른 예상 할인액
        assertEquals(expectedStarDayDiscount, discountDetails.getStarDayDiscount(), "별점 일자 할인 계산이 올바르지 않습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "4", "5", "6", "7", "8"})
    void 별점_일자_아닌_날짜_테스트(String day) {
        설정_변경(day);
        int expectedStarDayDiscount = 0; // 별점 일자가 아닌 날짜에 따른 예상 할인액
        assertEquals(expectedStarDayDiscount, discountDetails.getStarDayDiscount(),
                "별점 일자가 아닌 날짜에 대한 할인 계산이 올바르지 않습니다.");
    }

    @Test
    void 증정_메뉴_가격_계산_테스트() {
        int expectedPresentMenuPrice = 25000; // 증정 메뉴에 따른 예상 가격
        assertEquals(expectedPresentMenuPrice, discountDetails.getPresentMenuPrice(), "증정 메뉴 가격 계산이 올바르지 않습니다.");
    }

    @Test
    void 총_할인_금액_계산_테스트() {
        int expectedTotalDiscount = discountDetails.getDateBasedDiscount() + discountDetails.getMenuBasedDiscount()
                + discountDetails.getStarDayDiscount();
        assertEquals(expectedTotalDiscount, discountDetails.getTotalDiscount(), "총 할인 금액 계산이 올바르지 않습니다.");
    }

    private void 설정_변경(String day) {
        VisitDate visitDate = new VisitDate(ChristmasPolicy.EVENT_YEAR, ChristmasPolicy.DECEMBER, day);
        List<SelectMenu> selectMenus = List.of(
                new SelectMenu("티본스테이크", 1),
                new SelectMenu("바비큐립", 1),
                new SelectMenu("초코케이크", 2),
                new SelectMenu("제로콜라", 1),
                new SelectMenu("아이스크림", 3)
        );
        Amount totalAmount = new Amount(157000);
        SelectMenus selectMenuss = new SelectMenus(selectMenus);

        order = new Order(visitDate, selectMenuss, totalAmount);
        LocalDate orderDate = LocalDate.of(ChristmasPolicy.EVENT_YEAR, ChristmasPolicy.DECEMBER, Integer.parseInt(day));

        discountDetails = new DiscountDetails(order, orderDate, selectMenuss);
    }
}
