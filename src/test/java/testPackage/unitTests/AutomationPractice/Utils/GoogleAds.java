package testPackage.unitTests.AutomationPractice.Utils;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;

public class GoogleAds {

    public static GoogleAds handleGoogleAd(SHAFT.GUI.WebDriver driver, By locator){
        driver.browser().refreshCurrentPage();
        try {
            driver.element().click(locator);

        } catch (Throwable e){
            //empty (Expected to be caught in case if the Ad didn't get triggered)
        }

        return new GoogleAds();
    }
}
