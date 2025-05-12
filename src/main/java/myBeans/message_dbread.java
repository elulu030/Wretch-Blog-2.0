package myBeans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.util.Map;
import java.util.HashMap;

public class message_dbread {
	static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    static final String DB_URL = "jdbc:mariadb://127.0.0.1/java_test";
    static final String USER = "root";
    static final String PASS = "123456789";
    private Connection conn = null;
    private PreparedStatement stmt = null;
	
    public message_dbread() {
    	 try {
             Class.forName(JDBC_DRIVER);
             conn = DriverManager.getConnection(DB_URL, USER, PASS);
             }catch(SQLException se) {
                 se.printStackTrace();
             } catch (Exception e) {
                 e.printStackTrace();
             }
    }
    
    public ArrayList<Map<String, Object>> getMessageById() {
    	ArrayList<Map<String, Object>> messageposts = new ArrayList<>();
        
        String sql = "SELECT id, content_id, user_id, user_name, message, time FROM message ";
        
        try {
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
            	Map<String, Object> messagepost = new HashMap<>();
            	messagepost.put("id", rs.getInt("id"));
                messagepost.put("content_id", rs.getInt("content_id"));
                messagepost.put("user_id", rs.getInt("user_id"));
                messagepost.put("user_name", rs.getString("user_name"));
                String rawmessage = rs.getString("message");
                if (rawmessage == null) rawmessage = "";
                
                StringBuilder formatted = new StringBuilder();
                for (int i = 0; i < rawmessage.length(); i++) {
                    formatted.append(rawmessage.charAt(i));
                    if ((i + 1) % 50 == 0) {
                        formatted.append("<br>");
                    }
                }
                messagepost.put("message", formatted.toString());
                messagepost.put("time", rs.getTimestamp("time"));
                messageposts.add(messagepost);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return messageposts;
    }
}


