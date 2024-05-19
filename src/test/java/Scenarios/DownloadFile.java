package Scenarios;

import BasePage.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.Collections;

public class DownloadFile extends BaseClass {

    public static void main(String[] args) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--blink-settings=imagesEnabled=false");  //block image

        //remove "𝐂𝐡𝐫𝐨𝐦𝐞 𝐢𝐬 𝐛𝐞𝐢𝐧𝐠 𝐜𝐨𝐧𝐭𝐫𝐨𝐥𝐥𝐞𝐝 𝐛𝐲 𝐚𝐮𝐭𝐨𝐦𝐚𝐭𝐞𝐝 𝐭𝐞𝐬𝐭 𝐬𝐨𝐟𝐭𝐰𝐚𝐫𝐞"..

        options.setExperimentalOption("useAutomationExtension", false);
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

        ChromeLaunch(options);

        driver.get("https://letcode.in/file");

        WebElement downloadBtn = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.
                elementToBeClickable(By.xpath("//a[@id='xls']")));
        downloadBtn.click();

        File file = new File("C:\\Users\\hp\\Downloads","sample.xlsx");
        FluentWait<File> wait = new FluentWait<>(file)
                .withTimeout(Duration.ofMinutes(5)).pollingEvery(Duration.ofSeconds(5))
                .ignoring(Exception.class).withMessage("File is not downloaded yet");

        boolean fileDownload = false;
        try{
            fileDownload = wait.until(f->f.exists() && f.canRead());
        }
        catch (Exception e){
            System.out.println("File is not downloaded successfully");
        }

        if(fileDownload){
            System.out.println("File downloaded successfully");
        }

        driver.quit();
    }
}
