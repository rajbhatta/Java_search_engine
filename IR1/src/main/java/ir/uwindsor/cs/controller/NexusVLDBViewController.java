package ir.uwindsor.cs.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author raj-pc
 *
 */

@WebServlet("/NexusVLDBViewController")
public class NexusVLDBViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NexusVLDBViewController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResposne) throws ServletException, IOException {
	
		servletRequest.getRequestDispatcher("pagerank-search.jsp").forward(servletRequest, servletResposne);
		
	}

}
