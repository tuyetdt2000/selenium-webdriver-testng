package common;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;
import java.util.Set;

public class ValidateUIHelpers {
    private WebDriverWait explicitWait;
    private Alert alert;
    private Select select;
    private JavascriptExecutor jsExecutor;
    private Actions action;
    private WebElement element;
    private final long longTimeOut = 30;

    /*open url*/
    public void openUrl(WebDriver driver, String url) {
        driver.get(url);
    }

    public String getPageTittle(WebDriver driver) {
        return driver.getTitle();
    }

    /**/
    public String getCurrentPageUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public String getPageSource(WebDriver driver) {
        return driver.getPageSource();
    }

    public void backToPage(WebDriver driver) {
        driver.navigate().back();
    }

    public void refreshCurrentPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    public void forwardToPage(WebDriver driver) {
        driver.navigate().forward();
    }

    public void waitToAlertPresence(WebDriver driver) {
        explicitWait = new WebDriverWait(driver,longTimeOut);
        explicitWait.withTimeout(Duration.ofSeconds(longTimeOut));
        explicitWait.until(ExpectedConditions.alertIsPresent());
    }

    public void acceptAlert(WebDriver driver) {
        alert = driver.switchTo().alert();
        alert.accept();

    }

    public void cancelAlert(WebDriver driver) {
        alert = driver.switchTo().alert();
        alert.dismiss();
    }

    public void sendKeyToAlert(WebDriver driver, String value) {
        alert = driver.switchTo().alert();
        alert.sendKeys(value);
    }

    public String getTextAlert(WebDriver driver) {
        alert = driver.switchTo().alert();
        return alert.getText();
    }

