package Locator_Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_04_Relative_Locator {
    WebDriver driver;

    public static final String URL="https://demo.nopcommerce.com/register";
    @BeforeMethod
    public void createDriver() {;
        System.setProperty("webdriver.chrome.driver", "browserDrivers\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().window().maximize();


    }
    @Test
    public void TC_01_ID(){
        By pass = By.cssSelector("button#register-button");
        By repass = By.cssSelector("input#ConfirmPassword");
        WebElement element = driver
                .findElement(RelativeLocator.with(By.tagName("label"))
                        .above(pass)
                        .toLeftOf(repass));
    }
}
