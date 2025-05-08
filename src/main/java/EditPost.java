

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class EditPost
 */
@WebServlet("/EditPost")
public class EditPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditPost() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		int userID;
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		 HttpSession session = request.getSession(false);
		    if (session != null && session.getAttribute("user_name") != null && session.getAttribute("user_id") != null) {
		        try {
		            userID = Integer.parseInt(session.getAttribute("user_id").toString());
		        } catch (NumberFormatException e) {
		            e.printStackTrace();
		            response.sendRedirect("login_Form.jsp");
		            return;
		        }
		    } else {
		        response.sendRedirect("login_Form.jsp");
		        return;
		    }
		    
		    myBeans.content_dbread reader = new myBeans.content_dbread();
		    Map<String, Object> post = reader.getPostById(id);
		    request.setAttribute("post", post);
			request.getRequestDispatcher("editPost.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		int userID;
		String id = request.getParameter("id");
		String title = request.getParameter("title");
	    String author = request.getParameter("author");
	    String content = request.getParameter("content");
	    String password = request.getParameter("pwd");
	    
	    Integer pwd = null;
	    if (password != null && !password.trim().isEmpty()) {
	    	pwd = Integer.parseInt(password.trim());
	    }
	    
	    HttpSession session = request.getSession(false);
	    if (session != null && session.getAttribute("user_name") != null && session.getAttribute("user_id") != null) {
	        try {
	            userID = Integer.parseInt(session.getAttribute("user_id").toString());
	        } catch (NumberFormatException e) {
	            e.printStackTrace();
	            response.sendRedirect("login_Form.jsp");
	            return;
	        }
	    } else {
	        response.sendRedirect("login_Form.jsp");
	        return;
	    }
	    
	    myBeans.Edit_content edit = new myBeans.Edit_content();
	    String result = edit.editOne(id,userID,title,author,content,pwd);
	    
	    String msg = "";
	    
	    if (result != null && !result.isEmpty()) {
	    	switch (result) {
	    	 	case "success":
	    	 		msg="修改成功！";
            		request.setAttribute("errorMessage", msg);
            		request.getRequestDispatcher("postList.jsp").forward(request, response);
            		break;
	    	 	default:
            		msg="修改失敗，請稍後再試！";
            		request.setAttribute("errorMessage", msg);
            		request.getRequestDispatcher("postForm.jsp").forward(request, response);
            		break;
            	
	    	}
	    }
	    
	}

}
