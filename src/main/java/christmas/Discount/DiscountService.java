package christmas.Discount;

import java.util.List;

public interface DiscountService {
    int ShowAllDiscount(int totalPrice);
    void TodayBenefits(int date);
    WeekType getWeekType();
    void sumWeekDiscount(List<Integer> DiscountTarget, WeekType weekType);

}
