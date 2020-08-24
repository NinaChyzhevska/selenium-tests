package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.Waiters;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ShopPage extends Page {
    private static final String BACKGROUND_COLOR = "background-color";

    @FindBy(xpath = "//*[contains(@class,'color_to_pick_list')]/li/a")
    private List<WebElement> colorFromCatalog;

    @FindBy(xpath = "//*[@id='ul_layered_id_attribute_group_3']/li/input")
    private List<WebElement> colorsFromFilters;

    @FindBy(xpath = "//*[@id='ul_layered_id_attribute_group_3']/li//span")
    private List<WebElement> colorCount;

    @FindBy(xpath = "//*[contains(@class, 'bt_compare_bottom')]")
    private WebElement compareButton;

    public ShopPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        openPage();
    }

    public Map<String, Integer> countColorsFromCatalog() {
        Map<String, Integer> occurrences = new HashMap<>();
        for (WebElement color : colorFromCatalog) {
            String backgroundColor = color.getCssValue(BACKGROUND_COLOR);
            if (!occurrences.containsKey(backgroundColor)) {
                occurrences.put(backgroundColor, 1);
            } else {
                int occ = occurrences.get(backgroundColor);
                occurrences.put(backgroundColor, occ + 1);
            }
        }
        return occurrences;
    }

    public Map<String, Integer> countColorsFromFilters() {
        Map<String, Integer> occurrences = new HashMap<>();
        for (int i = 0; i < colorCount.size(); i++) {
            String colorName = colorsFromFilters.get(i).getCssValue(BACKGROUND_COLOR);
            int expectedOcc = getColorCount(colorCount.get(i));
            occurrences.put(colorName, expectedOcc);
        }
        return occurrences;
    }

    private void openPage() {
        driver.get(getPageUrl());
        Waiters.waitUntilElementIsVisible(driver, compareButton);
    }

    private int getColorCount(WebElement colorCount) {
        return Integer.parseInt(colorCount.getText().trim().replace("(","").replace(")", ""));
    }

    protected abstract String getPageUrl();
}
