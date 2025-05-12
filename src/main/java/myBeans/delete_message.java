package myBeans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class delete_message {
	static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    static final String DB_URL = "jdbc:mariadb://127.0.0.1/java_test";
    static final String USER = "root";
    static final String PASS = "123456789";
    private Connection conn = null;
    private PreparedStatement stmt = null;
    
    public delete_message() {
   	 try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            }catch(SQLException se) {
                se.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
   	 }
    public String deleteOneMs(int ID) {
    	try {
    		String sql_delete = "DELETE FROM message WHERE id = ?";
    		 stmt = conn.prepareStatement(sql_delete);
             stmt.setInt(1, ID);
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
