package common;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BaseSetUp {
    public static WebDriver driver;



    public WebDriver setupDriver(String browserType) {
        switch (browserType.trim().toLowerCase()) {
            case "chrome":
                driver = initChromeDriver();
                break;
            case "firefox":
                driver = initFirefoxDriver();
                break;
            case "edge":
                driver = initEdgeDriver();
                break;
            default:
                System.out.println("Browser: " + browserType + " is invalid, Launching Chrome as browser of choice...");
                driver = initChromeDriver();
        }
        return driver;
    }

    // Hàm này để tùy chọn Browser. Cho chạy trước khi gọi class này (BeforeClass)
    public void setDriver(String browserType, String appURL) {
        switch (browserType) {
            case "chrome":
                driver = initChromeDriver();
                driver.navigate().to(appURL);
                break;
            case "firefox":
                driver = initFirefoxDriver();
                driver.navigate().to(appURL);
                break;
            default:
                System.out.println("Browser: " + browserType + " is invalid, Launching Chrome as browser of choice...");
                driver = initChromeDriver();
        }
    }

    // Khởi tạo cấu hình của các Browser để đưa vào Switch Case

    private WebDriver initChromeDriver() {
//        System.out.println("Launching Chrome browser...");
//        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        return driver;
    }

    private WebDriver initEdgeDriver() {
        System.out.println("Launching Edge browser...");
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        return driver;
    }

    private WebDriver initFirefoxDriver() {
        System.out.println("Launching Firefox browser...");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        return driver;
    }


    // Chạy hàm initializeTestBaseSetup trước hết khi class này được gọi
//    @Parameters({"browserType", "webURL"})
//    @BeforeClass
    public void initializeTestBaseSetup(String browserType, String webURL) {
        try {
            // Khởi tạo driver và tùy chọn browser và web url
            setDriver(browserType, webURL);
        } catch (Exception e) {
            System.out.println("Error..." + e.getStackTrace());
        }
    }

//    @AfterClass
    public void tearDown() throws Exception {
        if(driver != null){
            driver.quit();
        }
    }

}
