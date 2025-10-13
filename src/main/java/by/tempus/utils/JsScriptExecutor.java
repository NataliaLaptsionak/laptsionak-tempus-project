package by.tempus.utils;

import by.tempus.web.driver.Browser;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class JsScriptExecutor {
    public static void getJavaScriptExecutor(String script, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) Browser.getDriver();
        js.executeScript(script, element);
    }
}
