package mesCommandes;
import java.io.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;
public class EntreeMagasinDisque extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	   {	  
		String nomClient = request.getParameter("nom");
		Stock stockCourant = new Stock();
		ArrayList<String> listeDisques;
		
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        session.setAttribute("name", nomClient);
        session.setAttribute("stock", stockCourant);
        response.sendRedirect("achat");
        

	   }
   
	public void doPost(HttpServletRequest request,  HttpServletResponse response) throws IOException, ServletException
	      { 
	         doGet(request, response);
	      }
}
