package christmas.IO;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    //AskVisting 구현
    public static String AskVisiting(){
        OutputView.AskVisiting();
        return Console.readLine();
    }

}
