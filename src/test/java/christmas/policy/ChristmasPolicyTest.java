package christmas.policy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class ChristmasPolicyTest {

    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    void 별점_일자_판별_정상(int day) {
        assertTrue(ChristmasPolicy.isStarDay(day));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 4, 5, 11, 12, 13, 14, 15, 16, 18, 19, 20, 21, 22, 23, 26, 27, 28, 29, 30})
    void 별점_일자_판별_비정상(int day) {
        assertFalse(ChristmasPolicy.isStarDay(day));
    }
}
