package christmas.controller;

import christmas.domain.MenuItem;
import christmas.domain.MenuItems;
import christmas.domain.SelectMenu;
import christmas.domain.VisitDate;
import christmas.exception.domain.visitdate.FormatDayException;
import christmas.exception.domain.visitdate.InvalidDayException;
import christmas.exception.domain.visitdate.InvalidOrderException;
import christmas.policy.ChristmasPolicy;
import christmas.policy.MenuPolicy;
import christmas.utils.Parser;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Arrays;

public class ChristmasController {

    private final OutputView outputView;
    private final InputView inputView;

    public ChristmasController(OutputView outputView, InputView inputView) {
        this.outputView = outputView;
        this.inputView = inputView;
    }

    public void run() {
        outputView.printWelcomeMessage();
        showEventGuideMessage();
        VisitDate visitDate = getVisitDate();
        MenuItems menuItems = getMenuItems();
        outputView.printEventPreviewMessage(visitDate.getMonth(),visitDate.getDay());
        showOrderMenu(menuItems);
        showTotalPrice(menuItems);
    }

    private VisitDate getVisitDate() {
        try {
            String day = inputView.getExpectedVisitDay();
            return new VisitDate(ChristmasPolicy.DECEMBER, day);
        } catch (FormatDayException | InvalidDayException e) {
            System.out.println(e.getMessage());
            return getVisitDate();
        }
    }

    private MenuItems getMenuItems() {
        try {
            String orderMenu = inputView.getOrderMenu();
            return Parser.parseMenuItems(Parser.splitMenuItems(orderMenu));
        } catch (InvalidOrderException e) {
            System.out.println(e.getMessage());
            return getMenuItems();
        }
    }

    private void showEventGuideMessage() {
        outputView.printEventGuidelines();
        outputView.printMinimumOrderAmountNotice();
        outputView.printBeverageOrderRestrictionNotice();
        outputView.printMaxMenuItemsNotice();
    }
    private void showOrderMenu(MenuItems menuItems) {
        outputView.printOrderMenuMessage();
        for (SelectMenu item : menuItems.getItems()) {
            System.out.println(item.getMenuName() + " " + item.getMenuCount() + "개");
        }
    }

    private void showTotalPrice(MenuItems menuItems) {
        outputView.printTotalPriceMessage();
        int totalPrice = 0;

        for (SelectMenu item : menuItems.getItems()) {
            int price = findMenuPrice(item.getMenuName());
            totalPrice += price * item.getMenuCount();
        }

        outputView.printTotalPriceOutputMessage(totalPrice);
    }

    private int findMenuPrice(String menuName) {
        return Arrays.stream(MenuPolicy.values())
                .flatMap(menuPolicy -> menuPolicy.getMenuItems().stream())
                .filter(menuItem -> menuItem.getName().equals(menuName))
                .findFirst()
                .map(MenuItem::getPrice)
                .orElseThrow(InvalidOrderException::new);
    }
}
