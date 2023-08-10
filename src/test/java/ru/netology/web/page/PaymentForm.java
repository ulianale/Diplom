package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentForm {

    private final SelenideElement cardNumber = $$(".input__control").get(0);
    private final SelenideElement month = $$(".input__control").get(1);
    private final SelenideElement year = $$(".input__control").get(2);
    private final SelenideElement name = $$(".input__control").get(3);
    private final SelenideElement cvc = $$(".input__control").get(4);
    private final SelenideElement button = $$(".button").get(2);

    private final SelenideElement success = $(withText("Операция одобрена Банком."));
    private final SelenideElement error = $(withText("Ошибка! Банк отказал в проведении операции."));
    private final SelenideElement wrongFormat = $(withText("Неверный формат"));
    private final SelenideElement emptyField = $(withText("Поле обязательно для заполнения"));
    private final SelenideElement wrongExpiryDate = $(withText("Неверно указан срок действия карты"));
    private final SelenideElement cardExpired = $(withText("Истёк срок действия карты"));

    public void PaymentFields(DataHelper.CardInfo info) {
        cardNumber.setValue(info.getCardNumber());
        month.setValue(info.getMonth());
        year.setValue(info.getYear());
        name.setValue(info.getName());
        cvc.setValue(info.getCvc());
        button.click();
    }

    public void successNotification() {
        success.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void errorNotification() {
        error.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void wrongFormat() {
        wrongFormat.shouldBe(Condition.visible);
    }

    public void wrongExpiryDate() {
        wrongExpiryDate.shouldBe(Condition.visible);
    }

    public void cardExpired() {
        cardExpired.shouldBe(Condition.visible);
    }

    public void emptyField() {
        emptyField.shouldBe(Condition.visible);
        wrongFormat.shouldBe(Condition.hidden);
    }

}
