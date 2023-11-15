package christmas.Util;

import christmas.Menu.MenuDto;
import christmas.Menu.MenuType;

public class ParsingMenu {

    public static String[] CountMenu(String menu){ //ex) 티본스테이크-55000,바비큐립-54000.. 문자열들을 콤마(,)를 기준으로 분리함
        String Menu = menu;
        String[] items = Menu.split(",");
        return items;
    }

    public static Object[] parseItem(String item) { //ex) 티본스테이크-55000 문자열 을 대시(-)를 기준으로 분리함.
        String[] parts = item.split("-");
        String name = parts[0];
        int count = Integer.parseInt(parts[1]); //해당 함수를 ParsingMenu.menuDto 에서 사용 시 price를 의미, OrderService에서 사용 시 quantity를 의미
        return new Object[]{name, count};
    }

    public static MenuDto menuDto(String item, MenuType menuType){ //item 예시 : 타파스-35000(가격) / parsedItem[1]은 price를 의미함
        Object[] parsedItem = parseItem(item);

        String menuName = (String)parsedItem[0];
        int menuPrice = (Integer)parsedItem[1];

        return new MenuDto(menuName,menuPrice,menuType);
    }

}
