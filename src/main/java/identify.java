

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class identify
 */
@WebServlet("/identify")
public class identify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public identify() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String user_mail = request.getParameter("user_mail");
		 String identify_code = request.getParameter("identify_code");
		 System.out.println(user_mail);
		 System.out.println(identify_code);
		 if (identify_code == null || identify_code.isEmpty()) {
		        // 如果識別碼為空，回傳錯誤訊息
		        String msg = "識別碼不能為空。";
		        request.setAttribute("errorMessage", msg);
		        request.getRequestDispatcher("login_Form.jsp").forward(request, response);
		        return; // 停止執行後續程式
		    }
		 int code = Integer.parseInt( identify_code);
		 myBeans.db_upser check = new myBeans.db_upser();
		 String result = check.db_active(user_mail, code);
		 String msg;
		 System.out.println(user_mail);
		 System.out.println(code);
		 System.out.println(result);
		 if (result != null && !result.isEmpty()) {
			 switch (result) {
	            case "true":
	                response.sendRedirect("/jakartaee-hello-world/index");
	                break;

	            case "false_already":
	                msg = "識別碼錯誤或帳號已啟用";
	                request.setAttribute("errorMessage", msg);
	                request.getRequestDispatcher("login_Form.jsp").forward(request, response);
	                break;

	            case "false_nocount":
	                msg = "查無帳號密碼";
	                request.setAttribute("errorMessage", msg);
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
