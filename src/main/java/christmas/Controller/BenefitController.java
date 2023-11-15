package christmas.Controller;

import christmas.Discount.Badge;
import christmas.Discount.DiscountService;
import christmas.Discount.WeekType;
import christmas.Gift.GiftService;
import christmas.IO.OutputView;
import christmas.Order.OrderService;

import java.util.List;

import static christmas.Constant.DiscountConstant.EVENTSTANDARD;
import static christmas.Constant.GiftConstant.GIFTPRICE;

public class BenefitController { //사용자의 요청에 대해 필요한 로직 호출 후 요청처리
    private final OrderService orderService;
    private final GiftService giftService;
    private final DiscountService discountService;;
    private int TotalPrice;
    private int TotalDiscount;

    public BenefitController(OrderService orderService, GiftService giftService, DiscountService discountService) {
        this.orderService = orderService;
        this.giftService = giftService;
        this.discountService = discountService;
    }

    public void ShowBenefits(){
        TotalPrice = orderService.getAllPrice();
        boolean IsEvent = TotalPrice >= EVENTSTANDARD; //구매가격이 이벤트 기준 가격보다 작다면, 이벤트는 발생하지 않음
        boolean IsGift = giftService.getGift();


        IsGift(IsGift);
        ShowDiscount(IsEvent);
        ShowGift(IsGift);
        ShowTotalDiscountAndPayMent();

        DetermineBedge(IsEvent);
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

    private void ShowTotalDiscountAndPayMent(){
        OutputView.PrintTotalDiscount(TotalDiscount, TotalPrice);
        OutputView.PrintPayment(TotalPrice,TotalDiscount);
    }

    private void DetermineBedge(boolean isEvent){
        int totalDiscount = TotalDiscount;
        if(isEvent){
            totalDiscount += GIFTPRICE;
        }
        Badge bedge = Badge.getBadge(totalDiscount);
        OutputView.PrintBedge(bedge);
    }

}

