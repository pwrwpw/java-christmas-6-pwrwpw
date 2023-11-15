package christmas;

import christmas.controller.ChristmasController;
import christmas.service.ChristmasDiscountService;
import christmas.service.ChristmasService;
import christmas.service.ChristmasOrderService;
import christmas.service.DiscountService;
import christmas.service.OrderService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        OrderService orderService = new ChristmasOrderService();
        DiscountService discountService = new ChristmasDiscountService();
        ChristmasService christmasService = new ChristmasService(discountService, orderService);

        ChristmasController christmasController = new ChristmasController(
                new OutputView(),
                new InputView(),
                christmasService);
        christmasController.run();
    }
}
