package testPackage.unitTests.AutomationPractice.PageObjects;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class DeleteAccountPage {
    // Variables
    private SHAFT.GUI.WebDriver driver;

    // Locators
    private final By accountDeleted_b = By.cssSelector("h2[data-qa='account-deleted'] > b");

    // Constructor
    public DeleteAccountPage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }

    //////////////////// Actions \\\\\\\\\\\\\\\\\\\\

    //////////////////// Validations \\\\\\\\\\\\\\\\\\\\
    @Step("Validate Account Deleted")
    public DeleteAccountPage validateAccountDeleted(String expectedMessage) {
        driver.verifyThat().element(accountDeleted_b).text().isEqualTo(expectedMessage).perform();
        return this;
    }
}
