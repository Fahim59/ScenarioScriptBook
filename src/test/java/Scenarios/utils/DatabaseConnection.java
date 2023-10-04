package Scenarios.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
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
            //BufferedReader BR = new BufferedReader(new FileReader("E:\\My Laptop\\Extra.txt"));
            BufferedReader BR = new BufferedReader(new FileReader("E:\\My Laptop\\Real.txt"));
            String content = "";

            for (; (content = BR.readLine()) != null; ) {
                String[] arrOfStr = content.split("\\| ", 3);

                db = arrOfStr[0];
                user = arrOfStr[1];
                pass = arrOfStr[2];
            }

            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://10.11.200.96:5432/"+db,user,pass);

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

    public void delete_data(Connection conn) {
        String samity = "916";

        try {
            String budget_info = "DELETE FROM coop.budget_info WHERE samity_id = CAST(? AS integer)";
            String gl_transaction = "DELETE FROM coop.gl_transaction WHERE samity_id = CAST(? AS integer)";
            String committee_member = "DELETE FROM coop.committee_member WHERE samity_id = CAST(? AS integer)";
            String committee_info = "DELETE FROM coop.committee_info WHERE samity_id = CAST(? AS integer)";
            String member_financial_info = "DELETE FROM coop.member_financial_info WHERE samity_id = CAST(? AS integer)";
            String samity_authorized_person = "DELETE FROM coop.samity_authorized_person WHERE samity_id = CAST(? AS integer)";
            String samity_info = "DELETE FROM coop.samity_info WHERE id = CAST(? AS integer)";

            PreparedStatement preparedStatement = conn.prepareStatement(budget_info);
            preparedStatement.setString(1, samity);

            int deletedRows = preparedStatement.executeUpdate();
            assert (deletedRows > 0);

            preparedStatement.close();

            PreparedStatement preparedStatement1 = conn.prepareStatement(gl_transaction);
            preparedStatement1.setString(1, samity);

            int deletedRows1 = preparedStatement1.executeUpdate();
            assert (deletedRows1 > 0);

            preparedStatement1.close();

            PreparedStatement preparedStatement3 = conn.prepareStatement(committee_member);
            preparedStatement3.setString(1, samity);

            int deletedRows3 = preparedStatement3.executeUpdate();
            assert (deletedRows3 > 0);

            preparedStatement3.close();

            PreparedStatement preparedStatement4 = conn.prepareStatement(committee_info);
            preparedStatement4.setString(1, samity);

            int deletedRows4 = preparedStatement4.executeUpdate();
            assert (deletedRows4 > 0);

            preparedStatement4.close();

            PreparedStatement preparedStatement5 = conn.prepareStatement(member_financial_info);
            preparedStatement5.setString(1, samity);

            int deletedRows5 = preparedStatement5.executeUpdate();
            assert (deletedRows5 > 0);

            preparedStatement5.close();

            PreparedStatement preparedStatement6 = conn.prepareStatement(samity_authorized_person);
            preparedStatement6.setString(1, samity);

            int deletedRows6 = preparedStatement6.executeUpdate();
            assert (deletedRows6 > 0);

            preparedStatement6.close();

            PreparedStatement preparedStatement7 = conn.prepareStatement(samity_info);
            preparedStatement7.setString(1, samity);

            int deletedRows7 = preparedStatement7.executeUpdate();
            assert (deletedRows7 > 0);

            preparedStatement7.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}