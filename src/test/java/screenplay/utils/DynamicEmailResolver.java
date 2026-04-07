package screenplay.utils;

import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

public final class DynamicEmailResolver {

    private static final String DATE_DIGIT_SUM_TOKEN = "<DATE_DIGIT_SUM>";

    private DynamicEmailResolver() {
    }

    public static String resolve(String rawEmail) {
        if (rawEmail == null || !rawEmail.contains(DATE_DIGIT_SUM_TOKEN)) {
            return rawEmail;
        }

        long now = System.currentTimeMillis();
        int randomSuffix = ThreadLocalRandom.current().nextInt(1000, 9999);
        String uniqueSuffix = String.format(Locale.ROOT, "%d%d", now, randomSuffix);

        return rawEmail.replace(DATE_DIGIT_SUM_TOKEN, uniqueSuffix);
    }
}