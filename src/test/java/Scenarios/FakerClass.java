package Scenarios;

import BasePage.BaseClass;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class FakerClass extends BaseClass {
    public static void main(String[] args) {
        FakerClass();
    }

    public static void FakerClass(){
        Faker faker = new Faker();
        Random rand = new Random();

        String fullName = faker.name().fullName();
        String name = faker.name().firstName();
        String email = name.toLowerCase() + "@gmail.com";
        String address = faker.address().fullAddress();
        String mobileNumber = "+8801" + String.format("%09d", rand.nextInt(1000000000));
        String phoneNumber = "+8802" + String.format("%08d", rand.nextInt(100000000));

        System.out.println(fullName);
        System.out.println(name);
        System.out.println(email);
        System.out.println(address);
        System.out.println(phoneNumber);
        System.out.println(mobileNumber);

        //LocalDate startDate = LocalDate.now().minus(365, ChronoUnit.DAYS);
        LocalDate startDate = LocalDate.now().withDayOfYear(1);
        LocalDate endDate = LocalDate.now();
        long randomEpochDay = ThreadLocalRandom.current().nextLong(startDate.toEpochDay(), endDate.toEpochDay());
        LocalDate randomDate = LocalDate.ofEpochDay(randomEpochDay);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = randomDate.format(formatter);

        System.out.println("Random date: " + formattedDate);
    }
}
