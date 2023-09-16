package Locator_Selenium;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_name_Template {
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



    }

    @AfterMethod
    public void Closed() {
        driver.quit();
    }
}
