package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private final SelenideElement heading = $("[class='heading heading_size_l heading_theme_alfa-on-white']");
    private final SelenideElement paymentCardButton= $(byText("Купить"));
    private final SelenideElement paymentCreditButton = $(byText("Купить в кредит"));
    private final SelenideElement paymentByCard = $$("h3.heading").find(Condition.exactText("Оплата по карте"));
    private final SelenideElement paymentByCredit = $$("h3.heading").find(Condition.exactText("Кредит по данным карты"));

    public DashboardPage() {
        heading.shouldBe(Condition.visible);
    }

    public PaymentFormDebit getDebitCardPayment(){
        paymentCardButton.click();
        paymentByCard.shouldBe(Condition.visible);
        return new PaymentFormDebit();
    }
    public PaymentFormCredit getCreditPayment(){
        paymentCreditButton.click();
        paymentByCredit.shouldBe(Condition.visible);
        return new PaymentFormCredit();
    }
}
