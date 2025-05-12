package myBeans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.util.Map;
import java.util.HashMap;

public class content_dbread {
	static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    static final String DB_URL = "jdbc:mariadb://127.0.0.1/java_test";
    static final String USER = "root";
    static final String PASS = "123456789";
    private Connection conn = null;
    private PreparedStatement stmt = null;
	
    public content_dbread() {
    	 try {
             Class.forName(JDBC_DRIVER);
             conn = DriverManager.getConnection(DB_URL, USER, PASS);
             }catch(SQLException se) {
                 se.printStackTrace();
             } catch (Exception e) {
                 e.printStackTrace();
             }
    }
    
    public ArrayList<Map<String, Object>> getAllPosts(int userID) {
        ArrayList<Map<String, Object>> posts = new ArrayList<>();
        String sql = "";
        if(userID == 1) {
        	sql = "SELECT ID, Title, Content, pwd, UserID, Author, created_at FROM content ORDER BY created_at DESC";
        }else {
        	 sql = "SELECT ID, Title, Content, pwd, UserID, Author, created_at FROM content WHERE UserID = ? ORDER BY created_at DESC";
        }
        System.out.print("1LL");
        try {
        	stmt = conn.prepareStatement(sql);
        	if (userID != 1) {
        	    stmt.setInt(1, userID);
        	}
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
            	Map<String, Object> post = new HashMap<>();
                post.put("id", rs.getInt("ID"));
                post.put("user_id", rs.getInt("userID"));
                System.out.println(userID);
                post.put("title", rs.getString("Title"));
                post.put("author", rs.getString("Author"));
                int pwd = rs.getInt("pwd");
                if (rs.wasNull()) {
                    post.put("pwd", null); 
                } else {
                    post.put("pwd", pwd); 
                }
                String rawContent = rs.getString("Content");
                if (rawContent == null) rawContent = "";

                StringBuilder formatted = new StringBuilder();
                for (int i = 0; i < rawContent.length(); i++) {
                    formatted.append(rawContent.charAt(i));
                    if ((i + 1) % 50 == 0) {
                        formatted.append("<br>");
                    }
                }
                post.put("Content", formatted.toString());
                post.put("created_at", rs.getTimestamp("created_at"));
                posts.add(post);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();  // 如果你需要重複使用連線，可以不要關閉
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return posts;
    }
    
    public Map<String, Object> getPostById(int id) {
        Map<String, Object> post = new HashMap<>();
        
        String sql = "SELECT ID, Title, Content, pwd, UserID, Author, created_at FROM content WHERE ID = ?";
        
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                post.put("id", rs.getInt("ID"));
                post.put("title", rs.getString("Title"));
                post.put("author", rs.getString("Author"));
                String rawContent = rs.getString("Content");
                if (rawContent == null) rawContent = "";
                
                StringBuilder formatted = new StringBuilder();
                for (int i = 0; i < rawContent.length(); i++) {
                    formatted.append(rawContent.charAt(i));
                    if ((i + 1) % 50 == 0) {
                        formatted.append("<br>");
                    }
                }
                post.put("Content", formatted.toString());
                post.put("created_at", rs.getTimestamp("created_at"));
                post.put("userID", rs.getInt("UserID"));
                int pwd = rs.getInt("pwd");
                if (pwd==0) {
                    post.put("pwd", null); 
                } else {
                    post.put("pwd", pwd); 
                }
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
        return post;
    }
}
