

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class deleteMs
 */
@WebServlet("/deleteMs")
public class deleteMs extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteMs() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 int id = Integer.parseInt(request.getParameter("id"));
		    
		    myBeans.delete_message delete = new myBeans.delete_message();
		    String result = delete.deleteOneMs(id);
		    if (result != null && !result.isEmpty()) {
		    	switch (result) {
	            	case "success":
	            		String msg="刪除成功！";
	            		request.setAttribute("errorMessage", msg);
	            		request.getRequestDispatcher("/index").forward(request, response);
	            		break;
	            	default:
	            		msg="刪除失敗，請稍後再試！";
	            		request.setAttribute("errorMessage", msg);
	            		request.getRequestDispatcher("/index").forward(request, response);
	            		break;
		    		}
		    	}
			}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
