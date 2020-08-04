import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class AbstractSearchTest {
    private WebDriver driver;

    @BeforeEach
    public void openWebPage() {
        driver = getDriver();
        driver.manage().window().maximize();
        String URL = "https://www.google.com/";
        driver.get(URL);
    }

    protected abstract WebDriver getDriver();

    @Test
    public void resultOnTheFirstPageTest() {

        WebElement searchInformation = driver.findElement(By.xpath("//input[@type='text']"));
        searchInformation.sendKeys("selenium automation testing");
        searchInformation.sendKeys(Keys.ENTER);

        List<WebElement> elements = driver.findElements(By.xpath("//*[text() = 'www.selenium.dev']"));
        assertFalse(elements.isEmpty());
        takeScreenshot(elements.get(0));
        printPageToConsole(1);
    }

    @Test
    public void resultLocatedFurtherThanTenPageTest() {

        WebElement searchInformation = driver.findElement(By.xpath("//input[@type='text']"));
        searchInformation.sendKeys("осциллограф");
        searchInformation.sendKeys(Keys.ENTER);

        WebElement searchButton = driver.findElement(By.xpath("//a[@aria-label='Page 10']"));
        searchButton.click();
        WebElement findResult = driver.findElement(By.id("pnnext"));
        findResult.click();
        List<WebElement> elements = driver.findElements(By.xpath("//*[text()='books.google.com.ua']"));
        assertFalse(elements.isEmpty());
        takeScreenshot(elements.get(0));
        printPageToConsole(11);
    }

    @Test
    public void resultIsNotFoundTest() {

        WebElement searchInformation = driver.findElement(By.xpath("//input[@type='text']"));
        searchInformation.sendKeys("осциллограф");
        searchInformation.sendKeys(Keys.ENTER);

        int page = 1;
        while (true) {
            List<WebElement> nextButton = driver.findElements(By.id("pnnext"));
            if (nextButton.isEmpty()) {
                break;
            }
            nextButton.get(0).click();
            page++;
            List<WebElement> elements = driver.findElements(By.id("Nina"));
            if (!elements.isEmpty()) {
                takeScreenshot(elements.get(0));
                printPageToConsole(page);
            }
            assertTrue(elements.isEmpty());
        }
    }

    @AfterEach
    public void browserTearDown() {
        driver.quit();
    }

    private void takeScreenshot(WebElement focusElement) {
        new Actions(driver).moveToElement(focusElement).perform();

        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File("D://QAscreenshots/" + System.currentTimeMillis() + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printPageToConsole(int page) {
        System.out.println("Element page number is " + page);
    }
}
