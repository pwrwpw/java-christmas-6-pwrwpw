package christmas.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatValidator {
    private static final Pattern numberRegex = Pattern.compile("^[0-9]+$"); // 양수

    public static boolean isNumber(String input) {
        Matcher matcher = numberRegex.matcher(input);
        return !matcher.matches();
    }
}
