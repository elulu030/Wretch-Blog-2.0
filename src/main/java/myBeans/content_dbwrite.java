package myBeans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class content_dbwrite {
	static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    static final String DB_URL = "jdbc:mariadb://127.0.0.1/java_test";
    static final String USER = "root";
    static final String PASS = "123456789";
    private Connection conn = null;
    private PreparedStatement stmt = null;
    
    public content_dbwrite() {
   	 try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            }catch(SQLException se) {
                se.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
    	}

	
	public String content__insert(int UserID,String Title,String author,String Content,Integer pwd) {
		try {
		String sql_insert = "INSERT INTO content (UserID, Title, author, Content, pwd) VALUES (?, ?, ?, ?, ?)";
		 stmt = conn.prepareStatement(sql_insert);
         stmt.setInt(1, UserID);
         stmt.setString(2, Title);
         stmt.setString(3, author);
         stmt.setString(4, Content);
         if (pwd != null) {
             stmt.setInt(5, pwd);
         } else {
             stmt.setNull(5, java.sql.Types.INTEGER);
         }
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