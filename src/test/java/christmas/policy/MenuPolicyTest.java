package christmas;

import christmas.policy.MenuPolicy;
import christmas.exception.domain.visitdate.InvalidOrderException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class MenuPolicyTest {

    @ParameterizedTest
    @ValueSource(strings = {"티본스테이크", "바비큐립", "초코케이크"})
    void 메뉴이름_유효성_검사_정상(String menuName) {
        assertFalse(MenuPolicy.isNotValidMenuName(menuName));
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "b", "a"})
    void 메뉴이름_유효성_검사_비정상(String menuName) {
        assertTrue(MenuPolicy.isNotValidMenuName(menuName));
    }

    @ParameterizedTest
    @CsvSource({
            "티본스테이크, MAIN",
            "아이스크림, DESSERT",
            "제로콜라, DRINK"
    })
    void 메뉴_카테고리_확인(String menuName, MenuPolicy category) {
        assertTrue(MenuPolicy.isMenuItemInCategory(menuName, category));
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "b"})
    void 메뉴_가격_조회_실패(String menuName) {
        assertThrows(InvalidOrderException.class, () -> MenuPolicy.getMenuPrice(menuName));
    }

    @ParameterizedTest
    @CsvSource({
            "1, false",
            "0, true",
            "-1, true",
            "100, true"
    })
    void 메뉴_수량_유효성_검사(int menuCount, boolean expected) {
        assertEquals(expected, MenuPolicy.isNotValidMenuCount(menuCount));
    }
}
