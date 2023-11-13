package christmas.utils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class FormatValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "19"})
    void 숫자_형식_올바른_입력_테스트(String input) {
        assertFalse(FormatValidator.isNotNumber(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"abc", "123abc", "abc123", "-123", "123.45", " ", ""})
    void 숫자_형식_잘못된_입력_테스트(String input) {
        assertTrue(FormatValidator.isNotNumber(input));
    }
}
