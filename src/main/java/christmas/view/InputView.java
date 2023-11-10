package christmas.view;

import static camp.nextstep.edu.missionutils.Console.readLine;

import christmas.exception.domain.visitdate.FormatDayException;
import christmas.exception.domain.visitdate.InvalidOrderException;
import christmas.utils.FormatValidator;
import christmas.utils.StringValidator;


public class InputView {

    public String getExpectedVisitDay() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String expectedVisitDay = readLine();

        if (FormatValidator.isValidDayFormat(expectedVisitDay)) {
            throw new FormatDayException();
        }
        return expectedVisitDay;
    }

    public String getOrderMenu() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1");
        String orderMenu = readLine();

        if (StringValidator.isBlankOrEmpty(orderMenu)) {
            throw new InvalidOrderException();
        }
        return readLine();
    }
}
