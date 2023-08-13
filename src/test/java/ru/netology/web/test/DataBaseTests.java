package ru.netology.web.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.data.SQLHelper;
import ru.netology.web.page.MainPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.web.data.SQLHelper.cleanDatabase;

public class DataBaseTests {
    @BeforeEach
    void setUp() {
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

    @Test           // данные о покупке по карте должны добавляться в поле payment_id
    void shouldAddCorrectPaymentInfoInOrderTable() {
        var paymentPage = new MainPage().paymentPage();
        var info = DataHelper.getApprovedField();
        paymentPage.PaymentFields(info);
        paymentPage.showAnyNotification();
        assertEquals(SQLHelper.getPaymentTableInfo().getTransaction_id(), SQLHelper.getOrderTableInfo().getPayment_id());
    }

    @Test           // данные о покупке в кредит должны добавляться в поле credit_id
    void shouldAddCorrectCreditInfoInOrderTable() {
        var paymentPage = new MainPage().creditPage();
        var info = DataHelper.getApprovedField();
        paymentPage.PaymentFields(info);
        paymentPage.showAnyNotification();
        assertEquals(SQLHelper.getCreditTableInfo().getBank_id(), SQLHelper.getOrderTableInfo().getCredit_id());
    }


}
