package ru.netology.web.data;

import lombok.Value;

import static ru.netology.web.data.DataGenerator.*;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class CardInfo {
        String cardNumber;
        String month;
        String year;
        String name;
        String cvc;
    }
    //  card number

    public static CardInfo getApprovedField() {
        return new CardInfo(getCardNumberApproved(), getCurrentMonth(), getCurrentYear(), getValidName(), getValidCode());
    }

    public static CardInfo getDeclinedField() {
        return new CardInfo(getCardNumberDeclined(), getCurrentMonth(), getCurrentYear(), getValidName(), getValidCode());
    }

    public static CardInfo getFieldWithEmptyCardNumber() {
        return new CardInfo("", getCurrentMonth(), getCurrentYear(), getValidName(), getValidCode());
    }

    public static CardInfo getFieldWithErrorCardNumber() {
        return new CardInfo("0000 0000 0000 0000", getCurrentMonth(), getCurrentYear(), getValidName(), getValidCode());
    }

    public static CardInfo getFieldWithInvalidCardNumber() {
        return new CardInfo(getInvalidCardNumber(), getCurrentMonth(), getCurrentYear(), getValidName(), getValidCode());
    }

    // month

    public static CardInfo getFieldWithEmptyMonth() {
        return new CardInfo(getCardNumberApproved(), "", getCurrentYear(), getValidName(), getValidCode());
    }

    public static CardInfo getFieldWithPrevMonth() {
        return new CardInfo(getCardNumberApproved(), getPrevMonth(), getCurrentYear(), getValidName(), getValidCode());
    }

    public static CardInfo getFieldWithErrorMonth() {
        return new CardInfo(getCardNumberApproved(), "13", getCurrentYear(), getValidName(), getValidCode());
    }

    public static CardInfo getFieldWithInvalidMonth() {
        return new CardInfo(getCardNumberApproved(), "1", getCurrentYear(), getValidName(), getValidCode());
    }

    public static CardInfo getFieldDoubleZeroMonth() {
        return new CardInfo(getCardNumberApproved(), "00", getYearInFiveYears(), getValidName(), getValidCode());
    }

    // year

    public static CardInfo getFieldWithYearInFiveYears() {
        return new CardInfo(getCardNumberApproved(), getPrevMonth(), getYearInFiveYears(), getValidName(), getValidCode());
    }

    public static CardInfo getFieldWithEmptyYear() {
        return new CardInfo(getCardNumberApproved(), getCurrentMonth(), "", getValidName(), getValidCode());
    }

    public static CardInfo getFieldWithMoreThenFiveYears() {
        return new CardInfo(getCardNumberApproved(), getPrevMonth(), getYearMoreThenFiveYears(), getValidName(), getValidCode());
    }

    public static CardInfo getFieldWithPrevYear() {
        return new CardInfo(getCardNumberApproved(), getCurrentMonth(), getPrevYear(), getValidName(), getValidCode());
    }

    // name

    public static CardInfo getFieldWithEmptyName() {
        return new CardInfo(getCardNumberApproved(), getCurrentMonth(), getCurrentYear(), "", getValidCode());
    }

    public static CardInfo getFieldWithRussianName() {
        return new CardInfo(getCardNumberApproved(), getCurrentMonth(), getCurrentYear(), getRussianName(), getValidCode());
    }

    public static CardInfo getFieldWithInvalidName() {
        return new CardInfo(getCardNumberApproved(), getCurrentMonth(), getCurrentYear(), "1111", getValidCode());
    }

    public static CardInfo getFieldWithLongName() { // в имени 21 символ включая пробел
        return new CardInfo(getCardNumberApproved(), getCurrentMonth(), getCurrentYear(), "aaaaaaaaaa aaaaaaaaaa", getValidCode());
    }

    public static CardInfo getFieldWithOneLetterInName() {
        return new CardInfo(getCardNumberApproved(), getCurrentMonth(), getCurrentYear(), "a", getValidCode());
    }

    // cvc

    public static CardInfo getFieldWithEmptyCode() {
        return new CardInfo(getCardNumberApproved(), getCurrentMonth(), getCurrentYear(), getValidName(), "");
    }

    public static CardInfo getFieldWithInvalidCode() {
        return new CardInfo(getCardNumberApproved(), getCurrentMonth(), getCurrentYear(), getValidName(), getInvalidCode());
    }

    public static CardInfo getFieldWithErrorCode() {
        return new CardInfo(getCardNumberApproved(), getCurrentMonth(), getCurrentYear(), getValidName(), "000");
    }

}
