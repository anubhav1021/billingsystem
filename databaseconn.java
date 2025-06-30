package billingsystempackage;

import java.sql.*;


public class databaseconn {
    Connection conn;
    Statement statement ;
    databaseconn(){
        try{    
            Class.forName("com.mysql.cj.jdbc.Driver");

        conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/illing_sys","root", "1106");
        statement =conn.createStatement();
        
        }
        catch(Exception p){
            // p.getname();
            
            p.printStackTrace();

        }
    }

    
}
