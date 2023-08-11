package BasePage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseClass {
    public static WebDriver driver;

    public static void FirefoxLaunch() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
    }

    public static void OpenWebsite(String Url) {
        driver.get(Url);
    }

    public static void BrowserQuit() {
        driver.quit();
    }

    public static void SmallWait(int second) throws InterruptedException {Thread.sleep(second);}
}
