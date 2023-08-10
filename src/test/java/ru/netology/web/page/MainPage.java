package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {
    private final SelenideElement heading = $("h2.heading");
    private final SelenideElement buttonBuy = $$(".button").get(0);
    private final SelenideElement buttonCredit = $$(".button").get(1);
    private final SelenideElement paymentByCard = $(byText("Оплата по карте"));
    private final SelenideElement paymentByCredit = $(byText("Кредит по данным карты"));

    public MainPage() {
        heading.shouldBe(Condition.visible);
        paymentByCard.shouldBe(Condition.hidden);
        paymentByCredit.shouldBe(Condition.hidden);
    }

    public PaymentForm paymentPage() {
        buttonBuy.click();
        paymentByCard.shouldBe(Condition.visible);
        return new PaymentForm();
    }

    public PaymentForm creditPage() {
        buttonCredit.click();
        paymentByCredit.shouldBe(Condition.visible);
        return new PaymentForm();
    }
}
