package myBeans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class Edit_content {
	static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    static final String DB_URL = "jdbc:mariadb://127.0.0.1/java_test";
    static final String USER = "root";
    static final String PASS = "123456789";
    private Connection conn = null;
    private PreparedStatement stmt = null;
    
    public Edit_content() {
   	 try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            }catch(SQLException se) {
                se.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
   	 }
    public String editOne(String idStr,int userID,String title,String author,String content,Integer pwd) {
    	try {
    		int id = Integer.parseInt(idStr);
    		
    		 String sql = "UPDATE content SET Title = ?, author = ?, Content = ?, pwd = ? WHERE id = ? AND userID = ?";
             stmt = conn.prepareStatement(sql);

             stmt.setString(1, title);
             stmt.setString(2, author);
             stmt.setString(3, content);
             if (pwd != null) {
                 stmt.setInt(4, pwd);
             } else {
                 stmt.setNull(4, java.sql.Types.INTEGER);
             }   
             stmt.setInt(5, id);      
             stmt.setInt(6, userID);      
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
