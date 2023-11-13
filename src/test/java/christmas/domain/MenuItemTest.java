package christmas.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class MenuItemTest {

    @Test
    void 생성자_테스트() {
        MenuItem item = new MenuItem("초콜릿", 1000);
        assertNotNull(item);
    }

    @Test
    void 필드_접근_테스트() {
        MenuItem item = new MenuItem("초콜릿", 1000);
        assertEquals("초콜릿", item.name());
        assertEquals(1000, item.price());
    }

    @Test
    void 동등성_테스트() {
        MenuItem item1 = new MenuItem("초콜릿", 1000);
        MenuItem item2 = new MenuItem("초콜릿", 1000);
        assertEquals(item1, item2);
    }

    @Test
    void 해시코드_테스트() {
        MenuItem item1 = new MenuItem("초콜릿", 1000);
        MenuItem item2 = new MenuItem("초콜릿", 1000);
        assertEquals(item1.hashCode(), item2.hashCode());
    }
}
