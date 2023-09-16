package Locator_Selenium;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_06_Register_Guru {
    WebDriver driver;
    String userName="";
    String pass="";
    public static final String URL="https://demo.guru99.com/";
    @BeforeMethod
    public void createDriver() {;
        System.setProperty("webdriver.chrome.driver", "browserDrivers\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().window().maximize();

    }
    @Test
    public void TC_01_Register(){
        // Truy cập trang demo guru
        String txtEmail="//input[@name='emailid']";
        String btnSubmit="//input[@name='btnLogin']";
        String txtUserName="//td[contains(text(),'User ID :')]//following-sibling::td";
        String txtPass="//td[contains(text(),'Password :')]//following-sibling::td";
        // Nhập vào 1 email button
        driver.findElement(By.xpath(txtEmail)).sendKeys("test01@gmail.com");
        // Click Submit button
        driver.findElement(By.xpath(btnSubmit)).click();
        // Get user/pass lưu vào 1 biến
        userName=driver.findElement(By.xpath(txtUserName)).getText();
        pass=driver.findElement(By.xpath(txtPass)).getText();
        System.out.println(userName + "+" + pass);


    }
    @Test
    public void TC_02_Login(){
        String txtUserID="//td[contains(text(),'UserID')]//following-sibling::td//input";
        String txtPass="//td[contains(text(),'Password')]//following-sibling::td//input";
        String btnLogin="//input[@name='btnLogin']";
        String textLoginSuccess="//td[contains(text(),'Manger Id : ')]";
        // Truy cập trang login https://demo.guru99.com/v4
        driver.get("https://demo.guru99.com/v4/");
        // Nhập user /pass  ở màn hình đăng ký
        driver.findElement(By.xpath(txtUserID)).sendKeys(userName);
        driver.findElement(By.xpath(txtPass)).sendKeys(pass);
        driver.findElement(By.xpath(btnLogin)).click();
        String textLogin= driver.findElement(By.xpath(textLoginSuccess)).getText();
        String txtLoginSuccessByUser= "Manger Id : " +userName;
        Assert.assertTrue(textLogin.equals(txtLoginSuccessByUser));


    }

    @AfterMethod
    public void Closed() {
        driver.quit();
    }
}
