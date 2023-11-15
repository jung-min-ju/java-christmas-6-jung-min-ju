package christmas.IO;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public static String AskVisiting(){
        OutputView.AskVisiting();
        return Console.readLine();
    }

    public static String AskOrder(){
        OutputView.AskOrder();
        return Console.readLine();
    }


}
