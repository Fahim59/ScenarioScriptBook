package Scenarios;

import BasePage.BaseClass;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Collections;

public class ImageBlocking extends BaseClass {

    public static void main(String[] args) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--blink-settings=imagesEnabled=false");  //block image

        //remove "𝐂𝐡𝐫𝐨𝐦𝐞 𝐢𝐬 𝐛𝐞𝐢𝐧𝐠 𝐜𝐨𝐧𝐭𝐫𝐨𝐥𝐥𝐞𝐝 𝐛𝐲 𝐚𝐮𝐭𝐨𝐦𝐚𝐭𝐞𝐝 𝐭𝐞𝐬𝐭 𝐬𝐨𝐟𝐭𝐰𝐚𝐫𝐞"

        options.setExperimentalOption("useAutomationExtension", false);
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

        ChromeLaunch(options);

        driver.get("https://www.freepik.com/");

        driver.quit();
    }
}
