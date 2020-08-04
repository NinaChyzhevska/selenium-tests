import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PersonManagingTest {
    private static final String MODIFIED_AGE = "33";
    private static final String EMAIL = "test@example.com";
    private static WebDriver driver;

    @BeforeAll
    public static void browserSetUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @BeforeEach
    public void navigateTab() {
        String URL = "http://demoqa.com/webtables";
        driver.navigate().to(URL);
    }

    @Test
    public void addPersonTest() {
        addPerson();
        verifyThatPersonAdded();
    }

    @Test
    public void modifyPersonAgeTest() {
        addPerson();
        modifyPersonAge();
        verifyThatPersonAgeModified();
    }

    @Test
    public void deletePersonTest() {
        addPerson();
        deletePerson();
        verifyThatPersonDeleted();
    }

    @AfterAll
    public static void browserTearDown() {
        driver.quit();
    }

    private void addPerson() {
        WebElement addButton = driver.findElement(By.id("addNewRecordButton"));
        addButton.click();

        WebElement firstName = driver.findElement(By.id("firstName"));
        firstName.sendKeys("Name");
        WebElement lastName = driver.findElement(By.id("lastName"));
        lastName.sendKeys("Surname");
        WebElement email = driver.findElement(By.id("userEmail"));
        email.sendKeys(EMAIL);
        WebElement age = driver.findElement(By.id("age"));
        age.sendKeys("22");
        WebElement salary = driver.findElement(By.id("salary"));
        salary.sendKeys("12345");
        WebElement department = driver.findElement(By.id("department"));
        department.sendKeys("Insurance");

        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();
    }

    private void verifyThatPersonAdded() {
        searchPerson();

        List<WebElement> editButton = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.numberOfElementsToBe(By.xpath("//*[@title = 'Edit']"), 1));
        assertFalse(editButton.isEmpty());
    }

    private void modifyPersonAge() {
        searchPerson();
        List<WebElement> editButton = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.numberOfElementsToBe(By.xpath("//*[@title = 'Edit']"), 1));
        editButton.get(0).click();
        WebElement age = driver.findElement(By.id("age"));
        age.clear();
        age.sendKeys(MODIFIED_AGE);
        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();
    }

    private void verifyThatPersonAgeModified() {
        searchPerson();
        WebElement ageChanged = driver.findElement(By.xpath("//*[@class='rt-tr -odd']/div[3]"));
        assertEquals(ageChanged.getText(), MODIFIED_AGE);
    }

    private void deletePerson() {
        searchPerson();
        WebElement deleteButton = driver.findElement(By.xpath("//*[@title = 'Delete']"));
        deleteButton.click();
    }

    private void verifyThatPersonDeleted() {
        searchPerson();
        List<WebElement> editButton = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.numberOfElementsToBe(By.xpath("//*[@title = 'Edit']"), 0));

        assertTrue(editButton.isEmpty());
    }

    private void searchPerson() {
        new WebDriverWait(driver, 30)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.className("modal-content")));

        WebElement searchField = driver.findElement(By.id("searchBox"));
        searchField.clear();
        searchField.sendKeys(EMAIL);
    }
}