package screenplay.utils;

import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

public final class TestMedicalDataFactory {

    private static final String TESTMAIL_DOMAIN = "testmail.local";

    private TestMedicalDataFactory() {
    }

    public static String uniqueName(String seed) {
        String normalized = sanitize(seed);
        long now = System.currentTimeMillis();
        return String.format(Locale.ROOT, "%s_%d", normalized, now);
    }

    public static String uniqueEmail(String seed) {
        String normalized = sanitize(seed);
        long now = System.currentTimeMillis();
        int randomSuffix = ThreadLocalRandom.current().nextInt(1000, 9999);
        return String.format(Locale.ROOT, "%s_%d_%d@%s", normalized, now, randomSuffix, TESTMAIL_DOMAIN);
    }

    public static long uniqueDocument() {
        long nowSuffix = System.currentTimeMillis() % 100_000_000L;
        int randomSuffix = ThreadLocalRandom.current().nextInt(100, 999);
        return 10_000_000_000L + (nowSuffix * 1_000L) + randomSuffix;
    }

    private static String sanitize(String seed) {
        if (seed == null || seed.isBlank()) {
            return "dato";
        }

        String normalized = seed.replaceAll("[^a-zA-Z0-9]", "").toLowerCase(Locale.ROOT);
        return normalized.isBlank() ? "dato" : normalized;
    }
}
