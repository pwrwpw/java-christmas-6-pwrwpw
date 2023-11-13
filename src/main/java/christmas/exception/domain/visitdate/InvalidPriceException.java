package christmas.exception.domain.visitdate;

public class InvalidPriceException extends IllegalArgumentException{

    private static final String MESSAGE = "[ERROR] 유효하지 않은 가격입니다.";

    public InvalidPriceException() {
        super(MESSAGE);
    }
}
