import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeSearchTest extends AbstractSearchTest {
    @Override
    protected WebDriver getDriver() {
        return new ChromeDriver();
    }
}
