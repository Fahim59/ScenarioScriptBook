package Scenarios;

import java.io.BufferedReader;
import java.io.FileReader;

public class ReadTextFile {
    public static void main(String[] args) {
        ReadFile();
    }

    public static void ReadFile(){
        try{
            String TestFile = "E:\\Intellij Files\\Member_Info.txt";
            FileReader FR = new FileReader(TestFile);
            BufferedReader BR = new BufferedReader(FR);
            String content = "";

            for (int i = 0; (content = BR.readLine()) != null; i++) {
                String[] arrOfStr = content.split("\\| ", 3);

                String id = arrOfStr[0];
                String image = arrOfStr[1];
                String sign = arrOfStr[2];

                System.out.println(id + ", " + image + ", " + sign);
            }
        }
        catch (Exception e){
            System.err.println("Error: " + e.getMessage());
        }
    }
}
