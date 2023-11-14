package christmas.domain;

import christmas.exception.domain.visitdate.InvalidOrderException;
import christmas.policy.MenuPolicy;
import java.util.Objects;

public record SelectMenu(String menuName, int menuCount) {

    public SelectMenu(String menuName, int menuCount) {
        validateMenuName(menuName);
        this.menuName = menuName;
        validateMenuCount(menuCount);
        this.menuCount = menuCount;
    }

    private void validateMenuName(String menuName) {
        if (MenuPolicy.isNotValidMenuName(menuName)) {
            throw new InvalidOrderException();
        }
    }

    private void validateMenuCount(int menuCount) {
        if (MenuPolicy.isNotValidMenuCount(menuCount)) {
            throw new InvalidOrderException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SelectMenu that = (SelectMenu) o;
        return menuCount == that.menuCount &&
                Objects.equals(menuName, that.menuName);
    }

}
