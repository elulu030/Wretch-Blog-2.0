

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

/**
 * Servlet implementation class register
 */
@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.print(1);
		response.sendRedirect("register_Form.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // 獲取表單提交的資料
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
	    String msg, userMail, password, phone, sex, baseurl, name;
	    userMail = request.getParameter("user");
	    password = request.getParameter("register_password");
	    sex = request.getParameter("sex");
	    phone = request.getParameter("phone");
	    baseurl = request.getParameter("baseurl");
	    name = request.getParameter("name");

	    // 生成驗證碼
	    Random rand = new Random();
	    int identifyCode = rand.nextInt(900) + 100;

	    myBeans.db_write write = new myBeans.db_write();
	    String result = write.db_insert(userMail, password, sex, phone, identifyCode, name);

	    if ("success".equals(result)) {
	    	try {
	    		myBeans.sendMail mailSender = new myBeans.sendMail();
	    		boolean success = mailSender.sendVerificationEmail(userMail, identifyCode, baseurl);
	    		if(success) {
	    			msg = "驗證信已寄出";
	                request.setAttribute("errorMessage", msg);
	                request.getRequestDispatcher("login_Form.jsp").forward(request, response);
	    		}else {
	    			request.setAttribute("errorMessage", "發送郵件時出現錯誤，請稍後再試");
		            request.getRequestDispatcher("register_Form.jsp").forward(request, response);
	    		}
	        } catch (Exception e) {
	            e.printStackTrace();
	            request.setAttribute("errorMessage", "發送郵件時出現錯誤，請稍後再試");
	            request.getRequestDispatcher("register_Form.jsp").forward(request, response);
	        }
	    } else {
	        // 失敗，處理錯誤訊息並轉發回註冊頁
	        if ("repeatApplication".equals(result)) {
	            msg = "該郵箱已經註冊過，請使用其他郵箱！";
	        } else {
	            msg = "註冊失敗，請稍後再試。";
	        }
	        request.setAttribute("errorMessage", msg);
	        request.getRequestDispatcher("register_Form.jsp").forward(request, response);
	    }
	}

}
