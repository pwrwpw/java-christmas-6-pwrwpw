package christmas.controller;

import christmas.domain.Amount;
import christmas.domain.DiscountDetails;
import christmas.domain.EventBadge;
import christmas.domain.SelectMenus;
import christmas.domain.SelectMenu;
import christmas.domain.Order;
import christmas.domain.User;
import christmas.domain.VisitDate;
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
    private Order order;
    private User user;
    private VisitDate visitDate;
    private SelectMenus selectMenus;
    private DiscountDetails discountDetails;

    public ChristmasController(OutputView outputView, InputView inputView,
                               ChristmasDiscountService christmasDiscountService) {
        this.outputView = outputView;
        this.inputView = inputView;
        this.christmasDiscountService = christmasDiscountService;
    }

    public void run() {
        showWelcomeMessage();
        initUserInput();
        showEventPreview();
        showOrderMenu();
        Amount totalAmount = showTotalPrice();
        createOrder(totalAmount);
        showPresentMenu();
        showBenefit();
        showTotalBenefitPrice();
        user = new User(order,
                new EventBadge(discountDetails.getTotalDiscount() + discountDetails.getPresentMenuPrice()));
        showDiscountAfterTotalPrice();
        showEventBadge();
    }

    private void showWelcomeMessage() {
        outputView.printWelcomeMessage();
        showEventGuideMessage();
    }

    private void initUserInput() {
        visitDate = getVisitDate();
        selectMenus = getSelectMenus();
    }

    private void showEventPreview() {
        outputView.printEventPreviewMessage(visitDate.getMonth(), visitDate.getDay());
    }

    private VisitDate getVisitDate() {
        try {
            String day = inputView.getExpectedVisitDay();
            return new VisitDate(ChristmasPolicy.EVENT_YEAR, ChristmasPolicy.DECEMBER, day);
        } catch (InvalidDayException e) {
            System.out.println(e.getMessage());
            return getVisitDate();
        }
    }

    private SelectMenus getSelectMenus() {
        try {
            String orderMenu = inputView.getOrderMenu();
            return Parser.parseMenuItems(Parser.splitMenuItems(orderMenu));
        } catch (InvalidOrderException e) {
            System.out.println(e.getMessage());
            return getSelectMenus();
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
        for (SelectMenu item : selectMenus.items()) {
            outputView.printOrderMenuItem(item.getMenuName(), item.getMenuCount());
        }
    }

    private Amount showTotalPrice() {
        int totalPrice = selectMenus.items().stream()
                .mapToInt(item -> item.getMenuCount() * findMenuPrice(item.getMenuName()))
                .sum();

        Amount totalAmount = new Amount(totalPrice);
        outputView.printTotalPriceOutputMessage(totalAmount.value());
        return totalAmount;
    }

    private int findMenuPrice(String menuName) {
        return MenuPolicy.getMenuPrice(menuName);
    }

    private void createOrder(Amount totalAmount) {
        order = new Order(visitDate, selectMenus.items().get(0), totalAmount);
    }

    private void showPresentMenu() {
        outputView.printPresentMenuMessage();
        if (order.getTotalAmount() > ChristmasPolicy.PRESENT_THRESHOLD_AMOUNT) {
            outputView.printPresentMenuOutputMessage(ChristmasPolicy.PRESENT_NAME, ChristmasPolicy.PRESENT_QUANTITY);
        } else {
            outputView.printPresentMenuOutputMessage(ChristmasPolicy.NO);
        }
    }

    private void showBenefit() {
        outputView.printBenefitMessage();
        discountDetails = christmasDiscountService.applyDiscount(order, selectMenus);
        outputView.displayDiscountDetails(discountDetails);
    }

    private void showTotalBenefitPrice() {
        int totalDiscountPrice = discountDetails.getTotalDiscount();
        int benefitPrice = totalDiscountPrice + discountDetails.getPresentMenuPrice();
        outputView.printTotalBenefitPriceMessage(benefitPrice);
    }

    private void showDiscountAfterTotalPrice() {
        int totalPrice = order.getTotalAmount();
        int discountPrice = discountDetails.getTotalDiscount();
        int discountAfterTotalPrice = totalPrice - discountPrice;
        outputView.printExpectedPaymentPriceMessage(discountAfterTotalPrice);
    }

    private void showEventBadge() {
        outputView.printEventBadgeMessage(user.getEventBadge());
    }
}
