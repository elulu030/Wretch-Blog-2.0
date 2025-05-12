package myBeans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class db_write {
	static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    static final String DB_URL = "jdbc:mariadb://127.0.0.1/java_test";
    static final String USER = "root";
    static final String PASS = "123456789";
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private PreparedStatement stmt_user = null;
	
    public db_write() {
    	 try {
             Class.forName(JDBC_DRIVER);
             conn = DriverManager.getConnection(DB_URL, USER, PASS);
             }catch(SQLException se) {
                 se.printStackTrace();
             } catch (Exception e) {
                 e.printStackTrace();
             }
    }
	
    public String db_insert(String user_mail,String password,String sex,String phone,int identify_code, String name) {
        try {
        	int counter = 0;
        	String sql_user = "SELECT * FROM account WHERE user_mail = ?";
    		stmt_user = conn.prepareStatement(sql_user);
    		stmt_user.setString(1, user_mail);
    		ResultSet rs_user = stmt_user.executeQuery();
    		while(rs_user .next()) {
        		counter++;
    		}
    		if(counter >= 1) {
    			return "repeatApplication";
    		}
        	int active = 0;
        	String sql_insert = "INSERT INTO account (user_mail, password, sex, phone, identify_code, active, name) VALUES (?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql_insert);
            stmt.setString(1, user_mail);
            stmt.setString(2, password);
            stmt.setString(3, sex);
            stmt.setString(4, phone);
            stmt.setInt(5, identify_code);
            stmt.setInt(6, active);
            stmt.setString(7, name);
            stmt.executeUpdate();
            return "success";
        } catch (Exception e) {
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