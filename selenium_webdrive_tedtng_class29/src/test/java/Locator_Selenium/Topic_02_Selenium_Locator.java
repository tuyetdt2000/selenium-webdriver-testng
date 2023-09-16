package Locator_Selenium;

import common.BaseSetUp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class Topic_02_Selenium_Locator extends BaseSetUp {
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
        driver.findElement(By.id("FirstName")).sendKeys("FirstName");
        driver.findElement(By.id("LastName")).sendKeys("LastName");
        driver.findElement(By.id("Email")).sendKeys("email@gmail.com");
        driver.findElement(By.id("Company")).sendKeys("Company");

    }
    @Test
    public void TC_02_CLASS(){
        System.out.println( driver.findElement(By.className("title")).getText());

    }
    @Test
    public void TC_03_NAME(){
        driver.findElement(By.name("FirstName")).sendKeys("FirstName");
        driver.findElement(By.name("LastName")).sendKeys("LastName");
        driver.findElement(By.name("Email")).sendKeys("email@gmail.com");
        driver.findElement(By.name("Company")).sendKeys("Company");


    }
    @Test
    public void TC_04_LINK_TEXT(){
        System.out.println( driver.findElement(By.linkText("Computers")).getText());
        System.out.println( driver.findElement(By.linkText("Electronics")).getText());
        System.out.println( driver.findElement(By.linkText("Apparel")).getText());
        System.out.println( driver.findElement(By.linkText("Digital downloads")).getText());


    }
    @Test
    public void TC_05_PARTIAL_LINK_TEXT(){
        System.out.println( driver.findElement(By.partialLinkText("Computers")).getText());
        System.out.println( driver.findElement(By.partialLinkText("Electronics")).getText());
        System.out.println( driver.findElement(By.partialLinkText("Apparel")).getText());
        System.out.println( driver.findElement(By.partialLinkText("Digital")).getText());

    }
    @Test
    public void TC_06_TAG_NAME(){
        List<WebElement> webElementList= driver.findElements(By.tagName("label"));
        for (WebElement e : webElementList
        ) {
            System.out.println(e.getText());

        }



    }
    @Test
    public void TC_07_CSS(){
        //ID
        driver.findElement(By.cssSelector("input[id='FirstName']")).sendKeys("FirstName");
        driver.findElement(By.cssSelector("input#FirstName")).sendKeys("FirstName3");
        driver.findElement(By.cssSelector("#FirstName")).sendKeys("FirstName4");
        //Class
        System.out.println(driver.findElement(By.cssSelector("div[class='page-title']")).getText());
        System.out.println(driver.findElement(By.cssSelector("div.page-title")).getText());
        System.out.println(driver.findElement(By.cssSelector(".page-title")).getText());
        //tagName
        List<WebElement> webElementList= driver.findElements(By.cssSelector("label"));
        for (WebElement e : webElementList
        ) {
            System.out.println(e.getText());

        }
        //Name
        driver.findElement(By.cssSelector("input[name='FirstName']")).sendKeys("FirstName1");


    }
    @Test
    public void TC_07_XPATH(){
        driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("FirstName");


    }
//    @AfterMethod
//    public void Closed(){
//        driver.quit();
//    }

}
