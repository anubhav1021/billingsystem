import java.sql.*;

public class JDBCExample {
    public static void main(String[] args) {
        // JDBC URL, username, and password of MySQL server
        String url = "jdbc:mysql://localhost:3306/testdb";
        String user = "root";          // replace with your MySQL username
        String password = "password";  // replace with your MySQL password

        // JDBC variables for opening, closing and managing connection
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Open connection
            conn = DriverManager.getConnection(url, user, password);

            // Create statement
            stmt = conn.createStatement();

            // Execute query
            rs = stmt.executeQuery("SELECT * FROM users");

            // Print results
            while (rs.next()) {
                System.out.println(
                    "ID: " + rs.getInt("id") +
                    ", Name: " + rs.getString("name") +
                    ", Email: " + rs.getString("email")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try { if (rs != null) rs.close(); } catch (SQLException se) { se.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (SQLException se) { se.printStackTrace(); }
            try { if (conn != null) conn.close(); } catch (SQLException se) { se.printStackTrace(); }
        }
    }
}
