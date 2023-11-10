package christmas.domain;

public class SelectMenu {

    private String menuName;
    private int menuCount;

    public SelectMenu(String menuName, int menuCount) {
        this.menuName = menuName;
        this.menuCount = menuCount;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getMenuCount() {
        return menuCount;
    }
}
