package ir.uwindsor.cs.controller;

import ir.uwindsor.cs.results.SearchPageRankWithContent;

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
 * @author raj-pc
 *
 */
@WebServlet("/PageRankController")
public class NexusPRController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ServletContext applicationServletContext;
    private String url;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NexusPRController() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        super.init(config);
        applicationServletContext = config.getServletContext();
        url = applicationServletContext.getRealPath("/");
    }
    
    
	protected void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
		
		String searchingKeyword = servletRequest.getParameter("searchKeyWord");
		SearchPageRankWithContent searchPageRankWithContent=new SearchPageRankWithContent();
		
		
		try 
		{	
			servletRequest.setAttribute("pageRankResult", searchPageRankWithContent.searchContentWithPageRank(searchingKeyword,servletResponse,url));	
			servletRequest.getRequestDispatcher("pagerank-result.jsp").forward(servletRequest, servletResponse);	
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

}
