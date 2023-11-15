package christmas.Menu;

public class MenuDto {
    private String MenuName;
    private int MenuPrice;
    MenuType MenuType;

    public MenuDto(String menueName, int menuePrice, MenuType menuType) {
        MenuName = menueName;
        MenuPrice = menuePrice;
        MenuType = menuType;
    }

    public String getMenuName() {
        return MenuName;
    }

    public int getMenuPrice() {
        return MenuPrice;
    }

    public christmas.Menu.MenuType getMenuType() {
        return MenuType;
    }

}
