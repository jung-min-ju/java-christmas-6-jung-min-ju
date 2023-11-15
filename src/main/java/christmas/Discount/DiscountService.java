package christmas.Discount;

import java.util.List;
import java.util.Map;

public interface DiscountService {
    int ReturnTotalDiscount(int totalPrice);
    BenefitDto TodayBenefits(int date);
    WeekType getWeekType();
    void sumWeekDiscount(List<Integer> DiscountTarget, WeekType weekType);
    BenefitDto PrintAllDiscounts();
    int getTotalWeekDiscount();
}
