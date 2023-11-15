package christmas;

import christmas.Controller.Controller;

public class Application {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        Controller controller = appConfig.controller();

        //클라이언트 역할
        controller.Reservation();
        controller.ShowMenuAndTotalPrice();
        controller.ShowBenefits();
    }
}
