package christmas.Discount;

import christmas.Util.JudgeWeek;
import static christmas.Constant.DiscountConstant.STARDAY;

public enum StarStatus { //상태와 행위를 한곳에서 관리
    EXISTING,
    NONE;

    public static StarStatus determineStatus(int DateInput) {
        boolean IsStarDay = JudgeWeek.ReturnJudgeStar(DateInput);
        if (IsStarDay == STARDAY) {
            return EXISTING;
        }
        return NONE;
    }

}
