package christmas.OutputTest;

import christmas.Discount.BenefitDto;
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
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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

        BenefitDto benefits = new BenefitDto(3300, EXISTING, WEEKDAYTYPE); //3300은 사용자 선택 날짜가 24일때의 크리스마스 이벤트 할인가
        OutputView.PrintTodayBenefits(benefits);
        String expectedOutput = "* 크리스마스 혜택 : 3,300원 할인\r\n" +
                "* 별이 있는 날! : 1,000원 할인\r\n" +
                "* 평일 할인. 디저트 메뉴 1개당 할인이 적용됩니다.(2,023원이 각 메뉴당 할인)";
        assertThat(outputStreamCaptor.toString().trim()).contains(expectedOutput);
    }

    @Test
    void 크리스마스이벤트X_별X_주말() {
        BenefitDto benefits = new BenefitDto(0, NONE, WEEKENDTYPE); //첫 번째 인수 0은 크리스마스 이벤트 할인가가 0일때를 의미.
        OutputView.PrintTodayBenefits(benefits);
        assertTrue(outputStreamCaptor.toString()
                .trim().contains("* 주말 할인. 메인 메뉴 1개당 할인이 적용됩니다.(2,023원이 각 메뉴당 할인)"));
    }


}