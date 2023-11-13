package christmas.domain;

import christmas.exception.domain.visitdate.InvalidOrderException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SelectMenuTest {

    @ParameterizedTest
    @ValueSource(strings = {"티본스테이크", "바비큐립", "초코케이크", "제로콜라", "아이스크림"})
    void 메뉴_이름_테스트(String menuName) {
        assertDoesNotThrow(() -> new SelectMenu(menuName, 1));
    }

    @ParameterizedTest
    @ValueSource(ints = {1,3,5,7,9,11,13,15,17,19})
    void 메뉴_수량_테스트(int menuCount) {
        assertDoesNotThrow(() -> new SelectMenu("티본스테이크", menuCount));
    }

    @ParameterizedTest
    @ValueSource(strings = {"잘못된메뉴1", "잘못된메뉴2", "InvalidMenu"})
    void 유효하지_않은_메뉴_이름_테스트(String invalidMenuName) {
        assertThrows(InvalidOrderException.class, () -> new SelectMenu(invalidMenuName, 1));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -2, -3, -4, -5})
    void 유효하지_않은_메뉴_수량_음수_테스트(int invalidMenuCount) {
        assertThrows(InvalidOrderException.class, () -> new SelectMenu("티본스테이크", invalidMenuCount));
    }

    @ParameterizedTest
    @ValueSource(ints = {21, 22, 23, 24, 25, 26, 27, 28, 29, 30})
    void 유효하지_않은_메뉴_수량_초과_테스트(int invalidMenuCount) {
        assertThrows(InvalidOrderException.class, () -> new SelectMenu("티본스테이크", invalidMenuCount));
    }

    @ParameterizedTest
    @CsvSource({"티본스테이크, 1", "바비큐립, 1", "초코케이크, 2", "제로콜라, 1", "아이스크림, 3"})
    void 메뉴_이름_수량_테스트(String menuName, int menuCount) {
        assertDoesNotThrow(() -> new SelectMenu(menuName, menuCount));
    }

    @ParameterizedTest
    @CsvSource({"잘못된메뉴1, 1", "잘못된메뉴2, 1", "InvalidMenu, 2"})
    void 유효하지_않은_메뉴_이름_유효한_수량_테스트(String invalidMenuName, int menuCount) {
        assertThrows(InvalidOrderException.class, () -> new SelectMenu(invalidMenuName, menuCount));
    }

    @ParameterizedTest
    @CsvSource({"티본스테이크, 0", "바비큐립, -1", "초코케이크, -2", "제로콜라, -3", "아이스크림, -4"})
    void 유효한_메뉴_수량_음수_테스트(String menuName, int invalidMenuCount) {
        assertThrows(InvalidOrderException.class, () -> new SelectMenu(menuName, invalidMenuCount));
    }

    @ParameterizedTest
    @CsvSource({"티본스테이크, 21", "바비큐립, 22", "초코케이크, 23", "제로콜라, 24", "아이스크림, 25"})
    void 유효한_메뉴_수량_초과_테스트(String menuName, int invalidMenuCount) {
        assertThrows(InvalidOrderException.class, () -> new SelectMenu(menuName, invalidMenuCount));
    }

    @ParameterizedTest
    @CsvSource({"잘못된메뉴1, 0", "잘못된메뉴2, -1", "InvalidMenu, -2"})
    void 유효하지_않은_메뉴_이름_유효하지_않은_수량_테스트(String invalidMenuName, int invalidMenuCount) {
        assertThrows(InvalidOrderException.class, () -> new SelectMenu(invalidMenuName, invalidMenuCount));
    }

    @Test
    void 동일한_메뉴_객체_테스트() {
        SelectMenu menu1 = new SelectMenu("티본스테이크", 1);
        SelectMenu menu2 = new SelectMenu("티본스테이크", 1);

        assertEquals(menu1, menu2, "동일한 메뉴 이름과 수량을 가진 객체는 같아야 합니다.");
    }

    @Test
    void 다른_메뉴_이름_테스트() {
        SelectMenu menu1 = new SelectMenu("티본스테이크", 1);
        SelectMenu menu2 = new SelectMenu("바비큐립", 1);

        assertNotEquals(menu1, menu2, "다른 메뉴 이름을 가진 객체는 달라야 합니다.");
    }

    @Test
    void 다른_메뉴_수량_테스트() {
        SelectMenu menu1 = new SelectMenu("티본스테이크", 1);
        SelectMenu menu2 = new SelectMenu("티본스테이크", 2);

        assertNotEquals(menu1, menu2, "다른 메뉴 수량을 가진 객체는 달라야 합니다.");
    }

    @Test
    void 동일_객체_테스트() {
        SelectMenu menu1 = new SelectMenu("티본스테이크", 1);

        assertEquals(menu1, menu1, "동일한 객체는 같아야 합니다.");
    }

    @Test
    void null과_비교_테스트() {
        SelectMenu menu1 = new SelectMenu("티본스테이크", 1);

        assertNotEquals(menu1, null, "null과 비교 시 객체는 다르다고 평가되어야 합니다.");
    }

    @Test
    void 다른_클래스_객체와_비교_테스트() {
        SelectMenu menu1 = new SelectMenu("티본스테이크", 1);
        String differentObject = "티본스테이크";

        assertNotEquals(menu1, differentObject, "다른 클래스의 객체와 비교 시 달라야 합니다.");
    }
}
