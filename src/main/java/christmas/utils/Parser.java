package christmas.utils;

import christmas.domain.MenuItems;
import christmas.domain.SelectMenu;
import christmas.exception.domain.visitdate.InvalidOrderException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {

    public static int parseInteger(String input) {
        return Integer.parseInt(input);
    }

    public static List<String> splitMenuItems(String input) {
        StringValidator.isBlankOrEmpty(input);
        String[] parsedItems = input.split(",");

        return Arrays.asList(parsedItems);
    }

    public static MenuItems parseMenuItems(List<String> splitMenuItems) {
        List<SelectMenu> parsedItems = new ArrayList<>();

        for (String selectMenu : splitMenuItems) {
            String[] selectMenuInfo = selectMenu.split("-");
            if (selectMenuInfo.length != 2) {
                throw new InvalidOrderException();
            }
            String name = selectMenuInfo[0].trim();
            String d = selectMenuInfo[1].trim();
            if(FormatValidator.isNumber(d)) {
                throw new InvalidOrderException();
            }
            int quantity = Integer.parseInt(d);
            parsedItems.add(new SelectMenu(name, quantity));
        }
        return new MenuItems(parsedItems);
    }

}
