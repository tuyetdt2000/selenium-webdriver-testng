package Locator_Selenium;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_05_Text_Locator {
    WebDriver driver;

    public static final String URL="https://automationfc.github.io/basic-form/";
    @BeforeMethod
    public void createDriver() {;
        System.setProperty("webdriver.chrome.driver", "browserDrivers\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().window().maximize();


    }
    @Test
    public void TC_01_(){
        //1 - Truyền cái text vào trong locator để check hiển  thị
        //    Nên sử dụng vì nó tuyệt đối
        driver.findElement(By.xpath("//h1[text()='Selenium WebDriver API']")).isDisplayed();
        //contains : Tìm kiếm text tương đối nên hạn chế dùng
        // Trong chuỗi chứa dấu nháy đơn ' thì phải dùng cú pháp contains(text(),"..."
        driver.findElement(By.xpath("//p[contains(text(),\"Mail Personal or Business Check, Cashier's Check or money order to:\")]")).isDisplayed();
//        2 -  Get text để verify sau
        String text  = driver.findElement(By.xpath("//h1[text()='Selenium WebDriver API']")).getText();
        Assert.assertTrue(text.contains("Selenium WebDriver API"));
        String nestText = driver.findElement(By.xpath("//h5[@id='nested']")).getText();
        Assert.assertTrue(nestText.contains("Hello World! (Ignore Me) @04:45 PM"));

    }

    @AfterMethod
    public void Closed() {
        driver.quit();
    }
}
