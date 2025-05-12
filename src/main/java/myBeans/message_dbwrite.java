package myBeans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class message_dbwrite {
	static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    static final String DB_URL = "jdbc:mariadb://127.0.0.1/java_test";
    static final String USER = "root";
    static final String PASS = "123456789";
    private Connection conn = null;
    private PreparedStatement stmt = null;
    
    public message_dbwrite() {
      	 try {
               Class.forName(JDBC_DRIVER);
               conn = DriverManager.getConnection(DB_URL, USER, PASS);
               }catch(SQLException se) {
                   se.printStackTrace();
               } catch (Exception e) {
                   e.printStackTrace();
               }
       	}
    
    public String message_insert(int content_id,int user_id,String user_name,String message) {
		try {
		String sql_insert = "INSERT INTO message (content_id, user_id, user_name, message) VALUES (?, ?, ?, ?)";
		 stmt = conn.prepareStatement(sql_insert);
         stmt.setInt(1, content_id);
         stmt.setInt(2, user_id);
         stmt.setString(3, user_name);
         stmt.setString(4, message);
         stmt.executeUpdate();
         return "success";
		}catch (Exception e) {
            e.printStackTrace();
            return "false";
    	} finally {
    		try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
	}
}

