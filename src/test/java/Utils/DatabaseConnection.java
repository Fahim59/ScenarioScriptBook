package Utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public Connection connect_to_db(){
        Connection conn = null;
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://10.11.200.96:5432/"+"MTDB","microt","micro@trust#23");

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
}
