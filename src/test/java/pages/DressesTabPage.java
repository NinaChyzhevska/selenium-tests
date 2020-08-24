package pages;

import org.openqa.selenium.WebDriver;

public class DressesTabPage extends ShopPage {

    public DressesTabPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected String getPageUrl() {
        return "http://automationpractice.com/index.php?id_category=8&controller=category";
    }
}
