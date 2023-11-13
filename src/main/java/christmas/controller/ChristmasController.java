package christmas.controller;

import christmas.domain.DiscountDetails;
import christmas.domain.SelectMenus;
import christmas.domain.User;
import christmas.domain.VisitDate;
import christmas.exception.domain.visitdate.InvalidDayException;
import christmas.exception.domain.visitdate.InvalidOrderException;
import christmas.policy.ChristmasPolicy;
import christmas.service.ChristmasService;
import christmas.utils.Parser;
import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasController {

    private final OutputView outputView;
    private final InputView inputView;
    private final ChristmasService christmasService;

    public ChristmasController(OutputView outputView, InputView inputView, ChristmasService christmasService) {
        this.outputView = outputView;
        this.inputView = inputView;
        this.christmasService = christmasService;
    }

    public void run() {
        showWelcomeMessage();
        User user = processUserInput();
        showEventDetails(user, user.getVisitDate(), user.getSelected());
    }

    private void showWelcomeMessage() {
        outputView.printWelcomeMessage();
        showEventGuideMessage();
    }

    private void showEventDetails(User user, VisitDate visitDate, SelectMenus selectMenus) {
        showEventPreview(visitDate);
        showOrderMenu(selectMenus);
        showTotalPrice(user.getTotalOrderAmount());
        showPresentMenu(user);
        showBenefit(user.getDiscountDetails());
        showTotalBenefit(user);
        showDiscountAfterTotalPrice(user);
        showEventBadge(user.getEventBadgeName());
    }

    private User processUserInput() {
        VisitDate visitDate = getVisitDate();
        SelectMenus selectMenus = getSelectMenus();
        return christmasService.processChristmasEvent(visitDate, selectMenus);
    }

    private VisitDate getVisitDate() {
        try {
            String day = inputView.getExpectedVisitDay();
            return new VisitDate(ChristmasPolicy.EVENT_YEAR, ChristmasPolicy.DECEMBER, day);
        } catch (InvalidDayException e) {
            outputView.printErrorMessage(e.getMessage());
            return getVisitDate();
        }
    }

    private SelectMenus getSelectMenus() {
        try {
            String orderMenu = inputView.getOrderMenu();
            return Parser.parseMenuItems(Parser.splitMenuItems(orderMenu));
        } catch (InvalidOrderException e) {
            outputView.printErrorMessage(e.getMessage());
            return getSelectMenus();
        }
    }

    private void showEventGuideMessage() {
        outputView.printEventGuidelines();
        outputView.printMinimumOrderAmountNotice();
        outputView.printBeverageOrderRestrictionNotice();
        outputView.printMaxMenuItemsNotice();
    }

    private void showEventPreview(VisitDate visitDate) {
        outputView.printEventPreviewMessage(visitDate.getMonth(), visitDate.getDay());
    }

    private void showOrderMenu(SelectMenus selectMenus) {
        outputView.printOrderMenuMessage();
        selectMenus.items().forEach(item ->
                outputView.printOrderMenuItem(item.getMenuName(), item.getMenuCount())
        );
    }

    private void showTotalPrice(int totalAmount) {
        outputView.printTotalPriceOutputMessage(totalAmount);
    }

    private void showPresentMenu(User user) {
        outputView.printPresentMenuMessage();
        if (user.isOrderEligibleForPresent()) {
            outputView.printPresentMenuOutputMessage(ChristmasPolicy.PRESENT_NAME, ChristmasPolicy.PRESENT_QUANTITY);
            return;
        }
        outputView.printPresentMenuOutputMessage(ChristmasPolicy.NO_PRESENT);
    }

    private void showBenefit(DiscountDetails discountDetails) {
        outputView.printBenefitMessage();
        outputView.displayDiscountDetails(discountDetails);
    }

    private void showTotalBenefit(User user) {
        int totalBenefit = user.getTotalBenefit();
        outputView.printTotalBenefitPriceMessage(totalBenefit);
    }

    private void showDiscountAfterTotalPrice(User user) {
        int finalPayment = user.calculateFinalPayment();
        outputView.printExpectedPaymentPriceMessage(finalPayment);
    }

    private void showEventBadge(String eventBadge) {
        outputView.printEventBadgeMessage(eventBadge);
    }
}
