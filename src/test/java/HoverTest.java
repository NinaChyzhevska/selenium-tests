import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class HoverTest {

    private static WebDriver driver;

    @BeforeAll
    public static void browserSetUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        String URL = "http://demoqa.com/tool-tips";
        driver.get(URL);
    }

    @Test
    public void hoverButtonTest() {
        Actions action = new Actions(driver);
        WebElement button = driver.findElement(By.id("toolTipButton"));

        action.moveToElement(button).perform();

        List<WebElement> buttonTooltip = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("buttonToolTip")));
        assertFalse(buttonTooltip.isEmpty());
    }

    @Test
    public void hoverTextFieldTest() {
        Actions action = new Actions(driver);
        WebElement textField = driver.findElement(By.id("toolTipTextField"));
        action.moveToElement(textField).perform();

        List<WebElement> textFieldToolTip = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("textFieldToolTip")));
        assertFalse(textFieldToolTip.isEmpty());
    }

    @Test
    public void hoverTheFirstLinkTest() {
        Actions action = new Actions(driver);
        WebElement link = driver.findElement(By.xpath("//*[@id='texToolTopContainer']//*[text()='Contrary']"));
        action.moveToElement(link).perform();

        List<WebElement> linkToolTip = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("contraryTexToolTip")));
        assertFalse(linkToolTip.isEmpty());
    }

    @Test
    public void hoverTheSecondLinkTest() {
        Actions action = new Actions(driver);
        WebElement link = driver.findElement(By.xpath("//*[@id='texToolTopContainer']/a[2]"));
        action.moveToElement(link).perform();

        List<WebElement> linkToolTip = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("sectionToolTip")));
        assertFalse(linkToolTip.isEmpty());
    }

    @AfterAll
    public static void browserTearDown() {
        driver.quit();
    }
}