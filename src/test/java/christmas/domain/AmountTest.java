package christmas.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class AmountTest {

    @Test
    public void 객체_생성_정상_테스트() {
        int value = 10;
        Amount amount = new Amount(value);
        assertNotNull(amount, "Amount 객체는 null이 아니어야 합니다");
        assertEquals(value, amount.value(), "Amount 객체의 값은 초기화된 값과 같아야 합니다");
    }

    @Test
    public void 값_정확성_테스트() {
        int value = 5;
        Amount amount = new Amount(value);
        assertEquals(value, amount.value(), "Amount 객체의 value 메소드 반환 값은 생성자 인자와 일치해야 합니다");
    }
}