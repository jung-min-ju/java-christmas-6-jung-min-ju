package christmas.Controller;

import christmas.Constant.MenuConstant;
import christmas.Discount.BenefitDto;
import christmas.Discount.DiscountService;
import christmas.Gift.GiftService;
import christmas.IO.InputView;
import christmas.IO.OutputView;
import christmas.Menu.MenuDto;
import christmas.Order.OrderDto;
import christmas.Order.OrderService;
import christmas.Util.Validate;

import java.util.List;
import java.util.Map;

public class OrderController {
    private final OrderService orderService;
    private final GiftService giftService;
    private final DiscountService discountService;
    private int Date;
    private String Menu;
    private int TotalPrice;
    public OrderController(OrderService orderService, GiftService giftService, DiscountService discountService) {
        this.orderService = orderService;
        this.giftService = giftService;
        this.discountService = discountService;
    }

    public void Reservation(){
        CheckDate();
        CheckMenu();
        Order();
        ShowTodayBenefits();
    }

    public void ShowMenuAndTotalPrice() {
        OutputView.PrintOrderTitle();
        List<OrderDto> orders = orderService.showOrder();
        for(OrderDto order : orders) {
            MenuDto menu = order.getMenu();
            OutputView.PrintOrder(menu.getMenuName(), order.getQuantity());
        }
        TotalPrice = orderService.getAllPrice();
        giftService.CheckGift(TotalPrice);
        OutputView.PrintAllPrice(TotalPrice);
    }

    private void CheckDate(){
        String dateInput = null;
        try {
            dateInput = InputView.AskVisiting();
            Date = Validate.DateInput(dateInput);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            CheckDate();
        }

    }

    private void Order() {
        try {
            orderService.createOrder(Menu);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            CheckMenu();
            Order();
        }
    }

    private void CheckMenu() {
        System.out.println(MenuConstant.MENU);
        try {
            Menu = InputView.AskOrder(); //타파스-1,제로콜라-1
            Validate.MenuFormInput(Menu); //메뉴 입력값 형식 확인
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            CheckMenu();
        }
    }

    private void ShowTodayBenefits(){
        OutputView.PrintTodatDate(Date);
        BenefitDto benefits = discountService.TodayBenefits(Date);

        OutputView.PrintTodayBenefits(benefits);
        OutputView.Null();
    }


}
