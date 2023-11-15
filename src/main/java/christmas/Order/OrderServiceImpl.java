package christmas.Order;

import christmas.Discount.WeekType;
import christmas.Menu.MenuDto;
import christmas.Menu.MenuRepository;
import christmas.Menu.MenuType;
import christmas.Util.ParsingMenu;
import christmas.Util.Validate;

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
    public List<OrderDto> showOrder() {
        return Order;
    }

    private void sumTotalPrice(OrderDto orderDto) {
        MenuDto menuDto = orderDto.getMenu();
        TotalPrice+= (menuDto.getMenuPrice() * orderDto.getQuantity());
    }

    @Override
    public int getAllPrice() {
        return TotalPrice;
    }

    @Override
    public List<Integer> FindDiscountOrder(WeekType weekType) { //평일인지 주말인지 나타내는 weekType 변수 외부에서 받아옴
        List<Integer> DiscountTargetQuantity = new ArrayList<>();
        for (OrderDto orderDto : Order) {
            MenuDto menuDto = orderDto.getMenu();
            String Target = weekType.getTarget(); //평일or주말에 대한 할인메뉴가 담긴 weekType.getTarget()의 반환값을 Target 변수에 저장
            String menuType = String.valueOf(menuDto.getMenuType());

            if(Target == menuType){ //order 정보들을 순환하며 찾던 Target과 메뉴타입이 맞다면
                DiscountTargetQuantity.add(orderDto.getQuantity()); //해당 order 객체의 quqntity를 DiscountTargetQuantity 리스트에 저장
            }
        }
        return DiscountTargetQuantity;
    }

    private void CountDrink(MenuDto menuDto){
        MenuType menuType = menuDto.getMenuType();
        if(menuType==DRINK){
            TotalDrink++;
        }
    }

}
