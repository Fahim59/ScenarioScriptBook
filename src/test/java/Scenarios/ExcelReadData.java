package Scenarios;

import Scenarios.utils.ExcelReader;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ExcelReadData {
    static int row = 5;

    public static void main(String[] args) throws IOException, InvalidFormatException {
        ReadFile();
    }

    public static void ReadFile() throws IOException, InvalidFormatException {
        ExcelReader reader = new ExcelReader();
        List<Map<String,String>> testData =
                reader.getData("E:\\ERA ALL FILES_Mustafizur Rahman\\RDCD\\Migration Data\\Coop\\Kushtia\\কুষ্টিয়া সদর\\Test.xlsx", "SamityInfo");

        System.out.println(testData.get(row).get("Test (নির্বাচনী)"));

        String[] unions = testData.get(row).get("Test (নির্বাচনী)").split(", ");
        System.out.println("Number of Data Items: " + unions.length);
    }
}
