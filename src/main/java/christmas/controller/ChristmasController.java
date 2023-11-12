package christmas.controller;

import christmas.domain.Amount;
import christmas.domain.DiscountDetails;
import christmas.domain.MenuItems;
import christmas.domain.SelectMenu;
import christmas.domain.User;
import christmas.domain.VisitDate;
import christmas.exception.domain.visitdate.FormatDayException;
import christmas.exception.domain.visitdate.InvalidDayException;
import christmas.exception.domain.visitdate.InvalidOrderException;
import christmas.policy.ChristmasPolicy;
import christmas.policy.MenuPolicy;
import christmas.service.ChristmasDiscountService;
import christmas.utils.Parser;
import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasController {

    private final OutputView outputView;
    private final InputView inputView;
    private final ChristmasDiscountService christmasDiscountService;
    private User user;
    private VisitDate visitDate;
    private MenuItems menuItems;
    private DiscountDetails discountDetails;

    public ChristmasController(OutputView outputView, InputView inputView, ChristmasDiscountService christmasDiscountService) {
        this.outputView = outputView;
        this.inputView = inputView;
        this.christmasDiscountService = christmasDiscountService;
    }

    public void run() {
        showWelcomeMessage();
        initUserInput();
        outputView.printEventPreviewMessage(visitDate.getMonth(), visitDate.getDay());
        showOrderMenu();
        Amount totalAmount = showTotalPrice();
        user = new User(visitDate, menuItems.getItems().get(0), totalAmount);
        showPresentMenu();
        showBenefit();
        showTotalBenefitPrice();
    }

    private void showWelcomeMessage() {
        outputView.printWelcomeMessage();
        showEventGuideMessage();
    }

    private void initUserInput() {
        this.visitDate = getVisitDate();
        this.menuItems = getMenuItems();
    }

    private VisitDate getVisitDate() {
        try {
            String day = inputView.getExpectedVisitDay();
            return new VisitDate(ChristmasPolicy.EVENT_YEAR,ChristmasPolicy.DECEMBER, day);
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

    private void showOrderMenu() {
        outputView.printOrderMenuMessage();
        for (SelectMenu item : menuItems.getItems()) {
            outputView.printOrderMenuItem(item.getMenuName(), item.getMenuCount());
        }
    }

    private Amount showTotalPrice() {
        int totalPrice = menuItems.getItems().stream()
                .mapToInt(item -> item.getMenuCount() * findMenuPrice(item.getMenuName()))
                .sum();

        Amount totalAmount = new Amount(totalPrice);
        outputView.printTotalPriceOutputMessage(totalAmount.value());
        return totalAmount;
    }

    private int findMenuPrice(String menuName) {
        return MenuPolicy.getMenuPrice(menuName);
    }

    private void showPresentMenu() {
        outputView.printPresentMenuMessage();
        if (user.getTotalAmount() > ChristmasPolicy.PRESENT_THRESHOLD_AMOUNT) {
            outputView.printPresentMenuOutputMessage(ChristmasPolicy.PRESENT_NAME, ChristmasPolicy.PRESENT_QUANTITY);
        } else {
            outputView.printPresentMenuOutputMessage(ChristmasPolicy.NO);
        }
    }

    private void showBenefit() {
        outputView.printBenefitMessage();
        discountDetails = christmasDiscountService.applyDiscount(user, menuItems);
        outputView.displayDiscountDetails(discountDetails);
    }

    private void showTotalBenefitPrice() {
        int totalDiscountPrice =  discountDetails.getTotalDiscount();
        int benefitPrice = totalDiscountPrice + discountDetails.getPresentMenuPrice();
        outputView.printTotalBenefitPriceMessage(benefitPrice);
    }
}