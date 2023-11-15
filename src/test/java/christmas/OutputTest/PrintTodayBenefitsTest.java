package christmas.OutputTest;

import christmas.IO.OutputView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static christmas.Constant.DateConstant.CHRISTMAS;
import static christmas.Constant.DiscountConstant.*;
import static christmas.Discount.StarStatus.EXISTING;
import static christmas.Discount.StarStatus.NONE;
import static christmas.Discount.WeekType.WEEKDAYTYPE;
import static christmas.Discount.WeekType.WEEKENDTYPE;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PrintTodayBenefitsTest {

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

    private int ChristmasDiscount(int dateInput){
        if(dateInput>CHRISTMAS){
            return CHRISTMASEVENTEND;
        }
        return CHRISTBASICDISCOUNT + ((dateInput-1)*100);
    }

    @Test
    void 크리스마스이벤트0_별0_평일() {
        int ChristDiscount = ChristmasDiscount(24); //크리스마스 이전. (크리스마스이벤트 적용됨)
        OutputView.PrintTodayBenefits(ChristDiscount, EXISTING, WEEKDAYTYPE);
        assertTrue(outputStreamCaptor.toString()
                .trim().contains("* 크리스마스 혜택 : 3,300원 할인\r\n" +
                        "* 별이 있는 날! : 1,000원 할인\r\n" +
                        "* 평일 할인. 디저트 메뉴 1개당 할인이 적용됩니다.(2,023원이 각 메뉴당 할인)"));
    }

    @Test
    void 크리스마스이벤트X_별X_주말() {
        int ChristDiscount = ChristmasDiscount(29); //크리스마스 이전. (크리스마스이벤트 적용됨)
        OutputView.PrintTodayBenefits(ChristDiscount, NONE, WEEKENDTYPE);
        assertTrue(outputStreamCaptor.toString()
                .trim().contains("* 주말 할인. 메인 메뉴 1개당 할인이 적용됩니다.(2,023원이 각 메뉴당 할인)"));
    }




}