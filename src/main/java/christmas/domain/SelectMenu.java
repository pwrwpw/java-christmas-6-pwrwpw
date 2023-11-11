package christmas.domain;

import christmas.exception.domain.visitdate.InvalidOrderException;
import christmas.policy.MenuPolicy;

public class SelectMenu {

    private String menuName;
    private int menuCount;

    public SelectMenu(String menuName, int menuCount) {
        validateMenuName(menuName);
        this.menuName = menuName;
        validateMenuCount(menuCount);
        this.menuCount = menuCount;
    }
    public String getMenuName() {
        return menuName;
    }

    public int getMenuCount() {
        return menuCount;
    }


    private void validateMenuName(String menuName) {
        if (MenuPolicy.isValidMenuName(menuName)) {
            throw new InvalidOrderException();
        }
    }

    private void validateMenuCount(int menuCount) {
        if (MenuPolicy.isNotValidMenuCount(menuCount)) {
            throw new InvalidOrderException();
        }
    }

}
