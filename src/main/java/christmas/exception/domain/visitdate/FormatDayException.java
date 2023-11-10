package christmas.exception.domain.visitdate;

public class FormatDayException extends IllegalArgumentException {

    private static final String MESSAGE = "[ERROR] 날짜는 숫자만 입력 가능합니다. 다시 입력해 주세요.";

    public FormatDayException() {
        super(MESSAGE);
    }
}
