package data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {
    private DataHelper() {

    }
    @Value
    public static class CardNumber {
        private String cardNumber;
    }
    //CardNumber

    public static CardNumber getApprovedCardNumber() {
        return new CardNumber("4444 4444 4444 4441");
    }
    public static CardNumber getDeclinedCardNumber() {
        return new CardNumber("4444 4444 4444 4442");
    }
    public static CardNumber getInvalidCardNumber() {
        return new CardNumber("4444 4444 4444 4440");
    }
    public static CardNumber getCardWithoutNumber() {
        return new CardNumber("");
    }

    //Month

    public static String getMonth() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
    }
    public static String getInvalidMonth() {
        return "00";
    }
    public static String getEmptyMonth() {
        return "";
    }

    //Year

    public static String getYear() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
    }
    public static String getLastYear(){
        return LocalDate.now().minusYears(1).format(DateTimeFormatter.ofPattern("yy"));
    }
    public static String getEmptyYear(){
        return "";
    }

    //CVC/CVV

    public static String getCorrectCVCCVV() {
        return "321";
    }

    public static String getCVCCVVWithTwoNumbers() {
        return "32";
    }
    public static String getCVCCVVWithOneNumber() {
        return "3";
    }
    public static String getEmptyCVCCVV() {
        return "";
    }

    //Owner

    public static String getValidOwner() {
        Faker faker = new Faker(new Locale("en"));
        return faker.name().firstName() + " " + faker.name().lastName();
    }
    public static String getOneEngLetter(){
        return "Q";
    }
    public static String getOwnerWithRusLetters() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().firstName() + " " + faker.name().lastName();
    }
    public static String getOwnerWithNumbers(){
        return "4321";
    }
    public static String getOwnerWithSpecialSymbols(){
        return "*&^%$";
    }
    public static String getEmptyOwner(){
        return "";
    }
}
