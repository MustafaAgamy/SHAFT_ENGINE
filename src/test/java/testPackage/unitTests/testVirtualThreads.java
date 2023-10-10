package testPackage.unitTests;

import com.shaft.driver.SHAFT;
import com.shaft.gui.element.ElementActions;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Consumer;

public class testVirtualThreads {
    private SHAFT.GUI.WebDriver driver;
    private final By registerBtn = By.className("ico-register");
    private final By genderMale = By.id("gender-male");
    private final By genderFemale = By.id("gender-female");
    private final By firstNameField = By.id("FirstName");
    private final By lastNameField = By.id("LastName");
    private final By birthDayField = By.name("DateOfBirthDay");
    private final By birthMonthField = By.name("DateOfBirthMonth");
    private final By birthYearField = By.name("DateOfBirthYear");
    private final By emailField = By.id("Email");
    private final By passwordField = By.id("Password");
    private final By confPasswordField = By.id("ConfirmPassword");
    private final By confRegisterBtn = By.id("register-button");



//    @BeforeMethod
//    public void beforeMethod() {
//        driver = new SHAFT.GUI.WebDriver();
//        driver.browser().navigateToURL("https://demo.nopcommerce.com/");
//    }


    @Test
    public void testVirtualThreadsArrayList(){
       VirtualThreadingActions virtualThreadingActions = new VirtualThreadingActions();
       virtualThreadingActions
               .async()
               .clickVirtual(genderMale).typeVirtual(firstNameField,"Mostafa")
               .typeVirtual(lastNameField,"Agamy").selectVirtual(birthDayField,"7")
               .selectVirtual(birthMonthField,"July").selectVirtual(birthYearField,"1995")
               .typeVirtual(emailField,"mostafaA1995@gmail.com").typeVirtual(passwordField,"mostafa111")
               .typeVirtual(confPasswordField,"mostafa111")
               .build();
    }
    @Test
    public void testVirtualThreads(){
        driver.element().click(registerBtn);

        try (ExecutorService myExecutor = Executors.newVirtualThreadPerTaskExecutor()) {
            var action1 = myExecutor.submit(() -> driver.element().click(genderMale));
            var action2 = myExecutor.submit(() -> driver.element().type(firstNameField,"Mostafa"));
            var action3 = myExecutor.submit(() -> driver.element().type(lastNameField,"Agamy"));
            var action4 = myExecutor.submit(() -> driver.element().select(birthDayField,"7"));
            var action5 = myExecutor.submit(() -> driver.element().select(birthMonthField,"July"));
            var action6 = myExecutor.submit(() -> driver.element().select(birthYearField,"1995"));
            var action7 = myExecutor.submit(() -> driver.element().type(emailField,"mostafaA1995@gmail.com"));
            var action8 = myExecutor.submit(() -> driver.element().type(passwordField,"mostafa111"));
            var action9 = myExecutor.submit(() -> driver.element().type(confPasswordField,"mostafa111"));


            //synchronization point
            action1.get();
            action2.get();
            action3.get();
            action4.get();
            action5.get();
            action6.get();
            action7.get();
            action8.get();
            action9.get();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void virtualThreads_2_oneService() throws InterruptedException {
        driver.element().click(registerBtn);
        try (ExecutorService myExecutor = Executors.newVirtualThreadPerTaskExecutor()) {
            myExecutor.submit(() -> {
                driver.element().click(genderMale);
                driver.element().type(firstNameField,"Mostafa");
                driver.element().type(lastNameField,"Agamy");
                driver.element().select(birthDayField,"7");
                driver.element().select(birthMonthField,"July");
                driver.element().select(birthYearField,"1995");
                driver.element().type(emailField,"mostafaA1995@gmail.com");
                driver.element().type(passwordField,"mostafa111");
                driver.element().type(confPasswordField,"mostafa111");
            }).get();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        if (driver != null)
            driver.quit();
    }
}
