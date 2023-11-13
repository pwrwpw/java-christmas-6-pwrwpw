package christmas.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import christmas.exception.domain.visitdate.InvalidOrderException;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ParserTest {

    @Test
    void 유효한_입력에_대한_정수_변환_테스트() {
        assertEquals(5, Parser.parseInteger("5"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"abc", "4.5", "-3", ""})
    void 잘못된_입력에_대한_예외_발생_테스트(String input) {
        assertThrows(InvalidOrderException.class, () -> Parser.parseInteger(input));
    }

    @Test
    void 단일_항목_문자열_분리_테스트() {
        List<String> result = Parser.splitMenuItems("singleItem");
        assertEquals(1, result.size());
    }

    @Test
    void 빈_항목_포함_문자열_분리_테스트() {
        List<String> result = Parser.splitMenuItems("item1,,item3");
        assertEquals(3, result.size());
    }

    @Test
    void 공백_문자열_분리_테스트() {
        List<String> result = Parser.splitMenuItems(" ");
        assertEquals(1, result.size());
    }
}