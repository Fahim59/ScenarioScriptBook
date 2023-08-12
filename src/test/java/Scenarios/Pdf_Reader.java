package Scenarios;

import BasePage.BaseClass;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

public class Pdf_Reader extends BaseClass {
    public static void main(String[] args) throws IOException, InterruptedException {
        readPdfInSameBrowserTest();
    }

    public static void readPdfInSameBrowserTest() throws IOException, InterruptedException {
        ChromeLaunch();
        OpenWebsite("file:///C:/Users/Mustafizur%20Rahman/Desktop/Pdf.html");

        SmallWait(2000);
        FindElementByXpath_Click("//*[@id='pdfButton']");

        SmallWait(2000);

        Set<String> s1 = driver.getWindowHandles();
        Iterator<String> i1 = s1.iterator();

        String dashboard = i1.next();
        String report = i1.next();

        driver.switchTo().window(report);

        URL url = new URL(driver.getCurrentUrl());

        InputStream is = url.openStream();
        BufferedInputStream fileParse = new BufferedInputStream(is);

        PDDocument document;

        document = PDDocument.load(fileParse);
        String pdfContent = new PDFTextStripper().getText(document);

        List<String> extractedCodesList = new ArrayList<>();

        String[] lines = pdfContent.split("\n");

        StringBuilder extractedCodes = new StringBuilder();

        for (String line : lines) {
            if (line.contains("TR00")) {
                String[] words = line.split(" ");

                for (String word : words) {
                    if (word.contains("TR00")) {
                        extractedCodes.append(word).append("\n");
                        extractedCodesList.add(word);
                    }
                }
            }
        }

        System.out.println(extractedCodesList);

        extractedCodesList.sort(Collections.reverseOrder());

        System.out.println("Last index value: " + extractedCodesList.get(0));

        SmallWait(2000);
        driver.switchTo().window(dashboard);

        SmallWait(2000);
        BrowserQuit();
    }
}
