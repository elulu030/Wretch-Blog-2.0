

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class message
 */
@WebServlet("/message")
public class message extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public message() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("postmessage");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		int user_id;
		String user_name;
		int content_id = Integer.parseInt(request.getParameter("content_id"));
	    String message = request.getParameter("message");
	    if (message != null) {
	    	message = message.replaceAll("(\r\n|\n)", "<br>");
	    }
	    
	    HttpSession session = request.getSession(false);
	    user_id = Integer.parseInt(session.getAttribute("user_id").toString());
	    user_name = session.getAttribute("user_name").toString();
	          
	    myBeans.message_dbwrite mswritetodb = new myBeans.message_dbwrite();
	    String result = mswritetodb.message_insert(content_id,user_id,user_name,message);
	    System.out.println("postmessage "+result);
	    
	    if (result != null && !result.isEmpty()) {
	    	switch (result) {
	    	 	case "success":
	    	 		String msg="留言新增成功！";
            		request.setAttribute("errorMessage", msg);
            		request.getRequestDispatcher("/index").forward(request, response);
            		break;
	    	 	default:
            		msg="留言新增失敗，請稍後再試！";
            		request.setAttribute("errorMessage", msg);
            		request.getRequestDispatcher("postForm.jsp").forward(request, response);
            		break;
            	
	    	}
	    }
	}

}