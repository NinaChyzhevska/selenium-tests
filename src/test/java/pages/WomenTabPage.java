package pages;

import org.openqa.selenium.WebDriver;

public class WomenTabPage extends ShopPage {

    public WomenTabPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected String getPageUrl() {
        return "http://automationpractice.com/index.php?id_category=3&controller=category";
    }
}
