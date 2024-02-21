package testPackage.unitTests.AutomationPractice;
import com.shaft.driver.DriverFactory;
import com.shaft.driver.SHAFT;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import testPackage.unitTests.AutomationPractice.PageObjects.*;

import java.io.File;

@Epic("Automation Exercise")
@Feature("Placing Order")
@Story("Place Order : Register Before Checkout")

public class PlaceOrderRegisterBeforeCheckout {
    private SHAFT.TestData.JSON testData;
    private SHAFT.TestData.JSON productData;
    private SHAFT.TestData.JSON CardData;
    private SHAFT.GUI.WebDriver driver;
    FirefoxOptions disableJSOption = new FirefoxOptions();
    FirefoxOptions enableJSOption = new FirefoxOptions();
    @BeforeClass
    public void beforeClass() {
        testData = new SHAFT.TestData.JSON("src/test/resources/testDataFiles/RegisterUser.json");
        productData = new SHAFT.TestData.JSON("src/test/resources/testDataFiles/Product.json");
        CardData = new SHAFT.TestData.JSON("src/test/resources/testDataFiles/CardData.json");
        SHAFT.Properties.flags.set().clickUsingJavascriptWhenWebDriverClickFails(true);
    }
    private  String timeStamp = String.valueOf(System.currentTimeMillis());
    @BeforeMethod
    public void beforeMethod() {
//        FirefoxProfile profile = new FirefoxProfile();
//        profile.addExtension(new File("E:\\Intelliji\\SHAFT_ENGINE_FORK\\src\\test\\resources\\adblockultimate@adblockultimate.net.xpi"));
//        profile.setPreference("extensions.adblockultimate.currentVersion", "3.8.21"); // Replace "x.x.x" with the version number
//        profile.setPreference("extensions.adblockultimate.enabled", true);
//        profile.setPreference("xpinstall.signatures.required", false);
//        FirefoxOptions options = new FirefoxOptions();
//        options.setProfile(profile);
//        firefoxOptions.setProfile(new FirefoxProfile(new File("C:\\Users\\mostafa.agamy\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\rwatp913.default-release")));
//        enableJSOption.addPreference("javascript.enabled", false);
//        enableJSOption.addPreference("javascript.enabled", true);

        driver = new SHAFT.GUI.WebDriver(DriverFactory.DriverType.EDGE);
        new HomePage(driver)
                .navigate()
                .validateOnVisibilityOfHomePage();
    }
    @Test(description = "Place Order: Register before Checkout")
    public void PlaceOrderRegisterBeforeCheckout ()
    {
        new NavigationBar(driver)
                .clickOnSignupLoginLink();
        new SignupLoginPage(driver)
                .validateOnSignUpVisibility(testData.getTestData("Messages.Signup"))
                .newUserSignup(testData.getTestData("UserName"), testData.getTestData("UserMail.GuiTimeStamp") + timeStamp + "@gizasystems.com");
        new SignupPage(driver)
                .validateOnAccountInfoPage(testData.getTestData("Messages.AccountInfo"))
                .enterAccountInformation(testData.getTestData("Gender"), testData.getTestData("UserPassword"), testData.getTestData("UserFirstName"), testData.getTestData("UserLastName"), testData.getTestData("UserBirthDay"), testData.getTestData("UserBirthMonth"), testData.getTestData("UserBirthYear"))
                .enterAddressInformation(testData.getTestData("UserAddress1"), testData.getTestData("UserCountry"), testData.getTestData("UserState"), testData.getTestData("UserCity"), testData.getTestData("UserZipCode"), testData.getTestData("UserMobile"))
                .validateOnAccountCreated(testData.getTestData("Messages.AccountCreated"))
                .clickOnContinueButton();
        new NavigationBar(driver)
                .validateTheLoggedInUser(testData.getTestData("UserName"));
        new ProductsPage(driver)
                .addProductsToCart(1)
                .ClickCartButton();
        new CartPage(driver)
                .navigate()
                .verifyCartPageIsLoaded()
                .verifyProductAddedToCart(productData.getTestData("productName"))
                .proceedToCheckOut();
        new CheckOutPage(driver)
                .navigate()
                .verififyingAddressDetails("Mr. Automation Bot",testData.getTestData("UserAddress1"),testData.getTestData("UserCountry"))
                .enteringDescriptionInCommentArea( "Place Order");
        new PaymentPage(driver)
                .navigate()
                .enterPaymentDetails(CardData.getTestData("CardName"),CardData.getTestData("CardNumber") , CardData.getTestData("CVC"), CardData.getTestData("CardExpMonth"),CardData.getTestData("CardExpYear"))
                .clickOnPayOrder()
                .DelteAccount();
        new DeleteAccountPage(driver)
                .validateAccountDeleted("ACCOUNT DELETED!");
    }
    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }
}