package mesCommandes;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/servlet/verifCompte")
public class VerificationCompte extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final int EXPIRE = 60000;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         String nomRecu, motPasseRecu;
         String inscrireClient, connecterClient;
         String nomCookie =null, motPasseCookie =null;
         Cookie[] cookies =null;

     //  ********************************************************************************************        
     //  initialisation des diff�rents param�tres possibles
     //  ********************************************************************************************    
         String requestMode = request.getParameter("mode");
         if(requestMode.equals("inscription")) {
        	 nomRecu = request.getParameter("nom");
        	 motPasseRecu = request.getParameter("mdp");
        	 if(nomRecu.length()>4 && motPasseRecu.length()>4) {
        		Cookie cookie = new Cookie(nomRecu, motPasseRecu); 
        		cookie.setMaxAge(EXPIRE);
        		response.addCookie(cookie);
        		response.sendRedirect("formulaire?inscriptionFait=True&nom="+nomRecu);
        	 }else {
        		 String erreurMsg = "Nom ou Mdp non valide\n"
        				 +"Il faut au moins 4 caractères\n";
        		 response.sendRedirect("formulaire?demande=inscription&erreurConnexion=" + erreurMsg);
        	 }
         }else if(requestMode.equals("connexion")) {
        	 nomRecu = request.getParameter("nom");
        	 motPasseRecu = request.getParameter("mdp");
        	 cookies = request.getCookies();
        	 if((cookies!=null)&&(cookies.length>0)) {
        		 motPasseCookie= Util.rechercheValeurCookies(cookies,nomRecu);
        		 if((motPasseCookie!= null) && (motPasseCookie.equals(motPasseRecu))) {
        			 response.sendRedirect("entree?nom=" + nomRecu);
        		 }else {
            		 String erreurMsg = "Votre identifiant ou mdp est errone";
            		 response.sendRedirect("formulaire?demande=inscription&erreurConnexion=" + erreurMsg);
            	 }
        	 }
         }
        }       
  
     // doGet(HttpServletRequest
         
public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
      { 
         doGet(request, response);
      }   

}
