package christmas.EnumTest;

import christmas.Discount.Badge;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BadgeTest {

    @Test
    public void testGetBadge() {
        // 0보다 크거나 같은 경우 "없음" 반환
        Assertions.assertEquals(Badge.없음, Badge.getBadge(0));
        Assertions.assertEquals(Badge.없음, Badge.getBadge(10));

        // 5000보다 크거나 같은 경우 "STAR" 반환
        Assertions.assertEquals(Badge.별, Badge.getBadge(5000));
        Assertions.assertEquals(Badge.별, Badge.getBadge(5500));

        // 10000보다 크거나 같은 경우 "TREE" 반환
        Assertions.assertEquals(Badge.나무, Badge.getBadge(10000));
        Assertions.assertEquals(Badge.나무, Badge.getBadge(10500));

        // 20000보다 크거나 같은 경우 "SANTA" 반환
        Assertions.assertEquals(Badge.산타, Badge.getBadge(20000));
        Assertions.assertEquals(Badge.산타, Badge.getBadge(30000));
    }
}

