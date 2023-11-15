package christmas.Menu;

import christmas.Util.ParsingMenu;
import christmas.Util.Validate;
import java.util.ArrayList;
import java.util.List;

import static christmas.Constant.MenuConstant.*;

public class MenuRepositoryImpl implements MenuRepository { //repository(db)는 service와 MenuDto로 소통
    private List<MenuDto> Menu;

    public MenuRepositoryImpl() {
        Menu = new ArrayList<>(); // 메뉴 리스트 초기화 및 생성

        CreateMenu(MAIN, MenuType.MAIN);
        CreateMenu(APITEZER, MenuType.EPITIZER);
        CreateMenu(DRINK, MenuType.DRINK);
        CreateMenu(DESSERT, MenuType.DESSERT);
    }

    private void CreateMenu(String menuConstant, MenuType menuType){
        String[] menuItems = ParsingMenu.CountMenu(menuConstant);
        for (String item : menuItems){
            MenuDto menuDto = ParsingMenu.menuDto(item,menuType);
            Menu.add(menuDto);
        }
    }

    @Override
    public MenuDto FindMenu(String MenuName) {
        for (MenuDto menuDto : Menu) {
            String ObjectName = menuDto.getMenuName();
            if (ObjectName.equals(MenuName)) {
                return menuDto;
            }
        }
        Validate.MenuNameInput();
        return null;
    }

}
