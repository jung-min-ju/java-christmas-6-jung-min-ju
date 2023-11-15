package christmas.Order;

import christmas.Menu.MenuDto;
public class OrderDto {
    private MenuDto menu;
    private int quantity;

    public OrderDto(MenuDto menu, int quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }

    public MenuDto getMenu() {
        return menu;
    }

    public int getQuantity() {
        return quantity;
    }

}
