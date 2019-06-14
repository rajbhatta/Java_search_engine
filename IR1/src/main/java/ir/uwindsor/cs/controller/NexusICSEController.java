package ir.uwindsor.cs.controller;

import ir.uwindsor.cs.results.SearchICSE;

import java.io.IOException;

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
@WebServlet("/NexusICSEController")
public class NexusICSEController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ServletContext applicationServletContext;
    private String url;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NexusICSEController() {
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
		SearchICSE searchICSE=new SearchICSE();
		
		
		try 
		{	
			servletRequest.setAttribute("icseResult", searchICSE.searchICSE(searchingKeyword,servletResponse,url));	
			servletRequest.getRequestDispatcher("icse-result.jsp").forward(servletRequest, servletResponse);	
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

}
