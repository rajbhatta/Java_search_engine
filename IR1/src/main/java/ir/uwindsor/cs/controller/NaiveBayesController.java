package ir.uwindsor.cs.controller;

import ir.uwindsor.cs.results.NaiveTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Paths;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * @author raj-pc
 *
 */
@WebServlet("/NaiveBayesController")
@MultipartConfig
public class NaiveBayesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ServletContext applicationServletContext;
    private String url;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NaiveBayesController() {
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
		
		
	}

	
	protected void doPost(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
		String searchingKeyword = servletRequest.getParameter("searchKeyWord");
		
		Part filePart = servletRequest.getPart("trainData"); 
	    String fileName = Paths.get(filePart.getName()).getFileName().toString(); 
	    InputStream fileContent = filePart.getInputStream();
		
	    
		NaiveTest naiveResult=new NaiveTest();
		
		try 
		{	
			servletRequest.setAttribute("naiveResult", naiveResult.computNaiveBayesResult(searchingKeyword,servletResponse,url,servletRequest,fileContent));	
			servletRequest.getRequestDispatcher("naivebayes-result.jsp").forward(servletRequest, servletResponse);	
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
