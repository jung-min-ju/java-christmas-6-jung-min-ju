package christmas.Gift;

import static christmas.Constant.GiftConstant.*;

public class GiftServiceImpl implements GiftService {
    private boolean Gift;

    public GiftServiceImpl() {
        Gift = NOGIFT;
    }

    @Override
    public void CheckGift(int totalPrice) {
        if(totalPrice>=GIFTSTANDARD){
            Gift = YESGIFT;
        }
    }

    @Override
    public boolean getGift() {
        return Gift;
    }

}
