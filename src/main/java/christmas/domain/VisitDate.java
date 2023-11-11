package christmas.domain;

import christmas.exception.domain.visitdate.InvalidDayException;
import christmas.policy.ChristmasPolicy;
import christmas.utils.FormatValidator;
import christmas.utils.Parser;

public class VisitDate {

    private final int month;
    private final int day;

    public VisitDate(int month, String day) {
        this.month = month;
        this.day = Parser.parseInteger(day);
        validateDay();
    }

    private void validateDay() {
        if (month == ChristmasPolicy.DECEMBER && (day < ChristmasPolicy.DEC_MINIMUM_DAY || day > ChristmasPolicy.DEC_MAXIMUM_DAY)) {
            throw new InvalidDayException();
        }
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }
}
