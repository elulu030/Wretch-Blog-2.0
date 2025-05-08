

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("user_name") != null) {
			response.sendRedirect("postForm.jsp");
        } else {
        	 response.sendRedirect("login_Form.jsp");
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // 取得使用者輸入的帳號與密碼
	    String user = request.getParameter("user");
	    String pass = request.getParameter("login_password");
	    String remember = request.getParameter("remember");
	    
	    HttpSession session = request.getSession();

	    if ("admin".equals(user) && "admin".equals(pass)) {
	        session.setAttribute("user_name", "admin");
	        response.sendRedirect("postForm.jsp");
	        return;
	    }
	    
	    myBeans.db_read check = new myBeans.db_read();
	    ArrayList<String> result = check.db_check(user, pass);
	    
	    
	    String msg;

	    if (result != null && !result.isEmpty()) {
	        String status = result.get(0);
	        System.out.println(status);
	        switch (status) {
	            case "success":
	                session.setAttribute("user_name", result.get(1));
	                session.setAttribute("user_id", result.get(2));
	                response.sendRedirect("postForm.jsp");
	                break;

	            case "false_user":
	                msg = "登入失敗：帳號錯誤";
	                request.setAttribute("errorMessage", msg);
	                request.getRequestDispatcher("login_Form.jsp").forward(request, response);
	                break;

	            case "false_pass":
	                msg = "登入失敗：密碼錯誤";
	                request.setAttribute("errorMessage", msg);

	                // 記住錯誤的使用者帳號一小時
	                System.out.println(remember);
	                if ("true".equals(remember)) {
	                	Cookie cookie = new Cookie("user_name", user);
	                	cookie.setMaxAge(3600); 
	                	response.addCookie(cookie);
	                	System.out.println("cookie");
	                }
	                request.getRequestDispatcher("login_Form.jsp").forward(request, response);
	                break;
	                
	            case "false_active":
	            	session.setAttribute("user_name", result.get(1));
	            	msg = "登入失敗：帳號尚未啟用，請輸入識別碼以啟用帳號。";
	            	request.setAttribute("errorMessage", msg);
	            	request.setAttribute("CodeInput", true);
	            	request.getRequestDispatcher("login_Form.jsp").forward(request, response);
	            	break;
	            	
	            default:
	                msg = "登入失敗，請稍後再試。";
	                request.setAttribute("errorMessage", msg);
	                request.getRequestDispatcher("login_Form.jsp").forward(request, response);
	                break;
	        }
	    } else {
	        msg = "登入失敗，系統錯誤或資料格式錯誤。";
	        request.setAttribute("errorMessage", msg);
	        request.getRequestDispatcher("login_Form.jsp").forward(request, response);
	    }
	}
}
