package BasePage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseClass {
    public static WebDriver driver;

    public static void FirefoxLaunch() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
    }

    public static void ChromeLaunch() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    public static void OpenWebsite(String Url) {
        driver.get(Url);
    }

    public static void BrowserQuit() {
        driver.quit();
    }

    public static void SmallWait(int second) throws InterruptedException {Thread.sleep(second);}

    public static void FindElementByXpath_Click(String xpath) { driver.findElement(By.xpath(xpath)).click(); }
}
