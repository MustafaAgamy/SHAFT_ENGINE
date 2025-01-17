package testPackage.mockedTests;

import com.shaft.driver.SHAFT;
import com.shaft.gui.internal.locator.LocatorBuilder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class JDK21VirtualThreadsAndAsyncActionsTests {
    SHAFT.GUI.WebDriver driver;
    String testElement = "data:text/html," +
            "<input id=\"text1\" type=\"text\"><br><br>" +
            "<input id=\"text2\" type=\"text\"><br><br>" +
            "<input id=\"text3\" type=\"text\"><br><br>" +
            "<input id=\"text4\" type=\"text\"><br><br>" +
            "<input id=\"text5\" type=\"text\"><br><br>" +
            "<input id=\"text6\" type=\"text\"><br><br>";
    LocatorBuilder locator = SHAFT.GUI.Locator.hasTagName("input");

    @Test
    public void _2asyncActions() {
        driver.browser().navigateToURL(testElement);
        driver.async().element().type(locator.hasId("text1").build(), "first string")
                .type(locator.hasId("text2").build(), "second string")
                .type(locator.hasId("text3").build(), "third string")
                .type(locator.hasId("text4").build(), "fourth string")
                .type(locator.hasId("text5").build(), "fifth string")
                .type(locator.hasId("text6").build(), "sixth string")
                .synchronize()
                .element().assertThat(locator.hasId("text1").build()).text().isEqualTo("first string").perform();
    }

    @Test
    public void _1syncActions() {
        driver.browser().navigateToURL(testElement);
        driver.element().type(locator.hasId("text1").build(), "first string")
                .type(locator.hasId("text2").build(), "second string")
                .type(locator.hasId("text3").build(), "third string")
                .type(locator.hasId("text4").build(), "fourth string")
                .type(locator.hasId("text5").build(), "fifth string")
                .type(locator.hasId("text6").build(), "sixth string");
        driver.element().assertThat(locator.hasId("text1").build()).text().isEqualTo("first string").perform();
    }

    @BeforeMethod
    public void beforeMethod() {
        driver = new SHAFT.GUI.WebDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        driver.quit();
    }
}