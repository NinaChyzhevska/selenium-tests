import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FacebookAuthorizationTest {

    private WebDriver driver;

    @BeforeEach
    public void browserSetUp() {
        driver = new ChromeDriver();
    }

    @Test
    public void testFacebookLogin() {
        String URL = "http://facebook.com";
        driver.get(URL);
        WebElement searchEmail = driver.findElement(By.id("email"));
        searchEmail.sendKeys("userLogin");
        WebElement searchPassword = driver.findElement(By.id("pass"));
        searchPassword.sendKeys("userPassword");
        WebElement entrance = driver.findElement(By.xpath("//*[@id='loginbutton']/input"));
        entrance.click();

        List<WebElement> elements = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//a[@href='/friends/']")));
        assertTrue(elements.size() > 0);
    }

    @AfterEach
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }
}
