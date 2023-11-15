package christmas.Util;

import christmas.Order.OrderDto;

import static christmas.Constant.ValidateConstant.UNCORRECTDATE;
import static christmas.Constant.ValidateConstant.UNCORRECTORDER;

public class Validate {

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

    public static void MenuFormInput(String OrderInput) throws IllegalArgumentException { //
        boolean hasInvalidDelimiter = OrderInput.matches(".*[^\\d,\\-,\\p{IsHangul}].*");
        //한글, 숫자, 쉼표(,), 대시(-)를 제외한 문자 포함시, true 반환
        if(hasInvalidDelimiter || !OrderInput.contains("-")){
            throw new IllegalArgumentException(UNCORRECTORDER+" (메뉴입력 형태가 잘못되었습니다.)\" ");
        }
    }

    public static void MenuNameInput(){
        throw new IllegalArgumentException(UNCORRECTORDER + " (메뉴가 존재하지 않습니다.)\n");
    }

}
