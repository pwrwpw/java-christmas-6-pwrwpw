package christmas.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import christmas.exception.domain.visitdate.InvalidOrderException;
import christmas.utils.Parser;
import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SelectMenusTest {

    @ParameterizedTest
    @CsvSource({"'티본스테이크-1, 바비큐립-2'", "'티본스테이크-1, 바비큐립-2","'티본스테이크-1,초코케이크-1,제로콜라-1'"})
    void 정상_테스트(String menuItemsString) {
        List<String> parsedMenuItems = Parser.splitMenuItems(menuItemsString);
        SelectMenus menuItems = SelectMenus.from(parsedMenuItems);
        assertDoesNotThrow(() -> new SelectMenus(menuItems.items()));
    }

    @ParameterizedTest
    @CsvSource({"'티본스테이크-1, 티본스테이크-2'", "'바비큐립-1, 바비큐립-2","'초코케이크-1,초코케이크-1,제로콜라-1'"})
    void 중복_메뉴_테스트(String menuItemsString) {
        List<String> parsedMenuItems = Parser.splitMenuItems(menuItemsString);
        assertThrows(InvalidOrderException.class, () -> SelectMenus.from(parsedMenuItems));
    }

    @ParameterizedTest
    @CsvSource({"'티본스테이크-a, 바비큐립-2'", "'티본스테이크-1, 바비큐립-b","'티본스테이크-1,초코케이크-1,제로콜라-c'"})
    void 잘못된_입력_수량_에_문자_테스트(String menuItemsString) {
        List<String> parsedMenuItems = Parser.splitMenuItems(menuItemsString);
        assertThrows(InvalidOrderException.class, () -> SelectMenus.from(parsedMenuItems));
    }

    @ParameterizedTest
    @CsvSource({"' '", "' '", "' '"})
    void 잘못된_입력_공백_테스트(String menuItemsString) {
        List<String> parsedMenuItems = Parser.splitMenuItems(menuItemsString);
        assertThrows(InvalidOrderException.class, () -> SelectMenus.from(parsedMenuItems));
    }
}
