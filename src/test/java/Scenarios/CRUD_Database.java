package Scenarios;

import BasePage.BaseClass;
import Scenarios.utils.DatabaseConnection;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.sql.Connection;
import java.util.Map;

public class CRUD_Database extends BaseClass {
    static Faker faker = new Faker();

    static String name = faker.name().firstName();
    static String email = name.toLowerCase() + "@gmail.com";
    static String password = faker.internet().password();

    static String getUsername,getEmail,getPassword;

    static DatabaseConnection db = new DatabaseConnection();
    static Connection conn = db.connect_to_db();

    public static void main(String[] args) throws InterruptedException {
//        insertData();
//        getInsertedData();

        deletData();

        //BrowserQuit();
    }

    public static void insertData() throws InterruptedException {
        FirefoxLaunch();

        OpenWebsite("file:///C:/Users/Mustafizur%20Rahman/Desktop/Registration.html");

        FindElementByName_Details("username", name);
        FindElementByName_Details("email", email);
        FindElementByName_Details("password", password);

        SmallWait(2000);
        FindElementByXpath_Click("//button[@type='submit']");

        getUsername = driver.findElement(By.name("username")).getAttribute("value");
        getEmail = driver.findElement(By.name("email")).getAttribute("value");
        getPassword = driver.findElement(By.name("password")).getAttribute("value");

        db.insert_data(conn,getUsername,getEmail,getPassword);

        SmallWait(3000);
    }

    public static void getInsertedData() {

        Map<String, String> fetchedData = db.read_last_inserted_data(conn);

        Assert.assertEquals(getUsername,fetchedData.get("username"));
        Assert.assertEquals(getEmail,fetchedData.get("email"));
        Assert.assertEquals(getPassword,fetchedData.get("password"));

        System.out.println("Data fetched successfully");
    }

    public static void deletData() {
        db.delete_data(conn);
    }
}
