package christmas.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringValidatorTest {

    @Test
    void 비어있는_문자열_테스트() {
        assertTrue(StringValidator.isBlankOrEmpty(""));
    }

    @Test
    void 공백만_포함된_문자열_테스트() {
        assertTrue(StringValidator.isBlankOrEmpty(" "));
    }

    @Test
    void 공백과_문자가_포함된_문자열_테스트() {
        assertFalse(StringValidator.isBlankOrEmpty(" 테스트 "));
    }

    @Test
    void 문자만_포함된_문자열_테스트() {
        assertFalse(StringValidator.isBlankOrEmpty("테스트"));
    }
}
