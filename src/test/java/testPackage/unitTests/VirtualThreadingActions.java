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


//    public VirtualThreadingActions actionsThreading(By locator, String value ,String actionToDo){
//        try (ExecutorService myExecutor = Executors.newVirtualThreadPerTaskExecutor()) {
//            java.util.concurrent.Future<ElementActions> action = null;
//            switch (actionToDo.toLowerCase()) {
//                case "type" -> action = myExecutor.submit(() -> driver.element().type(locator,value));
//                case "click" -> action = myExecutor.submit(() -> driver.element().click(locator));
//                case "select" -> action = myExecutor.submit(() -> driver.element().select(locator,value));
//            }
//            actionsList.add(action);
//        }
//        return this;
//    }

//    public VirtualThreadingActions click(By locator){
//        driver.browser().navigateToURL("https://demo.nopcommerce.com/");
//        driver.element().click(registerBtn);
//        java.util.concurrent.Callable<ElementActions> action;
//        try (ExecutorService myExecutor = Executors.newVirtualThreadPerTaskExecutor()) {
//            action = myExecutor.submit(() ->driver.element().click(locator));
//            actionsList.add(action);
//            return this;
//        }
//    }
//
//    public VirtualThreadingActions type(By locator, String value){
//        java.util.concurrent.Future<ElementActions> action;
//        try (ExecutorService myExecutor = Executors.newVirtualThreadPerTaskExecutor()) {
//            action = myExecutor.submit(() ->driver.element().type(locator, value));
//            actionsList.add(action);
//            return this;
//        }
//    }
//
//    public VirtualThreadingActions select(By locator, String textORValue){
//        java.util.concurrent.Future<ElementActions> action;
//        try (ExecutorService myExecutor = Executors.newVirtualThreadPerTaskExecutor()) {
//            action = myExecutor.submit(() ->driver.element().select(locator, textORValue));
//            actionsList.add(action);
//            return this;
//        }
//    }

//    public VirtualThreadingActions perform(){
//        for (java.util.concurrent.Future<ElementActions> action : actionsList) {
//            try {
//                action.get();
//            } catch (InterruptedException | ExecutionException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        return this;
//    }

//    private void addActionBuilderOne(Callable<ElementActions> action) {
//        try (ExecutorService myExecutor = Executors.newVirtualThreadPerTaskExecutor()) {
//            java.util.concurrent.Callable<ElementActions> futureAction = myExecutor.submit(action);
//            actionsList.add(futureAction);
//        }
//    }

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
