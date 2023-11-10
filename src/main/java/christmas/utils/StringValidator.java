package christmas.utils;

public class StringValidator {

    public static boolean isBlankOrEmpty(String input) {
        if (input.isBlank()) {
            return true;
        }
        return input.isEmpty();
    }
}