    /*   window*/
    public void switchToWindowByID(WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            if (!runWindow.equals(parentID)) {
                driver.switchTo().window(runWindow);
                break;
            }
        }
    }

    public void switchToWindowByTittle(WebDriver driver, String tittle) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            driver.switchTo().window(runWindow);
            String currentWin = driver.getTitle();
            if (currentWin.equals(tittle)) {
                break;
            }
        }
    }


    /*Element*/

    public By byXpath(String locator) {
        return By.xpath(locator);
    }

    public WebElement find(WebDriver driver, String locator) {
        return driver.findElement(byXpath(locator));

    }

    public List<WebElement> finds(WebDriver driver, String locator) {
        return driver.findElements(byXpath(locator));
    }

    public void clickToElement(WebDriver driver, String locator) {
        find(driver, locator).click();
    }

    public void senKeyToElement(WebDriver driver, String locator, String value) {
        find(driver, locator).clear();
        find(driver, locator).sendKeys(value);
    }

    public void senKeyToElementDate(WebDriver driver, String locator, String value) throws AWTException {
        //${arguments[1]} đại diện cho tham số thứ hai trong phương thức executeScript(),
        String script = "arguments[0].value = `${arguments[1]}`;";
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(script, find(driver, locator),value);
        find(driver, locator).click();
    }

    public void selectItemInDropdown(WebDriver driver, String locator, String itemValue) {
        select = new Select(find(driver, locator));
        select.deselectByVisibleText(itemValue);
    }

    public String getFirstSelectedItemInDropDown(WebDriver driver, String locator) {
        select = new Select(find(driver, locator));
        return select.getFirstSelectedOption().getText();
    }

    public boolean isDropdownMultiple(WebDriver driver, String locator) {
        select = new Select(find(driver, locator));
        return select.isMultiple();
    }

    public void uploadDocument(WebDriver driver, String locator, String pathDocument){
        WebElement upload = find(driver,locator);
        upload.click();
        sleepSecond(1);
        try {
            Robot robot = new Robot();
            StringSelection stringSelection = new StringSelection(pathDocument);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection,null);
            // press Contol+V for pasting
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            // release Contol+V for pasting
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_V);
            // for pressing and releasing Enter
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }

    }
    public void closedDropdownESC()  {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ESCAPE);
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }

    }






    public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childLocator, String expectedItem) {
        /*Click vào thẻ cha để xổ ra tất cả các item con*/
        find(driver, parentLocator).click();
        sleepSecond(1);
        explicitWait = new WebDriverWait(driver, longTimeOut);
        explicitWait.withTimeout(Duration.ofSeconds(30));
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(byXpath(childLocator)));

        List<WebElement> allItems = finds(driver, childLocator);
        for (WebElement item : allItems) {
            if (item.getText().equals(expectedItem)) {
                jsExecutor = (JavascriptExecutor) driver;
                jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
                sleepSecond(1);

                item.click();
                sleepSecond(1);
                break;
            }
        }
    }

    public void sleepSecond(long timeout) {
        try {
            Thread.sleep(1000*timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getElementAttribute(WebDriver driver, String locator, String attributeName) {
        return find(driver, locator).getAttribute(attributeName);
    }

    public String getElementText(WebDriver driver, String locator) {
        return find(driver, locator).getText();
    }

    public int countElementSize(WebDriver driver, String locator) {
        return finds(driver, locator).size();
    }

    public void checkToCheckbox(WebDriver driver, String locator) {
        if (!find(driver, locator).isSelected()) {
            find(driver, locator).click();
        }
    }

    public void uncheckToCheckbox(WebDriver driver, String locator) {
        if (find(driver, locator).isSelected()) {
            find(driver, locator).click();
        }
    }

    /*Có thể kiểm tra cho tất cả element*/
    public boolean isControlDisplay(WebDriver driver, String locator) {
        return find(driver, locator).isDisplayed();
    }

    /*Kiểm tra element có thao tác được không*/
    public boolean isControlEnable(WebDriver driver, String locator) {
        return find(driver, locator).isEnabled();
    }

    /*Chỉ sử dụng dropdown/Checkbox/alert */
    public boolean isControlSelected(WebDriver driver, String locator) {
        return find(driver, locator).isSelected();
    }

    /*Chuyển trang đến 1 đường link được nhúng trong trang web (link youtube)*/
    public void switchToFrame(WebDriver driver, String locator) {
        driver.switchTo().frame(find(driver, locator));
    }

    /*trở về trang mặc định*/
    public void switchToDefaultPage(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    public void doubleClickToElement(WebDriver driver, String locator) {
        action = new Actions(driver);
        action.doubleClick(find(driver, locator)).perform();
    }

    public void rightClickToElement(WebDriver driver, String locator) {
        action = new Actions(driver);
        action.contextClick(find(driver, locator)).perform();
    }

    public void hoverToElement(WebDriver driver, String locator) {
        action = new Actions(driver);
        action.moveToElement(find(driver, locator)).perform();
    }

    public void dragAndDropClickToElement(WebDriver driver, String sourceLocator, String targetLocator) {
        action = new Actions(driver);
        action.dragAndDrop(find(driver, sourceLocator), find(driver, targetLocator)).perform();
    }

    public void senKeyBoardToElement(WebDriver driver, String locator, Keys key) {
        action = new Actions(driver);
        action.sendKeys(find(driver, locator), key).perform();
    }

    public void scrollToBottomPage(WebDriver driver) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }
    public void scrollToTopPage(WebDriver driver) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0,-document.body.scrollHeight)");
    }

    public void scrollToElement(WebDriver driver, String locator) {
        jsExecutor = (JavascriptExecutor) driver;
        element = find(driver, locator);
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
        jsExecutor = (JavascriptExecutor) driver;
        element = find(driver, locator);
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
    }

    public void waitToElementPresence(WebDriver driver, String locator) {
        explicitWait = new WebDriverWait(driver,longTimeOut);
        explicitWait.withTimeout(Duration.ofSeconds(30));
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    }

    public void waitToElementVisible(WebDriver driver, String locator) {
        explicitWait = new WebDriverWait(driver,longTimeOut);
        explicitWait.withTimeout(Duration.ofSeconds(60));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
    }

    public void waitToElementInVisible(WebDriver driver, String locator) {
        explicitWait = new WebDriverWait(driver, longTimeOut);
        explicitWait.withTimeout(Duration.ofSeconds(30));
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(locator)));
    }

    public void waitToElementClickable(WebDriver driver, String locator) {
        explicitWait = new WebDriverWait(driver, longTimeOut);
        explicitWait.withTimeout(Duration.ofSeconds(30));
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
    }

    /**
     * Date
     */
    public void selectDate(String month_year, String select_day){

    }



}
