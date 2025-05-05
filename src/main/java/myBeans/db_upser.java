package myBeans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.PreparedStatement;
public class db_upser {
	static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    static final String DB_URL = "jdbc:mariadb://127.0.0.1/java_test";
    static final String USER = "root";
    static final String PASS = "123456789";
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private PreparedStatement stmt_user = null;
	
    public db_upser() {
    	 try {
             Class.forName(JDBC_DRIVER);
             conn = DriverManager.getConnection(DB_URL, USER, PASS);
             }catch(SQLException se) {
                 se.printStackTrace();
             } catch (Exception e) {
                 e.printStackTrace();
             }
    	}
    public String db_active(String user_name, int code) {
        Integer db_code = null;
        int active = 0;

        try {
            String sql = "SELECT * FROM account WHERE user_mail = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, user_name);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                db_code = rs.getInt(6); // 識別碼欄位
                active = rs.getInt(7);

                if (active == 0 && db_code == code) {
                    // 驗證成功，啟用帳號
                    String updateSql = "UPDATE account SET active = 1 WHERE user_mail = ?";
                    PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                    updateStmt.setString(1, user_name);
                    updateStmt.executeUpdate();
                    updateStmt.close();

                    System.out.println("帳號已啟用");
                    return "true";
                } else {
                    System.out.println("識別碼錯誤或帳號已啟用");
                    return "false_already";
                }

            } else {
                System.out.println("查無帳號密碼");
                return "false_nocount";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "false_error";
        	}
    }
   }