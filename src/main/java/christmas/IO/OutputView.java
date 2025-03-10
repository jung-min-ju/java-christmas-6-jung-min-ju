package christmas.IO;

import christmas.Discount.BenefitDto;
import christmas.Discount.StarStatus;
import christmas.Discount.WeekType;

import java.util.List;
import java.util.Map;

import static christmas.Constant.DateConstant.MONTH;
import static christmas.Constant.DiscountConstant.*;
import static christmas.Constant.GiftConstant.*;
import static christmas.Discount.StarStatus.EXISTING;
import static christmas.Discount.StarStatus.NONE;
import static christmas.Util.Formatter.formatPrice;
import static christmas.Util.ParsingWeekDescription.parseDescription;

public class OutputView {

    public static void Null(){
        System.out.println("");
    }
    public static void AskVisiting(){
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.\n" +
                "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
    }

    public static void AskOrder(){
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
    }

    public static void PrintTodatDate(int date){
        System.out.println(MONTH+"월 "+date+"일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }
    public static void PrintTodayBenefits(BenefitDto benefits){
        int ChristDiscount = benefits.getChristDiscount();
        StarStatus starStatus = benefits.getStarStatus();
        WeekType weekType = benefits.getWeekType();

        if(ChristDiscount != CHRISTMASEVENTEND){
            System.out.println("* 크리스마스 혜택 : "+ formatPrice(ChristDiscount) +"원 할인");
        }
        if(starStatus != NONE){
            System.out.println("* 별이 있는 날! : " + formatPrice(EXCISTINGSTARDISCOUNT) +"원 할인");
        }
        System.out.println("* "+ weekType.getDescription()+"("+ formatPrice(weekType.getDiscount()) +"원이 각 메뉴당 할인)");
    }

    public static void PrintOrderTitle(){
        System.out.println("<주문 메뉴>");
    }

    public static void PrintOrder(String MenuName, int Quantity){
        System.out.println(MenuName+" "+Quantity+"개");
    }

    public static void PrintAllPrice(int price){
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(formatPrice(price) + "원\n");
    }

    public static void PrintGiftMenu(boolean isGift){
        System.out.println("<증정 메뉴>");
        if(isGift){
            System.out.println(GIFTNAME+" "+GIFTQUANTITY+"개"+"\n");
            return;
        }
        System.out.println("없음"+"\n");
    }

    public static void PrintNoDiscount(){
        System.out.println("<혜택 내역>");
        System.out.println("없음\n");
    }

    public static void PrintYesDiscount(){
        System.out.println("<혜택 내역>");
    }

    public static void PrintAllDiscounts(BenefitDto benefit, int totalWeekDiscount){
        printChristmasDiscount(benefit.getChristDiscount());
        printSpecialDiscount(benefit.getStarStatus());
        printWeekDiscount(benefit.getWeekType(),totalWeekDiscount);
    }

    private static void printChristmasDiscount(int christDiscount) {
        if (christDiscount != CHRISTMASEVENTEND) {
            System.out.println("크리스마스 디데이 할인: -" + formatPrice(christDiscount) + "원");
        }
    }

    private static void printSpecialDiscount(StarStatus starStatus) {
        if (starStatus == EXISTING) {
            System.out.println("특별 할인: -" + formatPrice(EXCISTINGSTARDISCOUNT) + "원");
        }
    }

    public static void printWeekDiscount(WeekType weekType, int weekTotalDiscount) {
        String description = parseDescription(weekType.getDescription());
        String WeekPrice = formatPrice(weekTotalDiscount);
        System.out.print(description+": ");
        if(!WeekPrice.equals("0")){
            System.out.print("-");
        }
        System.out.println(WeekPrice+"원");
    }

    public static void PrintGiftDiscount(){
        System.out.println("증정 이벤트: " + "-" +formatPrice(GIFTPRICE)+"원\n");
    }

    public static void PrintTotalDiscount(int totalCount, int totalPrice){
        System.out.println("<총혜택 금액>");
        if(totalPrice>=GIFTSTANDARD){
            totalCount+=GIFTPRICE;
        }
        if(totalCount!=0){
            System.out.print("-");
        }
        System.out.println(formatPrice(totalCount)+"원\n");
    }

    public static void PrintPayment(int totalPrice, int totalCount){
        int AfterDiscount = totalPrice - totalCount;
        System.out.println("<할인 후 예상 결제 금액>\n" + formatPrice(AfterDiscount)+"원\n");
    }

    public static <Bedge> void PrintBedge(Bedge bedge) {
        System.out.println("<12월 이벤트 배지>\n" + bedge);
    }
}
