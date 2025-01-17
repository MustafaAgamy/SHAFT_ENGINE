package testPackage.validationsWizard;

import com.shaft.driver.DriverFactory;
import com.shaft.gui.browser.BrowserActions;
import com.shaft.validation.Validations;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Test_BrowserValidations {
    private WebDriver driver;

    @Test
    public void url() {
        Validations.assertThat().browser(driver).url().contains("google.com").perform();
    }

    @Test
    public void title() {
        Validations.assertThat().browser(driver).title().contains("oogle").perform();
    }

    @BeforeMethod
    public void beforeMethod() {
        driver = new DriverFactory().getDriver();
        String url = "https://www.google.com/";
        new BrowserActions(driver).navigateToURL(url);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        driver.quit();
    }
}
