package christmas.Order;

import christmas.Discount.WeekType;

import java.util.List;

public interface OrderService {
    void createOrder(String Menu);
    List<OrderDto> showOrder();
    int getAllPrice();
    List<Integer> FindDiscountOrder(WeekType weekType);
}
