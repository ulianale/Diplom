package ru.netology.web.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.web.data.DataHelper;
import ru.netology.web.data.SQLHelper;
import ru.netology.web.page.MainPage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.web.data.SQLHelper.cleanDatabase;

public class DataBaseTests {

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

    @AfterEach
    public void tearDown() {
        cleanDatabase();
    }

    // проверка статуса: APPROVED или DECLINED

    @Test
    void shouldApprovedWithApprovedCardPaymentGate() {
        var paymentPage = new MainPage().paymentPage();
        var info = DataHelper.getApprovedField();
        paymentPage.PaymentFields(info);
        paymentPage.showAnyNotification();
        assertEquals("APPROVED", SQLHelper.getPaymentTableInfo().getStatus());
    }

    @Test
    void shouldDeclinedWithDeclinedCardPaymentGate() {
        var paymentPage = new MainPage().paymentPage();
        var info = DataHelper.getDeclinedField();
        paymentPage.PaymentFields(info);
        paymentPage.showAnyNotification();
        assertEquals("DECLINED", SQLHelper.getPaymentTableInfo().getStatus());
    }

    @Test
    void shouldApprovedWithApprovedCardCreditGate() {
        var paymentPage = new MainPage().creditPage();
        var info = DataHelper.getApprovedField();
        paymentPage.PaymentFields(info);
        paymentPage.showAnyNotification();
        assertEquals("APPROVED", SQLHelper.getCreditTableInfo().getStatus());

    }

    @Test
    void shouldDeclinedWithDeclinedCardCreditGate() {
        var paymentPage = new MainPage().creditPage();
        var info = DataHelper.getDeclinedField();
        paymentPage.PaymentFields(info);
        paymentPage.showAnyNotification();
        assertEquals("DECLINED", SQLHelper.getCreditTableInfo().getStatus());
    }

    // проверка на правильное заполнение таблицы order_entity

    @Test
        // данные о покупке по карте должны добавляться в поле payment_id
    void shouldAddCorrectPaymentInfoInOrderTable() {
        var paymentPage = new MainPage().paymentPage();
        var info = DataHelper.getApprovedField();
        paymentPage.PaymentFields(info);
        paymentPage.showAnyNotification();
        assertEquals(SQLHelper.getPaymentTableInfo().getTransaction_id(), SQLHelper.getOrderTableInfo().getPayment_id());
    }

    @Test
        // данные о покупке в кредит должны добавляться в поле credit_id
    void shouldAddCorrectCreditInfoInOrderTable() {
        var paymentPage = new MainPage().creditPage();
        var info = DataHelper.getApprovedField();
        paymentPage.PaymentFields(info);
        paymentPage.showAnyNotification();
        assertEquals(SQLHelper.getCreditTableInfo().getBank_id(), SQLHelper.getOrderTableInfo().getCredit_id());
    }

    // проверка на добавление корректной даты и времени в таблицу

    @Test
    void shouldAddCorrectDateTimeInPaymentTable() {
        var date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        var paymentPage = new MainPage().paymentPage();
        var info = DataHelper.getApprovedField();
        paymentPage.PaymentFields(info);
        paymentPage.showAnyNotification();
        var dateInTable = SQLHelper.getPaymentTableInfo().getCreated();
        var index = dateInTable.indexOf(".");
        assertEquals(date, dateInTable.substring(0, index - 3));
    }

    @Test
    void shouldAddCorrectDateTimeInCreditTable() {
        var date = LocalDateTime.now().plusSeconds(5).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        var paymentPage = new MainPage().creditPage();
        var info = DataHelper.getApprovedField();
        paymentPage.PaymentFields(info);
        paymentPage.showAnyNotification();
        var dateInTable = SQLHelper.getCreditTableInfo().getCreated();
        var index = dateInTable.indexOf(".");
        assertEquals(date, dateInTable.substring(0, index - 3));
    }

    // проверка на соответствие цены тура на странице приложения и в базе данных

    @Test
    void shouldAddCorrectPriceInPaymentTable() {
        var paymentPage = new MainPage().paymentPage();
        var price = paymentPage.getPrice();
        var info = DataHelper.getApprovedField();
        paymentPage.PaymentFields(info);
        paymentPage.showAnyNotification();
        assertEquals(price, SQLHelper.getPaymentTableInfo().getAmount());
    }

}
