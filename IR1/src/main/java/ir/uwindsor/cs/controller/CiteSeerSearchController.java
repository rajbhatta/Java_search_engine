package ir.uwindsor.cs.controller;

import ir.uwindsor.cs.results.Search;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SearchController
 */
@WebServlet("/SearchController")
public class CiteSeerSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ServletContext servletContext;
    private String basePath;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CiteSeerSearchController() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    @Override
    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        super.init(config);
        servletContext = config.getServletContext();
        basePath = servletContext.getRealPath("/");
    }
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String searchValue = request.getParameter("searchKeyWord");
		
		Search searchObj=new Search();
		PrintWriter out= response.getWriter();
		
		try 
		{

			request.setAttribute("result", searchObj.search(searchValue,response,basePath));			
			request.getRequestDispatcher("citeseer-result.jsp").forward(request, response);
			
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
