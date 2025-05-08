package myBeans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.PreparedStatement;

public class db_read {
	static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    static final String DB_URL = "jdbc:mariadb://127.0.0.1/java_test";
    static final String USER = "root";
    static final String PASS = "123456789";
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private PreparedStatement stmt_user = null;
	
    public db_read() {
    	 try {
             Class.forName(JDBC_DRIVER);
             conn = DriverManager.getConnection(DB_URL, USER, PASS);
             }catch(SQLException se) {
                 se.printStackTrace();
             } catch (Exception e) {
                 e.printStackTrace();
             }
    }
	
    public ArrayList<String> db_check(String user_mail,String pass) {
    	String db_user=null;
    	String db_pass=null;
    	String db_id=null;
    	String db_sex=null;
    	int code;
    	int active = 0;
    	ArrayList<String> result = new ArrayList<String>();
    	try {
    		String sql_user = "SELECT * FROM account WHERE user_mail = ?";
    		stmt_user = conn.prepareStatement(sql_user);
    		stmt_user.setString(1, user_mail);
    		ResultSet rs_user = stmt_user.executeQuery();
    		while(rs_user .next()) {
        		db_user =rs_user.getString(2);
    		}
    		String sql = "SELECT * FROM account WHERE user_mail = ? AND password = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, user_mail);
            stmt.setString(2, pass);
            ResultSet rs = stmt.executeQuery();
            while(rs .next()) {
            		db_id =rs.getString(1);
            		db_pass =rs.getString(3);
            		db_sex =rs.getString(4);
            		code =rs.getInt(6);
            		active=rs.getInt(7);
            }
            System.out.println(db_user+","+user_mail);
            System.out.println(db_pass+","+pass);
            System.out.println(active);
            System.out.println(db_id);
            if(db_user != null && db_user.equals(user_mail)) {
            	if(db_pass != null && db_pass.equals(pass)) {
            		if(active == 0) {
            			result.add("false_active");
            			result.add(user_mail);
            			System.out.println(result);
            			return result;
            		}
            		result.add("success");
            		result.add(user_mail);
            		result.add(db_id);
            		System.out.println(result);
            		return result;
            	}else {
            		result.add("false_pass");
            		System.out.println(result);
            		return result;
            	}	
            }	
            else {
            	result.add("false_user");
            	System.out.println(result);
            	return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.add("false");
            System.out.println(result);
            return result;
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
    
    public String db_ftwd(String user_mail,String phone){
    	String db_pass=null;
    	System.out.println(user_mail+phone);
    	try {
    		String sql_user = "SELECT password  FROM account WHERE user_mail = ? AND phone = ?";
    		stmt_user = conn.prepareStatement(sql_user);
    		stmt_user.setString(1, user_mail);
    		stmt_user.setString(2, phone);
    		ResultSet rs_user = stmt_user.executeQuery();
    		while(rs_user .next()) {
        		db_pass =rs_user.getString(1);
    		}
    		System.out.println(db_pass);
    		return (db_pass != null) ? db_pass : "error_user";
    	}catch (Exception e) {
            e.printStackTrace();
            return "error";
    	}finally {
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