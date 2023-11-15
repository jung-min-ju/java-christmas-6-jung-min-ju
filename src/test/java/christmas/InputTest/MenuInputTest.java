package christmas.InputTest;

import christmas.Menu.MenuDto;
import christmas.Menu.MenuRepositoryImpl;
import christmas.Order.OrderDto;
import org.junit.jupiter.api.Test;
import christmas.Util.Validate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static christmas.Menu.MenuType.MAIN;
import static org.junit.jupiter.api.Assertions.*;

public class MenuInputTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void 올바른_메뉴입력() {
        assertDoesNotThrow(() -> Validate.MenuFormInput("티본스테이크-55000,제로콜라-3000"));
    }

    @Test
    void 잘못된_구분자사용1() {
        assertThrows(IllegalArgumentException.class, () -> Validate.MenuFormInput("티본스테이크+제로콜라"));
    }

    @Test
    void 잘못된_구분자사용2() {
        assertThrows(IllegalArgumentException.class, () -> Validate.MenuFormInput("티본스테이크-2+제로콜라-1"));
    }

    @Test
    void 입력된_메뉴_저장소에서_찾기성공() {
        MenuRepositoryImpl repository = new MenuRepositoryImpl();
        assertDoesNotThrow(() -> repository.FindMenu("티본스테이크"));
    }

    @Test
    void 입력된_메뉴_저장소에서_찾기실패() {
        MenuRepositoryImpl repository = new MenuRepositoryImpl();
        assertThrows(IllegalArgumentException.class, () -> repository.FindMenu("존재하지 않는 메뉴"));
    }

    @Test
    void 잘못된_메뉴수량_입력() {
        MenuDto menuDto = new MenuDto("티본스테이크",55000,MAIN);
        OrderDto orderDto = new OrderDto(menuDto, 0);
        assertThrows(IllegalArgumentException.class, () -> Validate.isQualityValid(orderDto));
    }

    @Test
    void 메뉴_중복입력_주문불가() {
        assertThrows(IllegalArgumentException.class, () -> Validate.CheckOrderValidate(1, 2, 1));
    }

    @Test
    void 음료수만_주문시_주문불가() {
        assertThrows(IllegalArgumentException.class, () -> Validate.CheckOrderValidate(1, 1, 1));
    }

    @Test
    void 최대메뉴_20개까지_주문가능() {
        assertThrows(IllegalArgumentException.class, () -> Validate.CheckOrderValidate(1, 21, 21));
    }



}
