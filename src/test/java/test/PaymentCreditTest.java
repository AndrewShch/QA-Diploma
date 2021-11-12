package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataHelper;
import data.SqlHelper;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.DashboardPage;
import page.PaymentFormCredit;



import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentCreditTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void setUp(){
        open("http://185.119.57.64:8080/");
    }




    @AfterAll
    static void tearDownAll() {
        SqlHelper.cleanTables();
    }

    //Card


    @Test
    void shouldPaymentCreditCardWithStatusApproved(){
        val dashboardPage = new DashboardPage();
        val paymentFormCredit = new PaymentFormCredit();
        dashboardPage.getCreditPayment();
        val cardNumber = DataHelper.getApprovedCardNumber();
        val month = DataHelper.getMonth();
        val year = DataHelper.getYear();
        val cvccvv = DataHelper.getCorrectCVCCVV();
        val owner = DataHelper.getValidOwner();
        paymentFormCredit.fillingFieldsFormat(cardNumber, month, year,cvccvv, owner);
        paymentFormCredit.checkSuccessNotification();
        val paymentStatus = SqlHelper.getStatusCreditRequestEntity();
        assertEquals("APPROVED", paymentStatus);
    }

    @Test
    void shouldPaymentCreditWithStatusDeclined(){
        val dashboardPage = new DashboardPage();
        val paymentFormCredit = new PaymentFormCredit();
        dashboardPage.getCreditPayment();
        val cardNumber = DataHelper.getDeclinedCardNumber();
        val month = DataHelper.getMonth();
        val year = DataHelper.getYear();
        val cvccvv = DataHelper.getCorrectCVCCVV();
        val owner = DataHelper.getValidOwner();
        paymentFormCredit.fillingFieldsFormat(cardNumber, month, year,cvccvv, owner);
        paymentFormCredit.checkErrorNotification();
        val paymentStatus = SqlHelper.getStatusCreditRequestEntity();
        assertEquals("DECLINES", paymentStatus);
    }

    @Test
    void shouldDontSuccessByCreditWithAnotherCard(){
        val dashboardPage = new DashboardPage();
        val paymentFormCredit = new PaymentFormCredit();
        dashboardPage.getCreditPayment();
        val cardNumber = DataHelper.getInvalidCardNumber();
        val month = DataHelper.getMonth();
        val year = DataHelper.getYear();
        val cvccvv = DataHelper.getCorrectCVCCVV();
        val owner = DataHelper.getValidOwner();
        paymentFormCredit.fillingFieldsFormat(cardNumber, month, year,cvccvv, owner);
        paymentFormCredit.checkErrorNotification();
    }

    @Test
    void shouldDontSuccessByCreditCardNumberWithNulls(){
        val dashboardPage = new DashboardPage();
        val paymentFormCredit = new PaymentFormCredit();
        dashboardPage.getCreditPayment();
        val cardNumber = DataHelper.getCardNumberWithNulls();
        val month = DataHelper.getMonth();
        val year = DataHelper.getYear();
        val cvccvv = DataHelper.getCorrectCVCCVV();
        val owner = DataHelper.getValidOwner();
        paymentFormCredit.fillingFieldsFormat(cardNumber, month, year,cvccvv, owner);
        paymentFormCredit.checkErrorNotification();
    }

    @Test
    void shouldDontSuccessByCreditWithEmptyFieldCardNumber(){
        val dashboardPage = new DashboardPage();
        val paymentFormCredit = new PaymentFormCredit();
        dashboardPage.getCreditPayment();
        val cardNumber = DataHelper.getCardWithoutNumber();
        val month = DataHelper.getMonth();
        val year = DataHelper.getYear();
        val cvccvv = DataHelper.getCorrectCVCCVV();
        val owner = DataHelper.getValidOwner();
        paymentFormCredit.fillingFieldsFormat(cardNumber, month, year,cvccvv, owner);
        paymentFormCredit.checkWrongFormatMessage();
    }



    // Month

    @Test
    void shouldDontSuccessByCreditWithInvalidMonth(){
        val dashboardPage = new DashboardPage();
        val paymentFormCredit = new PaymentFormCredit();
        dashboardPage.getCreditPayment();
        val cardNumber = DataHelper.getApprovedCardNumber();
        val month = DataHelper.getInvalidMonth();
        val year = DataHelper.getYear();
        val cvccvv = DataHelper.getCorrectCVCCVV();
        val owner = DataHelper.getValidOwner();
        paymentFormCredit.fillingFieldsFormat(cardNumber, month, year,cvccvv, owner);
        paymentFormCredit.checkWrongCardExpirationMessage();
    }

    @Test
    void shouldDontSuccessByCreditWithEmptyFieldMonth(){
        val dashboardPage = new DashboardPage();
        val paymentFormCredit = new PaymentFormCredit();
        dashboardPage.getCreditPayment();
        val cardNumber = DataHelper.getApprovedCardNumber();
        val month = DataHelper.getEmptyMonth();
        val year = DataHelper.getYear();
        val cvccvv = DataHelper.getCorrectCVCCVV();
        val owner = DataHelper.getValidOwner();
        paymentFormCredit.fillingFieldsFormat(cardNumber, month, year,cvccvv, owner);
        paymentFormCredit.checkWrongFormatMessage();
    }

    @Test
    void shouldDontSuccessByCreditMonthWithNulls(){
        val dashboardPage = new DashboardPage();
        val paymentFormCredit = new PaymentFormCredit();
        dashboardPage.getCreditPayment();
        val cardNumber = DataHelper.getApprovedCardNumber();
        val month = DataHelper.getMonthWithNulls();
        val year = DataHelper.getYear();
        val cvccvv = DataHelper.getCorrectCVCCVV();
        val owner = DataHelper.getValidOwner();
        paymentFormCredit.fillingFieldsFormat(cardNumber, month, year,cvccvv, owner);
        paymentFormCredit.checkWrongFormatMessage();
    }

    //Year

    @Test
    void shouldDontSuccessByCreditWithLastYear(){
        val dashboardPage = new DashboardPage();
        val paymentFormCredit = new PaymentFormCredit();
        dashboardPage.getCreditPayment();
        val cardNumber = DataHelper.getApprovedCardNumber();
        val month = DataHelper.getMonth();
        val year = DataHelper.getLastYear();
        val cvccvv = DataHelper.getCorrectCVCCVV();
        val owner = DataHelper.getValidOwner();
        paymentFormCredit.fillingFieldsFormat(cardNumber, month, year,cvccvv, owner);
        paymentFormCredit.checkCardExpiredMessage();
    }

    @Test
    void shouldDontSuccessByCreditWithEmptyFieldYear() {
        val dashboardPage = new DashboardPage();
        val paymentFormCredit = new PaymentFormCredit();
        dashboardPage.getCreditPayment();
        val cardNumber = DataHelper.getApprovedCardNumber();
        val month = DataHelper.getMonth();
        val year = DataHelper.getEmptyYear();
        val cvccvv = DataHelper.getCorrectCVCCVV();
        val owner = DataHelper.getValidOwner();
        paymentFormCredit.fillingFieldsFormat(cardNumber, month, year, cvccvv, owner);
        paymentFormCredit.checkWrongFormatMessage();
    }
    @Test
    void shouldSuccessByCreditCardExpiryDate(){
        val dashboardPage = new DashboardPage();
        val paymentFormCredit = new PaymentFormCredit();
        dashboardPage.getCreditPayment();
        val cardNumber = DataHelper.getApprovedCardNumber();
        val month = DataHelper.getMonth();
        val year = DataHelper.getCardExpiryDate();
        val cvccvv = DataHelper.getCorrectCVCCVV();
        val owner = DataHelper.getValidOwner();
        paymentFormCredit.fillingFieldsFormat(cardNumber, month, year, cvccvv, owner);
        paymentFormCredit.checkSuccessNotification();

    }

    @Test
    void shouldDontSuccessByCreditAfterCardExpiryDate(){
        val dashboardPage = new DashboardPage();
        val paymentFormCredit = new PaymentFormCredit();
        dashboardPage.getCreditPayment();
        val cardNumber = DataHelper.getApprovedCardNumber();
        val month = DataHelper.getMonth();
        val year = DataHelper.getAfterCardExpiryDate();
        val cvccvv = DataHelper.getCorrectCVCCVV();
        val owner = DataHelper.getValidOwner();
        paymentFormCredit.fillingFieldsFormat(cardNumber, month, year, cvccvv, owner);
        paymentFormCredit.checkWrongCardExpirationMessage();
    }


    //CVC/CVV

    @Test
    void shouldDontSuccessByCreditCvccvvWithTwoNumbers(){
        val dashboardPage = new DashboardPage();
        val paymentFormCredit = new PaymentFormCredit();
        dashboardPage.getCreditPayment();
        val cardNumber = DataHelper.getApprovedCardNumber();
        val month = DataHelper.getMonth();
        val year = DataHelper.getYear();
        val cvccvv = DataHelper.getCVCCVVWithTwoNumbers();
        val owner = DataHelper.getValidOwner();
        paymentFormCredit.fillingFieldsFormat(cardNumber, month, year, cvccvv, owner);
        paymentFormCredit.checkWrongFormatMessage();
    }

    @Test
    void shouldDontSuccessByCreditCvccvvWithOneNumber(){
        val dashboardPage = new DashboardPage();
        val paymentFormCredit = new PaymentFormCredit();
        dashboardPage.getCreditPayment();
        val cardNumber = DataHelper.getApprovedCardNumber();
        val month = DataHelper.getMonth();
        val year = DataHelper.getYear();
        val cvccvv = DataHelper.getCVCCVVWithOneNumber();
        val owner = DataHelper.getValidOwner();
        paymentFormCredit.fillingFieldsFormat(cardNumber, month, year, cvccvv, owner);
        paymentFormCredit.checkWrongFormatMessage();
    }

    @Test
    void shouldDontSuccessByCreditWithEmptyFieldCvccvv(){
        val dashboardPage = new DashboardPage();
        val paymentFormCredit = new PaymentFormCredit();
        dashboardPage.getCreditPayment();
        val cardNumber = DataHelper.getApprovedCardNumber();
        val month = DataHelper.getMonth();
        val year = DataHelper.getYear();
        val cvccvv = DataHelper.getEmptyCVCCVV();
        val owner = DataHelper.getValidOwner();
        paymentFormCredit.fillingFieldsFormat(cardNumber, month, year, cvccvv, owner);
        paymentFormCredit.checkWrongFormatMessage();
    }

    //Owner

    @Test
    void shouldDontSuccessByCreditOwnerWithOneLetter(){
        val dashboardPage = new DashboardPage();
        val paymentFormCredit = new PaymentFormCredit();
        dashboardPage.getCreditPayment();
        val cardNumber = DataHelper.getApprovedCardNumber();
        val month = DataHelper.getMonth();
        val year = DataHelper.getYear();
        val cvccvv = DataHelper.getCorrectCVCCVV();
        val owner = DataHelper.getOwnerWithOneEngLetter();
        paymentFormCredit.fillingFieldsFormat(cardNumber, month, year, cvccvv, owner);
        paymentFormCredit.checkWrongFormatMessage();
    }

    @Test
    void shouldDontSuccessByCreditOwnerWithRusLetters(){
        val dashboardPage = new DashboardPage();
        val paymentFormCredit = new PaymentFormCredit();
        dashboardPage.getCreditPayment();
        val cardNumber = DataHelper.getApprovedCardNumber();
        val month = DataHelper.getMonth();
        val year = DataHelper.getYear();
        val cvccvv = DataHelper.getCorrectCVCCVV();
        val owner = DataHelper.getOwnerWithRusLetters();
        paymentFormCredit.fillingFieldsFormat(cardNumber, month, year, cvccvv, owner);
        paymentFormCredit.checkWrongFormatMessage();
    }

    @Test
    void shouldDontSuccessByCreditOwnerWithNumbers(){
        val dashboardPage = new DashboardPage();
        val paymentFormCredit = new PaymentFormCredit();
        dashboardPage.getCreditPayment();
        val cardNumber = DataHelper.getApprovedCardNumber();
        val month = DataHelper.getMonth();
        val year = DataHelper.getYear();
        val cvccvv = DataHelper.getCorrectCVCCVV();
        val owner = DataHelper.getOwnerWithNumbers();
        paymentFormCredit.fillingFieldsFormat(cardNumber, month, year, cvccvv, owner);
        paymentFormCredit.checkWrongFormatMessage();
    }

    @Test
    void shouldDontSuccessByCreditOwnerWithSpecialSymbols(){
        val dashboardPage = new DashboardPage();
        val paymentFormCredit = new PaymentFormCredit();
        dashboardPage.getCreditPayment();
        val cardNumber = DataHelper.getApprovedCardNumber();
        val month = DataHelper.getMonth();
        val year = DataHelper.getYear();
        val cvccvv = DataHelper.getCorrectCVCCVV();
        val owner = DataHelper.getOwnerWithSpecialSymbols();
        paymentFormCredit.fillingFieldsFormat(cardNumber, month, year, cvccvv, owner);
        paymentFormCredit.checkWrongFormatMessage();
    }

    @Test
    void shouldDontSuccessByCreditWithEmptyFieldOwner(){
        val dashboardPage = new DashboardPage();
        val paymentFormCredit = new PaymentFormCredit();
        dashboardPage.getCreditPayment();
        val cardNumber = DataHelper.getApprovedCardNumber();
        val month = DataHelper.getMonth();
        val year = DataHelper.getYear();
        val cvccvv = DataHelper.getCorrectCVCCVV();
        val owner = DataHelper.getEmptyOwner();
        paymentFormCredit.fillingFieldsFormat(cardNumber, month, year, cvccvv, owner);
        paymentFormCredit.checkEmptyFieldMessage();
    }

}
