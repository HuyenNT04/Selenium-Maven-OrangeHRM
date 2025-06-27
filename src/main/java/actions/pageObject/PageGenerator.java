package actions.pageObject;

import actions.commons.BasePage;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Constructor;

public class PageGenerator {
    public static <T extends BasePage> T getPageInstance(Class<T> pageClass, WebDriver driver){
        try {
            //Get constructor to map driver
            Constructor<T> constructor = pageClass.getConstructor(WebDriver.class);

            //Create instance for page class
            return constructor.newInstance(driver);
        }catch (Exception e){
            throw new RuntimeException("Can not init page class: " + pageClass.getSimpleName(), e);
        }
    }
}
