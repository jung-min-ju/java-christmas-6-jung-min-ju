package christmas.EnumTest;

import christmas.Discount.WeekType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
class WeekTypeTest {
    @Test
    public void 올바른_WeekType_반환() {
        // 주말인 경우 WEEKENDTYPE을 반환하는지 테스트
        Assertions.assertEquals(WeekType.WEEKENDTYPE, WeekType.determineWeekStatus(1));

        // 평일인 경우 WEEKDAYTYPE을 반환하는지 테스트
        Assertions.assertEquals(WeekType.WEEKDAYTYPE, WeekType.determineWeekStatus(3));
    }

}