package testPackage.unitTests;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestUsingClickWithFlagSafari {
    private SHAFT.GUI.WebDriver driver;
    private final By featuredItems_div = By.cssSelector("div.features_items");
    private final By recommendedItems_div = By.cssSelector("div.recommended_items");
    private final By productAddedToCartMessage_div = By.cssSelector("div.modal-content > div > h4");
    private final By proceedToCheckout_btn = By.cssSelector(".btn.btn-default.check_out");
    private final By productName_h4 = By.xpath("//td[@class='cart_description']//h4");
    private final By viewCart_a = By.xpath("//div[contains(@class,'confirm')]//a[@href='/view_cart']");

    @Test
    public void addToCartFromRecommendedItems(){
                openRecommendedSection()
                .verifyRecommendedSectionVisibility()
                .addToCart("Blue Top")
                .verifyProductAddedToCart("Added!")
                .openCart()
                .verifyCartPageIsLoaded()
                .verifyProductAddedToCart("Blue Top");
    }

    @BeforeMethod
    public void setUp(){
        driver = new SHAFT.GUI.WebDriver();
                navigate()
                .validateOnVisibilityOfHomePage();
    }

    @AfterMethod
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }


    //////////////////// Actions \\\\\\\\\\\\\\\\\\\\
    @Step("Navigate to Home Page")
    public TestUsingClickWithFlagSafari navigate() {
        driver.browser().navigateToURL("https://automationexercise.com");
        return this;
    }

    @Step("Navigate Recommended Section Tab")
    public TestUsingClickWithFlagSafari openRecommendedSection(){
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver.getDriver();
        WebElement webElement = driver.getDriver().findElement(recommendedItems_div);
        javascriptExecutor.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})",webElement);
        Actions actions = new Actions(driver.getDriver());
        actions.pause(2000).moveToElement(webElement).perform();
        return this;
    }

    @Step("Add Recommended Product To Cart")
    public TestUsingClickWithFlagSafari addToCart(String productName){
        driver.element().click(addProductToCart_a(productName));
        return this;
    }

    private By addProductToCart_a(String productName){
        return By.xpath("//div[@class='recommended_items']//child::p[text()='" + productName + "']//parent::div//a");
    }

    @Step("Open Cart Page")
    public TestUsingClickWithFlagSafari openCart(){
        driver.element().clickUsingJavascript(viewCart_a);
        return this;
    }
    //////////////////// Validations \\\\\\\\\\\\\\\\\\\\
    @Step("Validate on Visibility of the Home Page")
    public TestUsingClickWithFlagSafari validateOnVisibilityOfHomePage() {
        driver.verifyThat().element(featuredItems_div).exists().perform();
        driver.verifyThat().element(recommendedItems_div).exists().perform();
        return this;
    }

    @Step("Validate on Visibility of Recommended Section")
    public TestUsingClickWithFlagSafari verifyRecommendedSectionVisibility(){
        driver.assertThat().element(recommendedItems_div).isVisible().perform();
        return this;
    }

    @Step("Validate on Visibility of Successful Add To Cart Message")
    public TestUsingClickWithFlagSafari verifyProductAddedToCart(String message){
        driver.assertThat().element(productAddedToCartMessage_div).textTrimmed().isEqualTo(message).perform();
        return this;
    }

    @Step("Verify Cart Page is Loaded")
    public TestUsingClickWithFlagSafari verifyCartPageIsLoaded(){
        driver.verifyThat().element(proceedToCheckout_btn).isVisible().perform();
        return this;
    }

    @Step("Validate on Product Added To Cart Page")
    public TestUsingClickWithFlagSafari VerifyProductExistsInCart(String addedProductName){
        driver.assertThat().element(productName_h4).text().isEqualTo(addedProductName).perform();
        return this;
    }
}
