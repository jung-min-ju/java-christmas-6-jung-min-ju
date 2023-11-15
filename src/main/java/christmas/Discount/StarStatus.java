package christmas.Discount;

import static christmas.Constant.DiscountConstant.STARDAY;
import static christmas.Util.Judge.ReturnJudgeStar;

public enum StarStatus { //상태와 행위를 한곳에서 관리
    EXISTING,
    NONE;

    public static StarStatus determineStatus(int DateInput) {
        boolean IsStarDay = ReturnJudgeStar(DateInput);
        if (IsStarDay == STARDAY) {
            return EXISTING;
        }
        return NONE;
    }

}
