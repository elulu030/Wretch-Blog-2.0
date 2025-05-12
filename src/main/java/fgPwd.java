

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class fgPwd
 */
@WebServlet("/fgPwd")
public class fgPwd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public fgPwd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 response.sendRedirect("fgPwd.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String user_mail = request.getParameter("user");
	    String sex = request.getParameter("sex");
	    String phone = request.getParameter("phone");
	    String baseurl = request.getParameter("baseurl");
	    String msg;
	    System.out.println(user_mail);
	    System.out.println(phone);
	    
	    myBeans.db_read query = new myBeans.db_read();
	    String pwd = query.db_ftwd(user_mail,phone);
	    System.out.println(pwd);
	    
	    if("error_user".equals(pwd)) {
	    	msg = "查詢失敗：輸入條件有誤";
            request.setAttribute("errorMessage", msg);
            request.getRequestDispatcher("login_Form.jsp").forward(request, response);
            return;
	    }else if("error".equals(pwd)) {
	    	msg = "查詢失敗";
            request.setAttribute("errorMessage", msg);
            request.getRequestDispatcher("login_Form.jsp").forward(request, response);
            return;
	    }
	    
	    myBeans.sendMail mailSender = new myBeans.sendMail();
	    boolean success = mailSender.sendPwdEmail(user_mail, pwd, baseurl);
	    
	    if(success) {
	    	msg = "查詢成功：請至您的信箱收取密碼信件";
            request.setAttribute("errorMessage", msg);
            request.getRequestDispatcher("login_Form.jsp").forward(request, response);
            return;
	    }else {
	    	msg = "查詢失敗：請稍後再試";
            request.setAttribute("errorMessage", msg);
            request.getRequestDispatcher("login_Form.jsp").forward(request, response);
            return;
	    }
	    
	}

}
