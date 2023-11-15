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

    public void ShowBenefits(){
        boolean IsEvent = TotalPrice >= EVENTSTANDARD; //구매가격이 이벤트 기준 가격보다 작다면, 이벤트는 발생하지 않음
        boolean IsGift = giftService.getGift();


        IsGift(IsGift);
        ShowDiscount(IsEvent);
        //할인혜택에서 증정품이 있는 경우 출력
        ShowGift(IsGift);
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

    private void IsGift(boolean isGift){
        OutputView.PrintGiftMenu(isGift);
    }

    private void ShowDiscount(boolean isEvent){
        if(!isEvent){
            OutputView.PrintNoDiscount();
            return;
        }
        int TotalPrice = orderService.getAllPrice();
        CalculateWeekDiscount(); //주말, 주중 할인계산 함수 필요
        TotalDiscount=discountService.ShowAllDiscount(TotalPrice); //할인 출력
    }

    private void CalculateWeekDiscount(){
        WeekType weekType = discountService.getWeekType();
        List<Integer> DiscountTarget = orderService.FindDiscountOrder(weekType);

        discountService.sumWeekDiscount(DiscountTarget, weekType);
    }

    private void ShowGift(boolean isGift){
        if(isGift){
            OutputView.PrintGiftDiscount();
        }
    }

}

