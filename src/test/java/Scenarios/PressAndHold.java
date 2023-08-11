package Scenarios;

import BasePage.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PressAndHold extends BaseClass {

    private static final By elementBtn = By.cssSelector("#px-captcha");
    private static final By signInText = By.xpath("//h1[@class='mt3 f3 mb1']");

    public static void main(String[] args) throws InterruptedException {
        ChromeLaunch();
        OpenWebsite("https://www.walmart.com/blocked?url=L2lwL3Nlb3J0LzEwMDA3MjY3OQ==&uuid=a54956f0-dd07-11ed-82ea-1d892cfec3d0&vid=c17a2c6b-5ab8-11ed-bf79-74676779686d&g=b");

        PressAndHold();
        //BrowserQuit();
    }

    public static void PressAndHold() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions action = new Actions(driver);
        SmallWait(5000);

        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(elementBtn));
        System.out.println(element.isDisplayed());

        action.clickAndHold(element).perform();
        action.pause(8000).release(element).perform();
        action.release(element).perform();

//        WebElement text = wait.until(ExpectedConditions.presenceOfElementLocated(signInText));
//        System.out.println(text.isDisplayed());
//        System.out.println(text.getText());
    }
}
