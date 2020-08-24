package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waiters {
    public static final int TIME_OUT_IN_SECONDS = 30;

    public static void waitUntilElementIsVisible(WebDriver driver, WebElement element) {
        new WebDriverWait(driver, Waiters.TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOf(element));
    }
}
