package christmas.utils;

import christmas.domain.SelectMenus;
import christmas.domain.SelectMenu;
import christmas.exception.domain.visitdate.InvalidOrderException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {

    public static int parseInteger(String input) {
        if(FormatValidator.isNumber(input)) {
            throw new InvalidOrderException();
        }
        return Integer.parseInt(input);
    }

    public static List<String> splitMenuItems(String input) {
        StringValidator.isBlankOrEmpty(input);
        return Arrays.asList(input.split(","));
    }
}
