package SearchEngineTesting;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class SearchTelerikTest {

    public static final String EXPECTED_URL = "https://www.telerikacademy.com";
    public static final int WAIT_AMOUNT = 5;

    WebDriver driver;

    @Test
    public void googleSearch(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();

        driver.get("https://google.com");
        WebElement cookieButton = driver.findElement(By.id("L2AGLb"));
        cookieButton.click();

        WebElement searchField = driver.findElement(By.name("q"));
        searchField.sendKeys("Telerik academy" + Keys.ENTER);

        WebDriverWait wait = new WebDriverWait(driver, Duration.of(WAIT_AMOUNT, ChronoUnit.SECONDS));
        // Wait for the first result <cite> element
        WebElement firstResult = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("cite.tjvcx.GvPZzd.dTxz9.cHaqb")));

        // Assert that the <cite> element contains the expected URL
        Assertions.assertTrue(firstResult.getText().contains(EXPECTED_URL),
                "The first result does not contain the expected URL.");

        System.out.println("First result is: " + firstResult.getText());
    }

    @Test
    public void bingSearch(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();

        //Will use the wait a couple of times
        WebDriverWait wait = new WebDriverWait(driver, Duration.of(WAIT_AMOUNT, ChronoUnit.SECONDS));

        driver.get("https://bing.com");

        //We can ignore cookie prompt but let's tackle it
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("bnp_btn_reject")));
        WebElement cookieButton = driver.findElement(By.id("bnp_btn_reject"));
        cookieButton.click();

        //Search
        WebElement searchField = driver.findElement(By.id("sb_form_q"));
        searchField.sendKeys("Telerik academy" + Keys.ENTER);

        // Wait for the first result <cite> element
        WebElement firstResult = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.b_attribution cite")));


        // Assert that the <cite> element contains the expected URL
        Assertions.assertTrue(firstResult.getText().contains(EXPECTED_URL),
                "The first result does not contain the expected title.");


        System.out.println("First result is: " + firstResult.getText());
    }

    // Close the browser after each test
    @AfterEach
    public void closeDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
