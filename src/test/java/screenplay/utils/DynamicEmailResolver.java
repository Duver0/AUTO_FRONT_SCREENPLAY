package screenplay.utils;

import java.time.LocalDateTime;

public final class DynamicEmailResolver {

    private static final String DATE_DIGIT_SUM_TOKEN = "<DATE_DIGIT_SUM>";

    private DynamicEmailResolver() {
    }

    public static String resolve(String rawEmail) {
        if (rawEmail == null || !rawEmail.contains(DATE_DIGIT_SUM_TOKEN)) {
            return rawEmail;
        }

        LocalDateTime now = LocalDateTime.now();
        int digitsSum = now.getDayOfMonth()
                + now.getMonthValue()
                + (now.getYear() % 100)
                + now.getHour()
                + now.getMinute()
                + now.getSecond();

        return rawEmail.replace(DATE_DIGIT_SUM_TOKEN, String.valueOf(digitsSum));
    }
}