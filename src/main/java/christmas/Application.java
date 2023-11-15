package christmas;

import christmas.Controller.BenefitController;
import christmas.Controller.OrderController;

public class Application {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        BenefitController benefitController = appConfig.benefitcontroller();
        OrderController orderController = appConfig.orderController();

        //클라이언트 역할
        orderController.Reservation();
        orderController.ShowMenuAndTotalPrice();
        benefitController.ShowBenefits();
    }
}
