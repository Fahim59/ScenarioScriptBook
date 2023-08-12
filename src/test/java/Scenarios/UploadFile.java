package Scenarios;

import BasePage.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;

public class UploadFile extends BaseClass {
    public static void main(String[] args) throws InterruptedException {
        UploadPicture("","");
    }
    public static void UploadPicture(String fileName, String xpath) throws InterruptedException {
        Actions builder = new Actions(driver);
        builder.moveToElement(driver.findElement(By.xpath(xpath))).click().build().perform();
        SmallWait(1000);

        boolean isFileUploaded = false;
        int retryCount = 0;
        while (!isFileUploaded && retryCount < 2) {
            try {
                StringSelection filePath = new StringSelection("E:\\Intellij Files\\RDCD_Automation\\Picture\\" + fileName);
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePath, null);

                Robot robot = new Robot();
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_CONTROL);

                SmallWait(1000);

                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class,'file-upload-dialog')]")));

                isFileUploaded = driver.findElement(By.xpath("//img[contains(@src, 'data:image/jpg;base64,')] | //img[contains(@src, 'data:image/jpeg;base64,')] | //img[contains(@src, 'data:image/png;base64,')] | //img[contains(@src, '/pdf')]")).isDisplayed();

                if (isFileUploaded) {
                    System.out.println("File uploaded successfully");
                }
                else {
                    System.out.println("File upload failed, retrying...");
                    retryCount++;
                }
            }
            catch (Exception exp) {
                exp.printStackTrace();
                retryCount++;
            }
        }

        if (!isFileUploaded) {
            System.out.println("File upload failed after " + retryCount + " attempts");
        }
    }
}
