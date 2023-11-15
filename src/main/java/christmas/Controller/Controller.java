package christmas.Controller;

import christmas.Constant.MenuConstant;
import christmas.Discount.Badge;
import christmas.Discount.DiscountService;
import christmas.Discount.WeekType;
import christmas.Gift.GiftService;
import christmas.IO.InputView;
import christmas.IO.OutputView;
import christmas.Order.OrderService;
import christmas.Util.Validate;

import java.util.List;

import static christmas.Constant.DiscountConstant.EVENTSTANDARD;
import static christmas.Constant.GiftConstant.GIFTPRICE;

public class Controller { //사용자의 요청에 대해 필요한 로직 호출 후 요청처리
    private final OrderService orderService;
    private final GiftService giftService;
    private final DiscountService discountService;
    private int Date;
    private String Menu;
    private int TotalPrice;
    private int TotalDiscount;

    public Controller(OrderService orderService, GiftService giftService, DiscountService discountService) {
        this.orderService = orderService;
        this.giftService = giftService;
        this.discountService = discountService;
    }
    //Reservatin 함수 구현
    public void Reservation(){
        CheckDate();
        CheckMenu();
        Order();
        ShowTodayBenefits();
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

    private void ShowTodayBenefits(){ //해당 날짜에 받을 수 있는 혜택을 사용자에게 알려주는 함수.
        OutputView.PrintTodatDate(Date);
        discountService.TodayBenefits(Date);
    }


}

