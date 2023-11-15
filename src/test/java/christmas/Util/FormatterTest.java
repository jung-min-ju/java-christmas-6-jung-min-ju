package christmas.Util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.NumberFormat;

class FormatterTest {

    @Test
    public void testFormatPrice() {
        int price = 123456;
        String expected = NumberFormat.getInstance().format(price);
        String actual = Formatter.formatPrice(price);

        Assertions.assertEquals(expected, actual);
    }

}