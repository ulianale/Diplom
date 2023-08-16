package ru.netology.web.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.MainPage;

import static com.codeborne.selenide.Selenide.*;

public class PaymentTests {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    public void setUp() {
        //Configuration.holdBrowserOpen = true;
        open("http://localhost:8080");
    }

    @Test
    void shouldSuccessWithApprovedCard() {
        var paymentPage = new MainPage().paymentPage();
        var info = DataHelper.getApprovedField();
        paymentPage.PaymentFields(info);
        paymentPage.successNotification();
    }

    @Test
    void shouldErrorWithDeclinedCard() {
        var paymentPage = new MainPage().paymentPage();
        var info = DataHelper.getDeclinedField();
        paymentPage.PaymentFields(info);
        paymentPage.errorNotification();
    }

    // card number

    @Test
    void shouldErrorWithEmptyCardNumber() {
        var paymentPage = new MainPage().paymentPage();
        var info = DataHelper.getFieldWithEmptyCardNumber();
        paymentPage.PaymentFields(info);
        paymentPage.emptyField();
    }

    @Test
    void shouldErrorWithErrorCardNumber() {
        var paymentPage = new MainPage().paymentPage();
        var info = DataHelper.getFieldWithErrorCardNumber();
        paymentPage.PaymentFields(info);
        paymentPage.errorNotification();
    }

    @Test
    void shouldWrongWithInvalidCardNumber() {
        var paymentPage = new MainPage().paymentPage();
        var info = DataHelper.getFieldWithInvalidCardNumber();
        paymentPage.PaymentFields(info);
        paymentPage.wrongFormat();
    }

    // month

    @Test
    void shouldErrorWithEmptyMonth() {
        var paymentPage = new MainPage().paymentPage();
        var info = DataHelper.getFieldWithEmptyMonth();
        paymentPage.PaymentFields(info);
        paymentPage.emptyField();
    }

    @Test
    void shouldErrorWithErrorMonth() {
        var paymentPage = new MainPage().paymentPage();
        var info = DataHelper.getFieldWithErrorMonth();
        paymentPage.PaymentFields(info);
        paymentPage.wrongExpiryDate();
    }

    @Test
    void shouldErrorWithInvalidMonth() {
        var paymentPage = new MainPage().paymentPage();
        var info = DataHelper.getFieldWithInvalidMonth();
        paymentPage.PaymentFields(info);
        paymentPage.wrongFormat();
    }

    @Test
    void shouldErrorWithDoubleZeroMonth() {
        var paymentPage = new MainPage().paymentPage();
        var info = DataHelper.getFieldDoubleZeroMonth();
        paymentPage.PaymentFields(info);
        paymentPage.wrongExpiryDate();
    }

    @Test
    void shouldExpiryWithPrevMonth() {
        var paymentPage = new MainPage().paymentPage();
        var info = DataHelper.getFieldWithPrevMonth();
        paymentPage.PaymentFields(info);
        paymentPage.cardExpired();
    }

    // year

    @Test
    void shouldSuccessWithCardInFiveYears() {
        var paymentPage = new MainPage().paymentPage();
        var info = DataHelper.getFieldWithYearInFiveYears();
        paymentPage.PaymentFields(info);
        paymentPage.successNotification();
    }

    @Test
    void shouldErrorWithEmptyYear() {
        var paymentPage = new MainPage().paymentPage();
        var info = DataHelper.getFieldWithEmptyYear();
        paymentPage.PaymentFields(info);
        paymentPage.emptyField();
    }

    @Test
    void shouldExpiryWithPrevYear() {
        var paymentPage = new MainPage().paymentPage();
        var info = DataHelper.getFieldWithPrevYear();
        paymentPage.PaymentFields(info);
        paymentPage.cardExpired();
    }

    @Test
    void shouldWrongWithCardMoreThenFiveYears() {
        var paymentPage = new MainPage().paymentPage();
        var info = DataHelper.getFieldWithMoreThenFiveYears();
        paymentPage.PaymentFields(info);
        paymentPage.wrongExpiryDate();
    }

    // name

    @Test
    void shouldErrorWithEmptyName() {
        var paymentPage = new MainPage().paymentPage();
        var info = DataHelper.getFieldWithEmptyName();
        paymentPage.PaymentFields(info);
        paymentPage.emptyField();
    }

    @Test
    void shouldErrorWithRussianName() {
        var paymentPage = new MainPage().paymentPage();
        var info = DataHelper.getFieldWithRussianName();
        paymentPage.PaymentFields(info);
        paymentPage.wrongFormat();
    }

    @Test
    void shouldErrorWithInvalidName() {
        var paymentPage = new MainPage().paymentPage();
        var info = DataHelper.getFieldWithInvalidName();
        paymentPage.PaymentFields(info);
        paymentPage.wrongFormat();
    }

    @Test
    void shouldErrorWithLongName() {
        var paymentPage = new MainPage().paymentPage();
        var info = DataHelper.getFieldWithLongName();
        paymentPage.PaymentFields(info);
        paymentPage.wrongFormat();
    }

    @Test
    void shouldErrorWithOneLetterInName() {
        var paymentPage = new MainPage().paymentPage();
        var info = DataHelper.getFieldWithOneLetterInName();
        paymentPage.PaymentFields(info);
        paymentPage.wrongFormat();
    }

    // cvc

    @Test
    void shouldErrorWithEmptyCode() {
        var paymentPage = new MainPage().paymentPage();
        var info = DataHelper.getFieldWithEmptyCode();
        paymentPage.PaymentFields(info);
        paymentPage.emptyField();
    }

    @Test
    void shouldErrorWithInvalidCode() {
        var paymentPage = new MainPage().paymentPage();
        var info = DataHelper.getFieldWithInvalidCode();
        paymentPage.PaymentFields(info);
        paymentPage.wrongFormat();
    }

    @Test
    void shouldErrorWithErrorCode() {
        var paymentPage = new MainPage().paymentPage();
        var info = DataHelper.getFieldWithErrorCode();
        paymentPage.PaymentFields(info);
        paymentPage.wrongFormat();
    }
}
