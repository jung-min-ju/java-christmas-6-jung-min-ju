package christmas.Order;

import christmas.Discount.WeekType;
import christmas.IO.OutputView;
import christmas.Menu.MenuDto;
import christmas.Menu.MenuRepository;
import christmas.Menu.MenuType;
import christmas.Util.ParsingMenu;
import christmas.Util.Validate;
import net.bytebuddy.implementation.bytecode.Throw;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static christmas.Menu.MenuType.DRINK;
import static christmas.Util.Validate.CheckOrderValidate;

public class OrderServiceImpl implements OrderService {
    MenuRepository menuRepository;
    private List<OrderDto> Order;
    private Set<String> IsDuplicate;
    private int TotalPrice;
    private int TotalDrink=0;

    public OrderServiceImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository; //순환의존이 되지 않도록 주의

        Order = new ArrayList<>();
        IsDuplicate = new HashSet<>();
        TotalPrice = 0;
    }

    @Override
    public void createOrder(String OrderInput) { //OrderInput 예시) 타파스-1,제로콜라-1
        String[] orderItems = ParsingMenu.CountMenu(OrderInput);
        for (String item : orderItems) {
            OrderDto orderDto = CreateOrderDto(item);

            Validate.isQualityValid(orderDto);
            sumTotalPrice(orderDto);
            Order.add(orderDto);
        }
        CheckOrderValidate(TotalDrink, IsDuplicate.size(), Order.size()); //메뉴를 중복입력함
    }

    private OrderDto CreateOrderDto(String item){
        Object[] parsedItem = ParsingMenu.parseItem(item);
        String MenuName = (String) parsedItem[0];
        int MenuQuantity = (int) parsedItem[1];
        MenuDto ExistsMenu = menuRepository.FindMenu(MenuName); // 메뉴가 없으면 여기서 예외가 발생합니다.
        if(ExistsMenu!=null){
            IsDuplicate.add(MenuName); //메뉴중복 검사 위함.
            CountDrink(ExistsMenu);
        }
        return new OrderDto(ExistsMenu,MenuQuantity); //구분자를 분리한 후, 해당 정보를 담은 OrdeDto 객체 반환
    }


    @Override
    public void showOrder() {
        OutputView.PrintOrderTitle();
        for(OrderDto orderDto : Order){
            MenuDto menuDto = orderDto.getMenu();
            OutputView.PrintOrder(menuDto.getMenuName(),orderDto.getQuantity());
        }
        OutputView.Null();
    }


    private void sumTotalPrice(OrderDto orderDto) {
        MenuDto menuDto = orderDto.getMenu();
        TotalPrice+= (menuDto.getMenuPrice() * orderDto.getQuantity());
    }

    @Override
    public int getAllPrice() {
        return TotalPrice;
    }


    private void CountDrink(MenuDto menuDto){
        MenuType menuType = menuDto.getMenuType();
        if(menuType==DRINK){
            TotalDrink++;
        }
    }

}
