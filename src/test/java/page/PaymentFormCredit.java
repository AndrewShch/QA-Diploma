package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentFormCredit {
    private final SelenideElement cardNumberField = $("[placeholder='0000 0000 0000 0000']");
    private final SelenideElement monthField = $("[placeholder='08']");
    private final SelenideElement yearField = $("[placeholder='22']");
    private final SelenideElement ownerField = $("fieldset > div:nth-child(3) > span > span:nth-child(1) > span > span > span.input__box > input");
    private final SelenideElement cvccvvField = $("[placeholder='999']");
    private final SelenideElement button = $$(".button").find(Condition.exactText("Продолжить"));

    private final SelenideElement successNotification = $(".notification_status_ok");
    private final SelenideElement errorNotification = $(".notification_status_error");
    private final SelenideElement emptyFieldMessage = $(byText("Поле обязательно для заполнения"));
    private final SelenideElement wrongFormatMessage = $(byText("Неверный формат"));
    private final SelenideElement wrongCardExpirationMessage = $(byText("Неверно указан срок действия карты"));
    private final SelenideElement cardExpiredMessage = $(byText("Истёк срок действия карты"));

    public void fillingFieldsFormat(DataHelper.CardNumber info, String month, String year, String cvccvv, String owner ) {
        cardNumberField.setValue(info.getCardNumber());
        monthField.setValue(month);
        yearField.setValue(year);
        cvccvvField.setValue(cvccvv);
        ownerField.setValue(owner);
        button.click();
    }


    public void checkSuccessNotification(){
        successNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }
    public void checkErrorNotification(){
        errorNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }
    public void checkEmptyFieldMessage(){
        emptyFieldMessage.shouldBe(Condition.visible);
    }
    public void checkWrongFormatMessage(){
        wrongFormatMessage.shouldBe(Condition.visible);
    }
    public void checkWrongCardExpirationMessage(){
        wrongCardExpirationMessage.shouldBe(Condition.visible);
    }
    public void checkCardExpiredMessage(){
        cardExpiredMessage.shouldBe(Condition.visible);
    }
}
