package mesCommandes;
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;


public class AfficherLesDisques extends HttpServlet {


	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException {
		String nom = null;
		Stock stockDisponible = null;
		
	//  ********************************************************************************************        
	//   initialisez  nom et  stockDisponible  à partir des variables de session.
	//      pour l’instant il n’est pas fait de test pour savoir si ces variables existent, par la suite ce test sera fait par un filtre.
	//  ********************************************************************************************                             	

	
	
	
   //  ********************************************************************************************  
	   response.setContentType("text/html");
	   PrintWriter out = response.getWriter();
	   out.println("<html>");
	   out.println("<head>");
	   out.println("<title> Commande de disques </title>");
	   out.println("<meta http-equiv='Content-Type' content='text/html; charset=iso-8859-1' >");
	   out.println("</head>");
	   out.println("<body bgcolor=\"white\">");
	   out.println("<h1> Super Marché du disque </h1>");
	   out.println("<h3>" + "Bonjour " + nom.toUpperCase() + " vous pouvez commander un disque" + "</h3>");

		//  ********************************************************************************************        
	   	// Appel de la méthode. afficherDisquesEnVente de Magasin, avec trois paramètres : 
		//    - le stockDisponible)
		//    - le « PrintWriter » pour pouvoir rajouter ces disques dans la page de la réponse HTML,
		//    - et le repertoire courant de votre application  "request.getContextPath()", pour trouver les images des disques
		// 
	 	//  ********************************************************************************************                   
		// cet affichage permet d'appeler "CommanderUnDisque" avec en paramètre le disque choisi

		
		
	    //  ******************************************************************************************** 
	                 
	   out.println("</body>");
	   out.println("</html>");
	} 


public void doPost(HttpServletRequest request,  HttpServletResponse response) throws IOException, ServletException
{
   doGet(request, response);
}

}
