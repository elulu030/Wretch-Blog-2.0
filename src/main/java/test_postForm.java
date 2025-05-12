

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class postForm
 */
@WebServlet("/test_postForm")
public class test_postForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public test_postForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		int userID;
	    if (session != null && session.getAttribute("user_name") != null && session.getAttribute("user_id") != null) {
	        try {
	        	response.sendRedirect("postForm.jsp");
	        } catch (NumberFormatException e) {
	            e.printStackTrace();
	            response.sendRedirect("login_Form.jsp");
	            return;
	        }
	    } else {
	        response.sendRedirect("login_Form.jsp");
	        return;
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String title = request.getParameter("title");
	    String author = request.getParameter("author");
	    String content = request.getParameter("content");
	    if (content != null) {
	        content = content.replaceAll("(\r\n|\n)", "<br>");
	    }
	    String password = request.getParameter("pwd");
	    	
	    int userID;
	    Integer pwd = null;
	    
	    if (password != null && !password.trim().isEmpty()) {
	    	pwd = Integer.parseInt(password.trim());
	    }
	    
	    HttpSession session = request.getSession(false);

	    if (session != null && session.getAttribute("user_name") != null && session.getAttribute("user_id") != null) {
	        try {
	            userID = Integer.parseInt(session.getAttribute("user_id").toString());
	            System.out.print(userID);
	        } catch (NumberFormatException e) {
	            e.printStackTrace();
	            response.sendRedirect("login_Form.jsp");
	            return;
	        }
	    } else {
	        response.sendRedirect("login_Form.jsp");
	        return;
	    }
	    
	    myBeans.content_dbwrite writetodb = new myBeans.content_dbwrite();
	    String result = writetodb.content__insert(userID,title,author,content,pwd);
	    
	    if (result != null && !result.isEmpty()) {
	    	switch (result) {
	    	 	case "success":
	    	 		String msg="新增成功！";
            		request.setAttribute("errorMessage", msg);
            		request.getRequestDispatcher("/index").forward(request, response);
            		break;
	    	 	default:
            		msg="發表失敗，請稍後再試！";
            		request.setAttribute("errorMessage", msg);
            		request.getRequestDispatcher("postForm.jsp").forward(request, response);
            		break;
            	
	    	}
	    }
	}

}
