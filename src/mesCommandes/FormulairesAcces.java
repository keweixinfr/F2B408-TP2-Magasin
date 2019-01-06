package mesCommandes;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/servlet/formulaire")
public class FormulairesAcces extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	// au prochain TP nous utiliserons des formulaires plus �labor�s
	private void infoConnexionetInscription (PrintWriter out, String nom)
	{
	    out.println("nom");
	    out.println("<input type='text' size='20' name='nom' value='" +((nom!=null)?nom:"") + "'  >  <br> ");
	    out.println("mot de passe");
	    out.println("<input type='password' size='20' name='motdepasse'> <br>");
	}

	private void infoInscription (PrintWriter out)
	{
	    out.println("email");
	    out.println("<input type='text' size='20' name='mail'> <br>");
	    out.println("telephone");
	    out.println("<input type='text' size='20' name='telephone'> <br>");
	}
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         String demande, nomRecu, erreurInscription, erreurConnexion, inscriptionFaite;


     //  ********************************************************************************************        
     //  initialisation des diff rents param tres possibles
     //  ********************************************************************************************    
         String paramName 		= "demande";
         String requestMode 	= request.getParameter(paramName);
         String inscriptionFait = request.getParameter("inscriptionFait");
         String erreur			= request.getParameter("erreurConnexion");

     //  ********************************************************************************************           
         response.setContentType("text/html");
         PrintWriter out = response.getWriter();
         
        // Les 2 m�thodes  infoInscription et infoConnexionetInscription sont � votre disposition.
         
        //  ********************************************************************************************            		 
        //   cas 1   inscription       param�tre demande = "inscription"        
        //       Si le param�tre "erreurInscription" est pr�sent, informez que les informations transmises ne sont pas acceptables.
      	//   Demandez des informations (nom, mot de passe, email, t�l�phone) par un formulaire
      	//   et rappel de la  servlet   "VerificationCompte" avec ces informations en param�tre pour enregistrer ces valeurs
        //   le param�tre inscrire est envoy� avec comme valeur inscrire (bouton submit)
        //  ********************************************************************************************       
         if(requestMode !=null && requestMode.equals("inscription")) {
        	 out.println("<HTML><HEAD><TITLE>"
        			 + "Inscription"
        			 + "</TITLE></HEAD><BODY>");
        	 if(erreur != null && !erreur.isEmpty())  {
        		 out.println("<h3> Erreur lors de la connexion</h3>");
        		 out.println("<p>"+erreur+"</p>");
        	 }
    		 out.println("<form action=\"verifCompte\" method=\"GET\">\r\n" + 
    			 "  <p>Nom: <input type=\"text\" name=\"nom\" /></p>\r\n" + 
    			 "  <p>Mot de passe: <input type=\"password\" name=\"mdp\" /></p>\r\n" +
    			 "  <p>Email: <input type=\"text\" name=\"email\" /></p>\r\n" + 
    			 "  <p>Telephone: <input type=\"text\" name=\"telephone\" /></p>\r\n" +
    			 "  <input type=hidden name=mode value=\"inscription\"/>" +
    			 "  <input type=\"submit\" value=\"Inscrire\" />\r\n" + 
    			 "</form>");
         }else if(requestMode !=null && requestMode.equals("connexion")) {
        	 out.println("<HTML><HEAD><TITLE>"
        			 + "Connexion"
        			 + "</TITLE></HEAD><BODY>"
        			 +"<form action=\"verifCompte\" method=\"GET\">\r\n" + 
        			 "  <p>Nom: <input type=\"text\" name=\"nom\" /></p>\r\n" + 
        			 "  <p>Mot de passe: <input type=\"password\" name=\"mdp\" /></p>\r\n" +
        			 "  <input type=hidden name=mode value=\"connexion\"/>" +
        			 "  <input type=\"submit\" value=\"Connecter\" />\r\n" + 
        			 "</form>");
         }else if(inscriptionFait != null && inscriptionFait.equals("True")) {
        	 String nom = request.getParameter("nom");
        	 out.println("<HTML><HEAD><TITLE>"
        			 + "Connexion"
        			 + "</TITLE></HEAD><BODY>"
        			 + "<p>Bienvenue "+ nom + "</p></BODY></HTML>");
         }
//         out.println("<HTML><HEAD><TITLE>"
//    			 + "Connexion"
//    			 + "</TITLE></HEAD><BODY>"
//    			 +"<p> request mode: " + requestMode + "</p>" 
//    			 +"<p> inscriptionFait: " + inscriptionFait +"</p>"
//    			 + "<p>Bienvenue </p></BODY></HTML>");
         out.close();
         
         //  ********************************************************************************************            		 
         //   cas 2   connection     param�tre demande = "connexion"        
         //       Si le param�tre "erreurConnexion" est pr�sent, informez que les informations transmises ne sont pas acceptables,
         //     et sortir la valeur de erreurConnexion
         //       Si le param�tre inscriptionFaite est pr�sent, il vient de s'inscrire, on rajoute un message comme quoi 
         //    l'inscription  s'est bien r�alis�e et dans le formulaire on initialise le nom avec le nom re�u en param�tre.
         //        
         //   Demandez des informations (nom, mot de passe) par un formulaire
         //   et rappel de la  servlet   VerificationCompte avec ces informations en param�tres pour v�rifier ces valeurs
         //   le param�tre "connecter" est envoy� avec comme valeur "connecter" (bouton submit)
         //  ********************************************************************************************         

   }  // doGet(HttpServletRequest
         
public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
      { 
         doGet(request, response);
      }  














}
