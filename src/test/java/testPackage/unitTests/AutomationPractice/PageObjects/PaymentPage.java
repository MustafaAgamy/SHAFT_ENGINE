package testPackage.unitTests.AutomationPractice.PageObjects;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class PaymentPage {
    private SHAFT.GUI.WebDriver driver;

    private String url = "https://automationexercise.com" + "/payment";
    private final By cardName_input = By.xpath("//div//input[@class=\"form-control\"]");
    private final By cardNumber_input = By.xpath("//div//input[@class=\"form-control card-number\"]");
    private final By cvc_input = By.xpath("//div//input[@class=\"form-control card-cvc\"]");
    private final By cardExpMonth_input = By.xpath("//div//input[@class=\"form-control card-expiry-month\"]");
    private final By cardExpYear_input = By.xpath("//div//input[@class=\"form-control card-expiry-year\"]");
    private final By payOrder_Btn = By.xpath("//button[@class=\"form-control btn btn-primary submit-button\"]");
    private final By delete_Btn = By.cssSelector("a[href='/delete_account']");
    public PaymentPage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }
    public PaymentPage navigate() {
        driver.browser().navigateToURL(url);
        return this;
    }
    @Step("Enter payment details: Name on Card, Card Number, CVC, Expiration date")
    public PaymentPage enterPaymentDetails(String User , String Number , String CVC , String ExpMonth , String ExpYear) {
        driver.element().type(cardName_input, User);
        driver.element().type(cardNumber_input, Number);
        driver.element().type(cvc_input, CVC);
        driver.element().type(cardExpMonth_input, ExpMonth);
        driver.element().type(cardExpYear_input, ExpYear);
        return this;
    }
    public PaymentPage clickOnPayOrder() {
        driver.element().click(payOrder_Btn);
        return this;
    }
    public PaymentPage DelteAccount() {
        driver.element().click(delete_Btn);
        return this;
    }

}
