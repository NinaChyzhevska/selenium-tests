import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.StringJoiner;

import static org.junit.jupiter.api.Assertions.*;

public class HomeTask4Test {

    private WebDriver driver;

    @BeforeEach
    public void browserSetUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void initialOrderTest() {
        String URL = "https://demoqa.com/sortable/";
        driver.get(URL);
        List<WebElement> items = driver.findElements(By.xpath("//*[@id='demo-tabpane-list']//*[@class='list-group-item list-group-item-action']"));
        List<String> itemNames = new LinkedList<>();
        for (WebElement item : items) {
             itemNames.add(item.getText());
        }

        List<String> correctOrder = new LinkedList<>();
        correctOrder.add("One");
        correctOrder.add("Two");
        correctOrder.add("Three");
        correctOrder.add("Four");
        correctOrder.add("Five");
        correctOrder.add("Six");

        assertEquals(correctOrder, itemNames);
    }

    @Test
    public void modifiedOrderTest() {
        String URL = "https://demoqa.com/sortable/";
        driver.get(URL);
        WebElement draggable = driver.findElement(By.xpath("//*[@id='demo-tabpane-list']//*[text()='Two']"));
        WebElement target = driver.findElement(By.xpath("//*[@id='demo-tabpane-list']//*[text()='Five']"));
        new Actions(driver).dragAndDrop(draggable, target).perform();

        List<WebElement> items = driver.findElements(By.xpath("//*[@id='demo-tabpane-list']//*[@class='list-group-item list-group-item-action']"));
        List<String> itemNames = new LinkedList<>();
        for (WebElement item : items) {
            itemNames.add(item.getText());
        }

        List<String> correctOrder = new LinkedList<>();
        correctOrder.add("One");
        correctOrder.add("Three");
        correctOrder.add("Four");
        correctOrder.add("Five");
        correctOrder.add("Two");
        correctOrder.add("Six");

        assertEquals(correctOrder, itemNames);
    }

    @Test
    public void selectableTest() {
        String URL = "https://demoqa.com/selectable/";
        driver.get(URL);

        List<WebElement> items = driver.findElements(By.xpath("//*[@class='mt-2 list-group-item list-group-item-action']"));
        Random rand = new Random();
        StringJoiner joiner = new StringJoiner("_", "", "_" + System.currentTimeMillis());
        int exclude = rand.nextInt(4);
        for (int i = 0; i < items.size(); i++) {
            if (i != exclude) {
                items.get(i).click();
                joiner.add(String.valueOf(i));
            }
        }

        takeScreenshot(items.get(0), joiner.toString());
    }

    @Test
    public void resizableTest() {
        String URL = "https://demoqa.com/resizable/";
        driver.get(URL);

        printSizeOfTheRectangle();
        WebElement resizableCorner = driver.findElement(By.xpath("//*[@id='resizableBoxWithRestriction']/span"));

        Actions actionsResize = new Actions(driver);
        actionsResize.dragAndDropBy(resizableCorner, 130, 60).perform();
        printSizeOfTheRectangle();

        actionsResize.dragAndDropBy(resizableCorner, -30, -40).perform();
        printSizeOfTheRectangle();
    }

    private void takeScreenshot(WebElement focusElement, String fileName) {
        new Actions(driver).moveToElement(focusElement).perform();

        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File("D://QAscreenshots/" + fileName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printSizeOfTheRectangle() {
        WebElement currentSizeOfTheRectangle = driver.findElement(By.id("resizableBoxWithRestriction"));
        Dimension dimensions = currentSizeOfTheRectangle.getSize();
        System.out.println("Height: " + dimensions.height + ". " + "Width: " + dimensions.width);
    }

    @AfterEach
    public void browserTearDown() {
        driver.quit();
    }
}