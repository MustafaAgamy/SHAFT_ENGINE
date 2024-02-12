package testPackage.unitTests;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestGoogleAds {
    private SHAFT.GUI.WebDriver driver;
    private final By products_link = By.cssSelector("a[href='/products']");
    private final By featuredItems_div = By.cssSelector("div.features_items");
    private final By recommendedItems_div = By.cssSelector("div.recommended_items");
    private final By productsPageTitle_div = By.xpath("//h2[@class='title text-center' and text()='All Products']");
    private By viewProduct_link(String productName) { return By.xpath("(//p[text()='" + productName + "'])[1]//ancestor::div[@class='product-image-wrapper']//child::a[contains(@href,'/product')]");}
    private final By productDetails_div = By.className("product-details");
    private final By productName_h2 = By.xpath("//div[@class='product-information']//h2");
    private final By productCategory_p = By.xpath("//div[@class='col-sm-7']//p[1]");
    private final By productAvailability_p = By.xpath("//div[@class='col-sm-7']//p[2]");
    private final By productCondition_p = By.xpath("//div[@class='col-sm-7']//p[3]");
    private final By productBrand_p = By.xpath("//div[@class='col-sm-7']//p[4]");
    private final By productPrice_span = By.xpath("//div[@class='col-sm-7']//span//span");
    private static final By headerElement_header = By.tagName("header");


    @Test(description = "Verify The Product and the Details on ProductDetails Page")
    @Description("Given I open Automation Exercise home, When I open Products, And I pick a Product, Then I am able to check the Product Details")
    public void VerifyAllProductsAndProductDetailPage() {
                clickOnProductsLink()
                .VerifyProductPageTitleVisibility()
                .pickProduct("Blue Top")
                .verifyProductDetailsPageVisibility()
                .verifyProductDetails("Blue Top", "Women > Tops", "Rs. 500",
                        "In Stock", "New", "Polo");
    }

    @BeforeMethod
    public void beforeMethod() {
        driver = new SHAFT.GUI.WebDriver();
        driver.browser().maximizeWindow();
                navigate()
                .validateOnVisibilityOfHomePage();
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }


    //////////////////// Actions \\\\\\\\\\\\\\\\\\\\
    @Step("Navigate to Home Page")
    public TestGoogleAds navigate() {
        driver.browser().navigateToURL("https://automationexercise.com");
        return this;
    }
    @Step("Click on Products Page Link")
    public TestGoogleAds clickOnProductsLink() {
        driver.element().clickUsingJavascript(products_link);
        dismissAlert(driver);
        return this;
    }
    @Step("Pick Product")
    public TestGoogleAds pickProduct(String productName){
        driver.element().click(viewProduct_link(productName));
        dismissAlert(driver);
        return this;
    }
    /////////////////// Validations \\\\\\\\\\\\\\\\\\\\
    @Step("Validate on Visibility of the Home Page")
    public TestGoogleAds validateOnVisibilityOfHomePage() {
        driver.verifyThat().element(featuredItems_div).exists().perform();
        driver.verifyThat().element(recommendedItems_div).exists().perform();
        return this;
    }

    @Step("Validate on Visibility of the Products Page Title")
    public TestGoogleAds VerifyProductPageTitleVisibility(){
        driver.assertThat().element(productsPageTitle_div).isVisible().perform();
        return this;
    }

    @Step("Verify Product Details Page Visibility")
    public TestGoogleAds verifyProductDetailsPageVisibility(){
        driver.assertThat().element(productDetails_div).isVisible().perform();
        return this;
    }

    @Step("Verify Product Details")
    public TestGoogleAds verifyProductDetails(String productName, String productCategory, String productPrice, String productAvailability
            , String productionCondition, String productBrand ){
        driver.assertThat().element(productName_h2).text().isEqualTo(productName).perform();
        driver.assertThat().element(productCategory_p).text().contains(productCategory).perform();
        driver.assertThat().element(productPrice_span).text().isEqualTo(productPrice).perform();
        driver.assertThat().element(productAvailability_p).textTrimmed().contains(productAvailability).perform();
        driver.assertThat().element(productCondition_p).textTrimmed().contains(productionCondition).perform();
        driver.assertThat().element(productBrand_p).textTrimmed().contains(productBrand).perform();
        return this;
    }

    /////////////////// Utilities \\\\\\\\\\\\\\\\\\\\
    public TestGoogleAds dismissAlert(SHAFT.GUI.WebDriver driver){
        driver.element().doubleClick(headerElement_header);
//        new Actions(driver.getDriver()).doubleClick().perform();
        return this;
    }
}
