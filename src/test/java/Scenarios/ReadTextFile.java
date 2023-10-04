package Scenarios;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class ReadTextFile {
    public static void main(String[] args) {
        ReadFile();
    }

    public static void ReadFile(){
        try{
            //String TestFile = "E:\\My Laptop\\Extra.txt";
            String TestFile = "E:\\Intellij Files\\Appium_TestNG\\Extra\\Email.txt";
            FileReader FR = new FileReader(TestFile);
            BufferedReader BR = new BufferedReader(FR);
            String content = "";

            for (int i = 0; (content = BR.readLine()) != null; i++) {
                String[] arrOfStr = content.split("\\| ", 4);

                String db = arrOfStr[0];
                String user = arrOfStr[1];
                String password = arrOfStr[2];
                String email = arrOfStr[3];

                System.out.println(db + ", " + user + ", " + password + ", " + email);
            }
        }
        catch (Exception e){
            System.err.println("Error: " + Arrays.toString(e.getStackTrace()));
        }
    }
}
