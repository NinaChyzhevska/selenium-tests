import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class LinkTest {

    private WebDriver driver;

    @BeforeEach
    public void browserSetUp() {
        String exePath = "D:\\webdriver\\msedgedriver.exe";
        System.setProperty("webdriver.edge.driver", exePath);
        driver = new EdgeDriver();
    }

    @Test
    public void testPrintAllLinks() {
        String webPageUrl = "https://en.wikipedia.org/wiki/Main_Page";
        driver.navigate().to(webPageUrl);

        List<WebElement> allLinkElements = driver.findElements(By.tagName("a"));
        assertFalse(allLinkElements.isEmpty());

        for (WebElement link : allLinkElements) {
            System.out.println(link.getText() + " (" + link.getAttribute("href") + ")");
        }
    }

    @AfterEach
    public void browserTearDown() {
        driver.quit();
    }
}