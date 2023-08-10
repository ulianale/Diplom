package ru.netology.web.data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {
    public static Faker faker = new Faker(new Locale("en"));

    //  numberCard
    public static String getCardNumberApproved() {
        return "4444 4444 4444 4441";
    }

    public static String getCardNumberDeclined() {
        return "4444 4444 4444 4442";
    }

    public static String getInvalidCardNumber() {
        return faker.number().digits(15);
    }

    // Month
    public static String getCurrentMonth() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getPrevMonth() {
        return LocalDate.now().minusMonths(1).format(DateTimeFormatter.ofPattern("MM"));
    }

    // Year
    public static String getCurrentYear() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getYearInFiveYears() {
        return LocalDate.now().plusYears(5).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getPrevYear() {
        return LocalDate.now().minusYears(1).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getYearMoreThenFiveYears() {
        return LocalDate.now().plusYears(6).format(DateTimeFormatter.ofPattern("yy"));
    }

    // Name
    public static String getValidName() {
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String getRussianName() {
        Faker faker1 = new Faker(new Locale("ru"));
        return faker1.name().firstName() + " " + faker1.name().lastName();
    }

    // CVC code
    public static String getValidCode() {
        return faker.number().digits(3);
    }

    public static String getInvalidCode() {
        return faker.number().digits(2);
    }

}