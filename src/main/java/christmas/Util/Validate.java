package christmas.Util;

import christmas.Order.OrderDto;

import static christmas.Constant.ValidateConstant.UNCORRECTDATE;
import static christmas.Constant.ValidateConstant.UNCORRECTORDER;

public class Validate {

    //DateInput 구현
    public static int DateInput(String Date) throws IllegalArgumentException {
        int date;
        try{
            date = Integer.parseInt(Date);
        }catch (NumberFormatException e){
            throw new IllegalArgumentException(UNCORRECTDATE + " (숫자가 아닙니다.)");
        }
        if(date<=0 || date>31) {
            throw new IllegalArgumentException(UNCORRECTDATE + " (1~31까지의 숫자만 넣어주세요.)");
        }
        return date;
    }

}
