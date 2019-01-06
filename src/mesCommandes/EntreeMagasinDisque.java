package mesCommandes;
import java.io.*;

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
		
		
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Cookie[] cookies = request.getCookies();
        
        out.println("<HTML><HEAD><TITLE>"
   			 + "Connexion"
   			 + "</TITLE></HEAD><BODY>");
        for(int i = 0; i < cookies.length; i++) {
        	Cookie cookie = cookies[i];
        	out.println("<p>Cookie name"+ cookie.getName());
        	out.println("<p>Cookie value"+ cookie.getValue());
        }
        out.println("<p>Bienvenue </p></BODY></HTML>");
		 //  ********************************************************************************************        
	//  Cr�ez deux variables de session : � nomClient � qui a pour valeur le nom de l�utilisateur  
	//  et � stockCourant �  qui a pour valeur une instance de la classe Stock, il contient une liste des disques disponibles
	// et appelez la servlet  AfficherLesDisques.java
	//  ********************************************************************************************

              }
  //  ********************************************************************************************
		//}
   
	public void doPost(HttpServletRequest request,  HttpServletResponse response) throws IOException, ServletException
	      { 
	         doGet(request, response);
	      }
}
