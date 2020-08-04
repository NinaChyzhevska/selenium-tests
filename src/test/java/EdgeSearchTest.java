import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class EdgeSearchTest extends AbstractSearchTest {
    @Override
    protected WebDriver getDriver() {
        String exePath = "D:\\webdriver\\msedgedriver.exe";
        System.setProperty("webdriver.edge.driver", exePath);
        return new EdgeDriver();
    }
}
