import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.DressesTabPage;
import pages.WomenTabPage;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ColorsTest {

    private WebDriver driver;

    @BeforeEach
    public void browserSetUp() {
        driver = new ChromeDriver();
    }

    @Test
    public void womenTabColorTest() {
        WomenTabPage womenTabPage = new WomenTabPage(driver);
        Map<String, Integer> occurrencesFromCatalog = womenTabPage.countColorsFromCatalog();
        Map<String, Integer> occurrencesFromColorFilter = womenTabPage.countColorsFromFilters();
        assertEquals(occurrencesFromColorFilter, occurrencesFromCatalog);
    }

    @Test
    public void dressesTabColorTest() {
        DressesTabPage dressesTabPage = new DressesTabPage(driver);
        Map<String, Integer> occurrencesFromCatalog = dressesTabPage.countColorsFromCatalog();
        Map<String, Integer> occurrencesFromColorFilter = dressesTabPage.countColorsFromFilters();
        assertEquals(occurrencesFromColorFilter, occurrencesFromCatalog);
    }

    @AfterEach
    public void browserTearDown() {
        driver.quit();
    }
}
