package testPackage.unitTests.AutomationPractice.PageObjects;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class HomePage {
    // Variables
    private SHAFT.GUI.WebDriver driver;
    private String url = "https://automationexercise.com";

    // Locators
    private final By featuredItems_div = By.cssSelector("div.features_items");
    private final By recommendedItems_div = By.cssSelector("div.recommended_items");
    public By getCategoryTitleLocator(String categoryTitle ){
        return  By.xpath("//h2[text()='"+ categoryTitle +"']");
    }

    // Constructor
    public HomePage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }

    //////////////////// Actions \\\\\\\\\\\\\\\\\\\\
    @Step("Navigate to Home Page")
    public HomePage navigate() {
        driver.browser().navigateToURL(url);
        return this;
    }

    //////////////////// Validations \\\\\\\\\\\\\\\\\\\\
    @Step("Validate on Visibility of the Home Page")
    public HomePage validateOnVisibilityOfHomePage() {
        driver.verifyThat().element(featuredItems_div).exists().perform();
        driver.verifyThat().element(recommendedItems_div).exists().perform();
        return this;
    }

    @Step("Validate on Visibility of the Category Title")
    public HomePage validateOnVisibilityOfCategoryTitle(String categoryTitle) {
        driver.verifyThat().element(getCategoryTitleLocator(categoryTitle)).exists().perform();
        return this;
    }

}
