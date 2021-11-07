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
import page.PaymentFormDebit;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentDebitCardTest {

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
    void shouldPaymentDebitCardWithStatusApproved(){
        val dashboardPage = new DashboardPage();
        val paymentFormDebit = new PaymentFormDebit();
        dashboardPage.getDebitCardPayment();
        val cardNumber = DataHelper.getApprovedCardNumber();
        val month = DataHelper.getMonth();
        val year = DataHelper.getYear();
        val cvccvv = DataHelper.getCorrectCVCCVV();
        val owner = DataHelper.getValidOwner();
        paymentFormDebit.fillingFieldsFormat(cardNumber, month, year,cvccvv, owner);
        paymentFormDebit.checkSuccessNotification();
        val paymentStatus = SqlHelper.getStatusPaymentEntity();
        assertEquals("APPROVED", paymentStatus);
    }

    @Test
    void shouldPaymentDebitCardWithStatusDeclined(){
        val dashboardPage = new DashboardPage();
        val paymentFormDebit = new PaymentFormDebit();
        dashboardPage.getDebitCardPayment();
        val cardNumber = DataHelper.getDeclinedCardNumber();
        val month = DataHelper.getMonth();
        val year = DataHelper.getYear();
        val cvccvv = DataHelper.getCorrectCVCCVV();
        val owner = DataHelper.getValidOwner();
        paymentFormDebit.fillingFieldsFormat(cardNumber, month, year,cvccvv, owner);
        paymentFormDebit.checkErrorNotification();
        val paymentStatus = SqlHelper.getStatusPaymentEntity();
        assertEquals("DECLINES", paymentStatus);
     }

     @Test
    void shouldDontSuccessWithAnotherCard(){
         val dashboardPage = new DashboardPage();
         val paymentFormDebit = new PaymentFormDebit();
         dashboardPage.getDebitCardPayment();
         val cardNumber = DataHelper.getInvalidCardNumber();
         val month = DataHelper.getMonth();
         val year = DataHelper.getYear();
         val cvccvv = DataHelper.getCorrectCVCCVV();
         val owner = DataHelper.getValidOwner();
         paymentFormDebit.fillingFieldsFormat(cardNumber, month, year,cvccvv, owner);
         paymentFormDebit.checkErrorNotification();
     }

     @Test
     void shouldDontSuccessCardNumberWithNulls(){
         val dashboardPage = new DashboardPage();
         val paymentFormDebit = new PaymentFormDebit();
         dashboardPage.getDebitCardPayment();
         val cardNumber = DataHelper.getCardNumberWithNulls();
         val month = DataHelper.getMonth();
         val year = DataHelper.getYear();
         val cvccvv = DataHelper.getCorrectCVCCVV();
         val owner = DataHelper.getValidOwner();
         paymentFormDebit.fillingFieldsFormat(cardNumber, month, year,cvccvv, owner);
         paymentFormDebit.checkErrorNotification();
     }

     @Test
    void shouldDontSuccessWithEmptyFieldCardNumber(){
         val dashboardPage = new DashboardPage();
         val paymentFormDebit = new PaymentFormDebit();
         dashboardPage.getDebitCardPayment();
         val cardNumber = DataHelper.getCardWithoutNumber();
         val month = DataHelper.getMonth();
         val year = DataHelper.getYear();
         val cvccvv = DataHelper.getCorrectCVCCVV();
         val owner = DataHelper.getValidOwner();
         paymentFormDebit.fillingFieldsFormat(cardNumber, month, year,cvccvv, owner);
         paymentFormDebit.checkWrongFormatMessage();
     }

     // Month

    @Test
    void shouldDontSuccessWithInvalidMonth(){
        val dashboardPage = new DashboardPage();
        val paymentFormDebit = new PaymentFormDebit();
        dashboardPage.getDebitCardPayment();
        val cardNumber = DataHelper.getApprovedCardNumber();
        val month = DataHelper.getInvalidMonth();
        val year = DataHelper.getYear();
        val cvccvv = DataHelper.getCorrectCVCCVV();
        val owner = DataHelper.getValidOwner();
        paymentFormDebit.fillingFieldsFormat(cardNumber, month, year,cvccvv, owner);
        paymentFormDebit.checkWrongCardExpirationMessage();
    }

    @Test
    void shouldDontSuccessWithEmptyFieldMonth(){
        val dashboardPage = new DashboardPage();
        val paymentFormDebit = new PaymentFormDebit();
        dashboardPage.getDebitCardPayment();
        val cardNumber = DataHelper.getApprovedCardNumber();
        val month = DataHelper.getEmptyMonth();
        val year = DataHelper.getYear();
        val cvccvv = DataHelper.getCorrectCVCCVV();
        val owner = DataHelper.getValidOwner();
        paymentFormDebit.fillingFieldsFormat(cardNumber, month, year,cvccvv, owner);
        paymentFormDebit.checkWrongFormatMessage();
    }

    @Test
    void shouldDontSuccessMonthWithNulls(){
        val dashboardPage = new DashboardPage();
        val paymentFormDebit = new PaymentFormDebit();
        dashboardPage.getDebitCardPayment();
        val cardNumber = DataHelper.getApprovedCardNumber();
        val month = DataHelper.getMonthWithNulls();
        val year = DataHelper.getYear();
        val cvccvv = DataHelper.getCorrectCVCCVV();
        val owner = DataHelper.getValidOwner();
        paymentFormDebit.fillingFieldsFormat(cardNumber, month, year,cvccvv, owner);
        paymentFormDebit.checkWrongFormatMessage();
    }

    //Year

    @Test
    void shouldDontSuccessWithLastYear(){
        val dashboardPage = new DashboardPage();
        val paymentFormDebit = new PaymentFormDebit();
        dashboardPage.getDebitCardPayment();
        val cardNumber = DataHelper.getApprovedCardNumber();
        val month = DataHelper.getMonth();
        val year = DataHelper.getLastYear();
        val cvccvv = DataHelper.getCorrectCVCCVV();
        val owner = DataHelper.getValidOwner();
        paymentFormDebit.fillingFieldsFormat(cardNumber, month, year,cvccvv, owner);
        paymentFormDebit.checkCardExpiredMessage();
    }

    @Test
    void shouldDontSuccessWithEmptyFieldYear() {
        val dashboardPage = new DashboardPage();
        val paymentFormDebit = new PaymentFormDebit();
        dashboardPage.getDebitCardPayment();
        val cardNumber = DataHelper.getApprovedCardNumber();
        val month = DataHelper.getMonth();
        val year = DataHelper.getEmptyYear();
        val cvccvv = DataHelper.getCorrectCVCCVV();
        val owner = DataHelper.getValidOwner();
        paymentFormDebit.fillingFieldsFormat(cardNumber, month, year, cvccvv, owner);
        paymentFormDebit.checkWrongFormatMessage();
    }

    @Test
    void shouldSuccessCardExpiryDate(){
        val dashboardPage = new DashboardPage();
        val paymentFormDebit = new PaymentFormDebit();
        dashboardPage.getDebitCardPayment();
        val cardNumber = DataHelper.getApprovedCardNumber();
        val month = DataHelper.getMonth();
        val year = DataHelper.getCardExpiryDate();
        val cvccvv = DataHelper.getCorrectCVCCVV();
        val owner = DataHelper.getValidOwner();
        paymentFormDebit.fillingFieldsFormat(cardNumber, month, year, cvccvv, owner);
        paymentFormDebit.checkSuccessNotification();

    }

    @Test
    void shouldDontSuccessAfterCardExpiryDate(){
        val dashboardPage = new DashboardPage();
        val paymentFormDebit = new PaymentFormDebit();
        dashboardPage.getDebitCardPayment();
        val cardNumber = DataHelper.getApprovedCardNumber();
        val month = DataHelper.getMonth();
        val year = DataHelper.getAfterCardExpiryDate();
        val cvccvv = DataHelper.getCorrectCVCCVV();
        val owner = DataHelper.getValidOwner();
        paymentFormDebit.fillingFieldsFormat(cardNumber, month, year, cvccvv, owner);
        paymentFormDebit.checkWrongCardExpirationMessage();
    }

    //CVC/CVV

    @Test
    void shouldDontSuccessCvccvvWithTwoNumbers(){
        val dashboardPage = new DashboardPage();
        val paymentFormDebit = new PaymentFormDebit();
        dashboardPage.getDebitCardPayment();
        val cardNumber = DataHelper.getApprovedCardNumber();
        val month = DataHelper.getMonth();
        val year = DataHelper.getYear();
        val cvccvv = DataHelper.getCVCCVVWithTwoNumbers();
        val owner = DataHelper.getValidOwner();
        paymentFormDebit.fillingFieldsFormat(cardNumber, month, year, cvccvv, owner);
        paymentFormDebit.checkWrongFormatMessage();
    }

    @Test
    void shouldDontSuccessCvccvvWithOneNumber(){
        val dashboardPage = new DashboardPage();
        val paymentFormDebit = new PaymentFormDebit();
        dashboardPage.getDebitCardPayment();
        val cardNumber = DataHelper.getApprovedCardNumber();
        val month = DataHelper.getMonth();
        val year = DataHelper.getYear();
        val cvccvv = DataHelper.getCVCCVVWithOneNumber();
        val owner = DataHelper.getValidOwner();
        paymentFormDebit.fillingFieldsFormat(cardNumber, month, year, cvccvv, owner);
        paymentFormDebit.checkWrongFormatMessage();
    }

    @Test
    void shouldDontSuccessWithEmptyFieldCvccvv(){
        val dashboardPage = new DashboardPage();
        val paymentFormDebit = new PaymentFormDebit();
        dashboardPage.getDebitCardPayment();
        val cardNumber = DataHelper.getApprovedCardNumber();
        val month = DataHelper.getMonth();
        val year = DataHelper.getYear();
        val cvccvv = DataHelper.getEmptyCVCCVV();
        val owner = DataHelper.getValidOwner();
        paymentFormDebit.fillingFieldsFormat(cardNumber, month, year, cvccvv, owner);
        paymentFormDebit.checkWrongFormatMessage();
    }

    //Owner

    @Test
    void shouldDontSuccessOwnerWithOneLetter(){
        val dashboardPage = new DashboardPage();
        val paymentFormDebit = new PaymentFormDebit();
        dashboardPage.getDebitCardPayment();
        val cardNumber = DataHelper.getApprovedCardNumber();
        val month = DataHelper.getMonth();
        val year = DataHelper.getYear();
        val cvccvv = DataHelper.getCorrectCVCCVV();
        val owner = DataHelper.getOwnerWithOneEngLetter();
        paymentFormDebit.fillingFieldsFormat(cardNumber, month, year, cvccvv, owner);
        paymentFormDebit.checkWrongFormatMessage();
    }

    @Test
    void shouldDontSuccessOwnerWithRusLetters(){
        val dashboardPage = new DashboardPage();
        val paymentFormDebit = new PaymentFormDebit();
        dashboardPage.getDebitCardPayment();
        val cardNumber = DataHelper.getApprovedCardNumber();
        val month = DataHelper.getMonth();
        val year = DataHelper.getYear();
        val cvccvv = DataHelper.getCorrectCVCCVV();
        val owner = DataHelper.getOwnerWithRusLetters();
        paymentFormDebit.fillingFieldsFormat(cardNumber, month, year, cvccvv, owner);
        paymentFormDebit.checkWrongFormatMessage();
    }

    @Test
    void shouldDontSuccessOwnerWithNumbers(){
        val dashboardPage = new DashboardPage();
        val paymentFormDebit = new PaymentFormDebit();
        dashboardPage.getDebitCardPayment();
        val cardNumber = DataHelper.getApprovedCardNumber();
        val month = DataHelper.getMonth();
        val year = DataHelper.getYear();
        val cvccvv = DataHelper.getCorrectCVCCVV();
        val owner = DataHelper.getOwnerWithNumbers();
        paymentFormDebit.fillingFieldsFormat(cardNumber, month, year, cvccvv, owner);
        paymentFormDebit.checkWrongFormatMessage();
    }

    @Test
    void shouldDontSuccessOwnerWithSpecialSymbols(){
        val dashboardPage = new DashboardPage();
        val paymentFormDebit = new PaymentFormDebit();
        dashboardPage.getDebitCardPayment();
        val cardNumber = DataHelper.getApprovedCardNumber();
        val month = DataHelper.getMonth();
        val year = DataHelper.getYear();
        val cvccvv = DataHelper.getCorrectCVCCVV();
        val owner = DataHelper.getOwnerWithSpecialSymbols();
        paymentFormDebit.fillingFieldsFormat(cardNumber, month, year, cvccvv, owner);
        paymentFormDebit.checkWrongFormatMessage();
    }

    @Test
    void shouldDontSuccessWithEmptyFieldOwner(){
        val dashboardPage = new DashboardPage();
        val paymentFormDebit = new PaymentFormDebit();
        dashboardPage.getDebitCardPayment();
        val cardNumber = DataHelper.getApprovedCardNumber();
        val month = DataHelper.getMonth();
        val year = DataHelper.getYear();
        val cvccvv = DataHelper.getCorrectCVCCVV();
        val owner = DataHelper.getEmptyOwner();
        paymentFormDebit.fillingFieldsFormat(cardNumber, month, year, cvccvv, owner);
        paymentFormDebit.checkEmptyFieldMessage();
    }
}

