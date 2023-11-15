package christmas.Discount;

import java.util.List;

import static christmas.Constant.DateConstant.CHRISTMAS;
import static christmas.Constant.DiscountConstant.*;
import static christmas.Discount.StarStatus.EXISTING;

public class DiscountServiceImpl implements DiscountService {

    private StarStatus starstatus;
    private WeekType weekType;
    private int ChristDiscount = 0;
    private int StarDiscount = 0;
    private int TotalWeekDiscount=0;

    @Override
    public BenefitDto TodayBenefits(int dateInput){
        starstatus = StarStatus.determineStatus(dateInput);
        weekType =  weekType.determineWeekStatus(dateInput);
        ChristDiscount = ChristmasDiscount(dateInput);
        StarDiscount = StarDiscount(starstatus);

        BenefitDto benefits = new BenefitDto(ChristDiscount,starstatus,weekType);
        return benefits;
    }

    private int ChristmasDiscount(int dateInput){
        if(dateInput>CHRISTMAS){
            return CHRISTMASEVENTEND;
        }
        return CHRISTBASICDISCOUNT + ((dateInput-1)*100);
    }

    private int StarDiscount(StarStatus starstatus){
        if(starstatus==EXISTING){
            return EXCISTINGSTARDISCOUNT;
        }
        return NONESTARDISCOUNT;
    }

    @Override
    public int ReturnTotalDiscount(int totalPrice) {
        int TotalDiscount=0;
        TotalDiscount += StarDiscount;
        TotalDiscount += ChristDiscount;
        TotalDiscount += TotalWeekDiscount; //평일주말할인

        return TotalDiscount;
    }

    public BenefitDto PrintAllDiscounts(){
        return new BenefitDto(ChristDiscount, starstatus, weekType);
    }

    @Override
    public int getTotalWeekDiscount() {
        return TotalWeekDiscount;
    }

    @Override
    public WeekType getWeekType() {
        return weekType;
    }

    @Override
    public void sumWeekDiscount(List<Integer> DiscountTarget, WeekType weekType) {
        int TargetDiscount = weekType.getDiscount();

        for(int TargetQuantity : DiscountTarget){
            TotalWeekDiscount += TargetQuantity*TargetDiscount; //메뉴 1개당 할인을 적용해주는 계산 로직
        }
    }
}
