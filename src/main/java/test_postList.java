

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * Servlet implementation class test_postList
 */
@WebServlet("/test_postList")
public class test_postList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public test_postList() {
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
	        
	        if (session != null && session.getAttribute("user_id") != null) {
	            userID = Integer.parseInt(session.getAttribute("user_id").toString());
	            System.out.print(userID);
	        } else {
	        	System.out.print("error");
	            response.sendRedirect("login_Form.jsp");
	            return;
	        }
	        
		 myBeans.content_dbread reader = new myBeans.content_dbread();
		 ArrayList<Map<String, Object>> posts = reader.getAllPosts(userID);
		 request.setAttribute("posts", posts);
		 request.getRequestDispatcher("postList.jsp").forward(request, response);
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
