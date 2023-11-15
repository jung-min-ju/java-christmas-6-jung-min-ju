package christmas.Util;

import java.text.NumberFormat;

public class Formatter {
    public static String formatPrice(int price) {
        NumberFormat formatter = NumberFormat.getInstance();
        return formatter.format(price);
    }
}
