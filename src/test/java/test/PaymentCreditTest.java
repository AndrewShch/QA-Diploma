package test;

import data.DataHelper;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.DashboardPage;
import page.PaymentFormCredit;

import static com.codeborne.selenide.Selenide.open;

public class PaymentCreditTest {

    @BeforeEach
    void setUp(){
        open("http://185.119.57.64:8080/");
    }

    //Card





    // Month

    @Test
    void shouldDontSuccessWithInvalidMonth(){
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
    void shouldDontSuccessWithEmptyFieldMonth(){
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

    //Year

    @Test
    void shouldDontSuccessWithLastYear(){
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
    void shouldDontSuccessWithEmptyFieldYear() {
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

    //CVC/CVV

    @Test
    void shouldDontSuccessCvccvvWithTwoNumbers(){
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
    void shouldDontSuccessCvccvvWithOneNumber(){
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
    void shouldDontSuccessWithEmptyFieldCvccvv(){
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
    void shouldDontSuccessOwnerWithOneLetter(){
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
    void shouldDontSuccessOwnerWithRusLetters(){
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
    void shouldDontSuccessOwnerWithNumbers(){
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
    void shouldDontSuccessOwnerWithSpecialSymbols(){
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
    void shouldDontSuccessWithEmptyFieldOwner(){
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
