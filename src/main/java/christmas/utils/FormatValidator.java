package christmas.utils;

import christmas.exception.domain.visitdate.FormatDayException;
import christmas.exception.domain.visitdate.InvalidDayException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatValidator {

    private static final Pattern dayRegex = Pattern.compile("^[0-9]{1,2}$"); // 1~2자리 숫자

    public static void validateDay(String day) {
        Matcher matcher = dayRegex.matcher(day);
        boolean isInvalidFormat = matcher.matches();

        if (!isInvalidFormat) {
            throw new FormatDayException();
        }
    }
}
