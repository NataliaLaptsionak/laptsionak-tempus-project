package by.tempus.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class DataGenerator {

    private static final Random random = new Random();

    public static String generateValidEmail() {
        return "test" + System.currentTimeMillis() + "@example.com";
    }

    public static String generateInvalidEmailMissingPartBeforeAt() {
        return "@invalid-email.com";
    }

    public static String generateInvalidEmailMissingPartAfterAt() {
        return "invalid-email@";
    }

    public static String generateInvalidEmailMissingAt() {
        return "invalidgmail.com";
    }

    public static String generateIncorrectEmail() {
        return "1@rtty";
    }

    public static String generateValidBelarusianPhoneNumber() {
        String operatorCode = String.format("%02d", random.nextInt(3) == 0 ? 29 : (random.nextInt(2) == 0 ? 33 : 44));
        String subscriberNumber = RandomStringUtils.randomNumeric(7);
        return "+375" + operatorCode + subscriberNumber;
    }

    public static String generateInvalidBelarusianPhoneNumber() {
        String operatorCode = String.format("%02d", random.nextInt(3) == 0 ? 28 : (random.nextInt(2) == 0 ? 31 : 45));
        String subscriberNumber = RandomStringUtils.randomNumeric(5);
        return "+375" + operatorCode + subscriberNumber;
    }

    public static String generateValidFullName() {
        return "Тест Тестович Тестовый";
    }

    public static String generateValidPassword() {
        return RandomStringUtils.randomAlphanumeric(10);
    }

    public static String generateInvalidPassword() {
        return "123";
    }

    public static String generateValidRepeatPassword() {
        return "Password-1";
    }

    public static String generateInvalidRepeatPassword() {
        return "Password-2";
    }
}
