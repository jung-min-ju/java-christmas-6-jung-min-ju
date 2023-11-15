package christmas.Controller;

import christmas.Constant.MenuConstant;
import christmas.Discount.DiscountService;
import christmas.Gift.GiftService;
import christmas.IO.InputView;
import christmas.IO.OutputView;
import christmas.Order.OrderService;
import christmas.Util.Validate;

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

    public void ShowMenuAndTotalPrice(){ //메뉴 및 전체 가격 출력
        orderService.showOrder();
        TotalPrice = orderService.getAllPrice();
        giftService.CheckGift(TotalPrice); //증정품 줘야하는지 확인
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

    private void ShowTodayBenefits(){ //해당 날짜에 받을 수 있는 혜택을 사용자에게 알려주는 함수. 직접 추가한 기능임
        OutputView.PrintTodatDate(Date);
        discountService.TodayBenefits(Date);
    }


}
