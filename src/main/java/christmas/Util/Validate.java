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

    public static void isQualityValid(OrderDto orderDto) {
        int quantity = orderDto.getQuantity();
        if(quantity <= 0) {
            throw new IllegalArgumentException(UNCORRECTORDER + " (메뉴의 수량이 유효하지 않습니다.)");
        }
    }

    public static void CheckOrderValidate(int TotalDrink, int orderSize, int duplicateSize){
        if(!(duplicateSize == orderSize)){
            throw new IllegalArgumentException(UNCORRECTORDER + " (메뉴를 중복해서 입력할 수 없습니다.)");
        }
        if(TotalDrink == orderSize){
            throw new IllegalArgumentException(UNCORRECTORDER + " (음료수만 주문할 수 없습니다.)");
        }
        if(orderSize > 20){
            throw new IllegalArgumentException(UNCORRECTORDER + " (메뉴는 최대 20개만 주문 가능합니다.)");
        }
    }
}
