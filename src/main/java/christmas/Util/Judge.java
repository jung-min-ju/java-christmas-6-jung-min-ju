package christmas.Util;

import christmas.Constant.DateConstant;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static christmas.Constant.DateConstant.*;
import static christmas.Constant.DiscountConstant.NONESTARDAY;
import static christmas.Constant.DiscountConstant.STARDAY;
import static java.time.DayOfWeek.*;

public class Judge {
    public static String ReturnJudgeWeek(int DateInput){
        int Year = DateConstant.YEAR;
        int Month = DateConstant.MONTH;

        LocalDate date = LocalDate.of(Year, Month, DateInput);
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        if(dayOfWeek == FRIDAY || dayOfWeek == SATURDAY){
            return WEEKEND;
        }
        return WEEKDAY;
    }

    public static boolean ReturnJudgeStar(int DateInput){
        int Year = DateConstant.YEAR;
        int Month = DateConstant.MONTH;

        LocalDate date = LocalDate.of(Year, Month, DateInput);
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        if(dayOfWeek == SUNDAY || DateInput == CHRISTMAS){
            return STARDAY;
        }
        return NONESTARDAY;
    }

}
