package Scenarios.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class DatabaseConnection {
    static String db;
    static String user;
    static String pass;

    static int lastGeneratedId;

    public Connection connect_to_db(){
        Connection conn = null;
        try{
            BufferedReader BR = new BufferedReader(new FileReader("E:\\My Laptop\\Extra.txt"));
            String content = "";

            for (; (content = BR.readLine()) != null; ) {
                String[] arrOfStr = content.split("\\| ", 3);

                db = arrOfStr[0];
                user = arrOfStr[1];
                pass = arrOfStr[2];
            }

            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+db,user,pass);

            if(conn!=null){
                System.out.println("Connection Established");
            }
            else{
                System.out.println("Connection Failed");
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void insert_data(Connection conn, String user, String email, String pass) {
        Statement statement;
        try {
            String query = String.format("INSERT INTO test.registration(username, email, password) VALUES('%s','%s','%s');",user,email,pass);

            statement = conn.createStatement();

            int rowsAffected = statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            int minimumRowAffected = 0;
            int columnIndex = 1;

            if (rowsAffected > 0) {
                System.out.println("Data inserted successfully!");
            }
            else {
                System.out.println("Data insertion failed.");
            }

            if (rowsAffected > minimumRowAffected) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    lastGeneratedId = generatedKeys.getInt(columnIndex);
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static int get_last_inserted_id() {
        return lastGeneratedId;
    }

    public static Map<String, String> read_last_inserted_data(Connection conn) {
        Statement statement;
        ResultSet rs;

        Map<String, String> userData = new HashMap<>();

        try {
            String query = String.format("SELECT * FROM test.registration Where id = '%s';", get_last_inserted_id());

            statement = conn.createStatement();
            rs = statement.executeQuery(query);

            while(rs.next()){
                userData.put("username", rs.getString("username"));
                userData.put("email", rs.getString("email"));
                userData.put("password", rs.getString("password"));
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return userData;
    }
}