package christmas;

import christmas.Controller.Controller;
import christmas.Discount.DiscountService;
import christmas.Discount.DiscountServiceImpl;
import christmas.Gift.GiftServiceImpl;
import christmas.Gift.GiftService;
import christmas.Menu.MenuRepository;
import christmas.Menu.MenuRepositoryImpl;
import christmas.Order.OrderService;
import christmas.Order.OrderServiceImpl;

public class AppConfig { //의존성 주입 클래스
    private final MenuRepository menuRepository = new MenuRepositoryImpl();
    private final OrderService orderService = new OrderServiceImpl(menuRepository);
    private final GiftService giftService = new GiftServiceImpl();
    public final DiscountService discountService = new DiscountServiceImpl();

    public Controller controller(){
        return new Controller(orderService, giftService, discountService);
    }

}

