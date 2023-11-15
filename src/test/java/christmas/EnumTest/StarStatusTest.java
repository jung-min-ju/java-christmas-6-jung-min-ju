package christmas.EnumTest;

import christmas.Discount.StarStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static christmas.Discount.StarStatus.EXISTING;
import static christmas.Discount.StarStatus.NONE;

class StarStatusTest {
    @Test
    public void testDetermineStatus() {
        // starday인 경우 EXISTING을 반환하는지 테스트
        Assertions.assertEquals(EXISTING, StarStatus.determineStatus(3));

        // starday가 아닌 경우 NONE을 반환하는지 테스트
        Assertions.assertEquals(NONE, StarStatus.determineStatus(4));
    }
}

