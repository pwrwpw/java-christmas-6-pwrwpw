package christmas;

import christmas.controller.ChristmasController;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        ChristmasController christmasController = new ChristmasController(new OutputView(), new InputView());
        christmasController.run();
    }
}
