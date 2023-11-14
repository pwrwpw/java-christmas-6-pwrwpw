package christmas.utils;

import christmas.exception.domain.visitdate.InvalidOrderException;
import java.util.Arrays;
import java.util.List;

public class Parser {

    private final static String DELIMITER = ",";

    public static int parseInteger(String input) {
        if (FormatValidator.isNotNumber(input)) {
            throw new InvalidOrderException();
        }
        return Integer.parseInt(input);
    }

    public static List<String> splitMenuItems(String input) {
        StringValidator.isBlankOrEmpty(input);
        return Arrays.asList(input.split(DELIMITER));
    }
}
