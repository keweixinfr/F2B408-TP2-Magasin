package mesCommandes;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class CommanderUnDisque extends HttpServlet {

 
	private static final long serialVersionUID = 1L;


	public void doGet(HttpServletRequest request,  HttpServletResponse response) 
        throws IOException, ServletException
{  
  String nom = null;
  String refer = request.getParameter("code");
  String repertoire = request.getContextPath();
  HttpSession session = request.getSession();
  nom = session.getAttribute("name").toString();
  ArrayList<String> leCaddie = Magasin.lesCaddy.get(nom);
  if(leCaddie==null) {
	  System.out.println("Commander un disque: creat caddy");
 	  leCaddie = new ArrayList<String>();
  }
  leCaddie.add(refer);
  Magasin.lesCaddy.put(nom, leCaddie);
    //  *********************************************************        
	//   Si la personne dont le nom est dans la session, ne poss�de pas de caddie , 
    //                       son caddie est cr�� dans l�ensemble des caddies, "Magasin.lesCaddy"
  	//   C�est une nouvelle ArrayList qui est rajout�e dans la TreeMap "lesCaddy"  de la classe � Magasin �, 
    //         avec   comme cl� le nom.
	// 
	//  **********************************************************                 
  	
   //  **********************************************************        
   //   Si le param�tre � ordre � est pr�sent est a comme valeur � ajouter �,
   //  la r�f�rence du disque pass�e en param�tre est rajout�e dans le panier (ArrayList<String>).
   // 
   //  ***********************************************************

//**************************************************************** 
 response.setContentType("text/html");
 PrintWriter out = response.getWriter();
 out.println("<html>");
 out.println("<head>");
 out.println("<title>  votre commande </title>");
 out.println("<meta http-equiv='Content-Type' content='text/html; charset=iso-8859-1' >");
 out.println("</head>");
 out.println("<body bgcolor=\"white\">");
 out.println("<h3>" + "Bonjour  "+ nom + "  voici  votre commande" + "</h3>");
 
 	Magasin.afficherContenuCaddy(leCaddie, out, repertoire);
    //  ************************************************************     
	//   affichage du contenu du caddie par la m�thode afficherContenuCaddy de � Magasin � avec trois param�tres : 
	//    - le caddie 
	//    - le � PrintWriter � pour pouvoir rajouter ces disques dans la r�ponse HTML,
	//    - le r�pertoire courant de votre application  "request.getContextPath()"
	//  *************************************************************                 


 
	//  *************************************************************
 out.println(" </table>");
 out.println("<A HREF=achat> Vous pouvez commandez un autre disque </A><br> ");
 out.println("<A HREF=enregistre> Vous pouvez enregistrer votre commande </A><br> ");
 out.println("<A HREF=facturer> Fin de la commande et demande de la facture </A><br> ");
 out.println("</body>");
 out.println("</html>");
}	



public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
{
     doGet(request, response);
}

}
