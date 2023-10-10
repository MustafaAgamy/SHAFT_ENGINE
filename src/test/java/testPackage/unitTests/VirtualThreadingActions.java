package testPackage.unitTests;

import com.shaft.driver.SHAFT;
import com.shaft.gui.element.ElementActions;
import lombok.SneakyThrows;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class VirtualThreadingActions {

    private List <java.util.concurrent.Callable<ElementActions>> actionsList = new ArrayList<>();
    private final SHAFT.GUI.WebDriver driver = new SHAFT.GUI.WebDriver();
    private final By registerBtn = By.className("ico-register");


    public VirtualThreadingActions async(){
        return this;
    }

    public VirtualThreadingActions typeVirtual(By locator, String value) {
        addActionBuilder(() -> driver.element().type(locator, value));
        return this;
    }

    public VirtualThreadingActions clickVirtual(By locator) {
        addActionBuilder(() -> driver.element().click(locator));
        return this;
    }

    public VirtualThreadingActions selectVirtual(By locator, String value) {
        addActionBuilder(() -> driver.element().select(locator, value));
        return this;
    }
    private void addActionBuilder(Callable<ElementActions> action) {
        actionsList.add(action);
    }


    public void build() {
        try (ExecutorService myExecutor = Executors.newVirtualThreadPerTaskExecutor()) {
            List<Future<ElementActions>> actions = myExecutor.invokeAll(actionsList);

            for (Future<ElementActions> action : actions) {
                try {
                    action.get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //    public VirtualThreadingActions executeActions() {
//        // Wait for all actions to complete here if needed
//        for (java.util.concurrent.Callable<ElementActions> action : actionsList) {
//            // Handle the completion or wait for it as necessary
//            try {
//                ElementActions result = action.get();
//                // Handle the result if needed
//            } catch (InterruptedException | ExecutionException e) {
//                // Handle exceptions
//            }
//        }
//        return this;
//    }
}
