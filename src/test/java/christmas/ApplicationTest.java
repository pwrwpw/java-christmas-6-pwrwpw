package christmas;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

class ApplicationTest extends NsTest {
    private static final String LINE_SEPARATOR = System.lineSeparator();

    @Test
    void 모든_타이틀_출력() {
        assertSimpleTest(() -> {
            run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains(
                "<주문 메뉴>",
                "<할인 전 총주문 금액>",
                "<증정 메뉴>",
                "<혜택 내역>",
                "<총혜택 금액>",
                "<할인 후 예상 결제 금액>",
                "<12월 이벤트 배지>"
            );
        });
    }

    @Test
    void 혜택_내역_없음_출력() {
        assertSimpleTest(() -> {
            run("26", "타파스-1,제로콜라-1");
            assertThat(output()).contains("<혜택 내역>" + LINE_SEPARATOR + "없음");
        });
    }

    @Test
    void 정상_주문_시나리오_테스트() {
        run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");

        assertThat(output()).contains(
                "안녕하세요! 우테코 식당 12월 이벤트 플래너 입니다",
                "<주문시 이벤트 주의사항>",
                "총주문 금액 10,000원 이상부터 이벤트가 적용됩니다.",
                "음료만 주문 시, 주문할 수 없습니다.",
                "메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.",
                "(e.g. 시저샐러드-1, 티본스테이크-1, 크리스마스파스타-1, 제로콜라-3, 아이스크림-1의 총개수는 7개)",
                "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)",
                "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1",
                "12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!",
                "<주문 메뉴>",
                "티본스테이크 1개",
                "바비큐립 1개",
                "초코케이크 2개",
                "제로콜라 1개",
                "<할인 전 총주문 금액>",
                "142000원",
                "<증정 메뉴>",
                "샴페인 1개",
                "<혜택 내역>",
                "크리스마스 디데이 할인: -1200원",
                "주말 할인: -4046원",
                "특별 할인: -1000원",
                "선물 메뉴 가격: -25000원",
                "<총혜택 금액>",
                "-31246원",
                "<할인 후 예상 결제 금액>",
                "135754원",
                "<12월 이벤트 배지>",
                "산타"
        );
    }

    @Test
    void 유효하지_않은_날짜_입력_테스트() {
        runException("32"); // 12월에는 31일까지만 존재
        assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @Test
    void 유효하지_않은_메뉴_입력_테스트() {
        runException("3", "존재하지않는메뉴-1");
        assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
