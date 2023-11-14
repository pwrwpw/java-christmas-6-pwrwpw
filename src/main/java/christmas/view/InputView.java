package christmas.view;

import static camp.nextstep.edu.missionutils.Console.readLine;

import christmas.exception.domain.visitdate.InvalidDayException;
import christmas.exception.domain.visitdate.InvalidOrderException;
import christmas.utils.FormatValidator;
import christmas.utils.StringValidator;


public class InputView {

    private static final String PROMPT_VISIT_DAY = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String PROMPT_ORDER_MENU = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    public String getExpectedVisitDay() {
        System.out.println(PROMPT_VISIT_DAY);
        String expectedVisitDay = readLine();

        if (FormatValidator.isNotNumber(expectedVisitDay)) {
            throw new InvalidDayException();
        }
        return expectedVisitDay;
    }

    public String getOrderMenu() {
        System.out.println(PROMPT_ORDER_MENU);
        String orderMenu = readLine();

        if (StringValidator.isBlankOrEmpty(orderMenu)) {
            throw new InvalidOrderException();
        }
        return orderMenu;
    }
}

