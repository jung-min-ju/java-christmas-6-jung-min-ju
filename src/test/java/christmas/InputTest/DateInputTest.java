package christmas.InputTest;

import christmas.Util.Validate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DateInputTest {
    @Test
    void 올바른_날짜입력() {
        assertEquals(15, Validate.DateInput("15"));
    }

    @Test
    void 잘못된_입력_문자열() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Validate.DateInput("a"));
        String expectedMessage = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요. (숫자가 아닙니다.)";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void 잘못된_입력_달일범위초과() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Validate.DateInput("32"));
        String expectedMessage = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요. (1~31까지의 숫자만 넣어주세요.)";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void 잘못된_입력_달일범위미만() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Validate.DateInput("-1"));
        String expectedMessage = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요. (1~31까지의 숫자만 넣어주세요.)";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }
}
