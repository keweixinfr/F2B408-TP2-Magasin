package mesCommandes;
import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import java.sql.*;

public class EnregistrerCommande extends HttpServlet {
 
	private static final long serialVersionUID = 1L;
	
	Connection connexion=null;
    Statement stmt=null;
    PreparedStatement pstmt=null;
 
     public void doGet(HttpServletRequest request,  HttpServletResponse response)
                        throws IOException, ServletException
    {  
    	 String nom = null;
    	 ArrayList<String>  leCaddy = null;
    	 String repertoire = request.getContextPath();
 		 HttpSession session = request.getSession();
 		 nom = session.getAttribute("name").toString();
 		 leCaddy = Magasin.lesCaddy.get(nom);
	   
 		 //  ******************************************************
          OuvreBase();
          AjouteNomBase(nom);
          response.setContentType("text/html");
          PrintWriter out = response.getWriter();
          out.println("<html>");
          out.println("<head>");
          out.println("<title>  votre commande </title>");
          out.println("<meta http-equiv='Content-Type' content='text/html; charset=iso-8859-1' >");
          out.println("</head>");
       
          out.println("<body bgcolor=\"white\">");
          out.println("<a HREF=afficheDisques> Vous pouvez commandez un autre disque </a><br> ");
		  out.println("<A HREF=facturer> Fin de la commande et demande de la facture  de   " + nom.toUpperCase()+" </A><br> ");
          out.println("<h3>" + " Disques rajout�s � la commande de  " + nom.toUpperCase()  + "</h3>");
	      //  ******************************************************       
	      //   afficher le contenu du caddie
	      //  ******************************************************   
          
         //  **************************************************
          AjouteCaddyBase(nom,leCaddy);
          out.println("<h3>" + "et voici " + nom.toUpperCase()  + "  Voici  l'ensemble de tes commandes  enregistr�es " + "</h3>");
          MontreCommandeBase(nom, out, request.getContextPath());
          out.println("</body>");
          out.println("</html>");
       }	
    
     protected void OuvreBase() {
       try {
          Class.forName("org.gjt.mm.mysql.Driver").newInstance(); 
          connexion = DriverManager.getConnection(  "jdbc:mysql://localhost/magasin","root","");
          connexion.setAutoCommit(true);
          stmt = connexion.createStatement();
       }
           catch (Exception E) {         
             log(" -------- probl�me  " + E.getClass().getName() );
             E.printStackTrace();
          }	
    }
 
     protected void fermeBase() {
       try {
          stmt.close();        
          connexion.close();         
       }
           catch (Exception E) {         
             log(" -------- probl�me  " + E.getClass().getName() );
             E.printStackTrace();
          }	
    }
     protected void AjouteNomBase(String nom) {
       try {
          ResultSet rset = null;
          pstmt= connexion.prepareStatement("select id from client where nom=?");
          pstmt.setString(1,nom);
          rset=pstmt.executeQuery();
          if (!rset.next())   
             stmt.executeUpdate("INSERT INTO client (nom)VALUES  ('" +  nom + "' )" );
       }
       
           catch (Exception E) {
             log(" - probeme  " + E.getClass().getName() );
             E.printStackTrace();
          }	
    }

     protected void AjouteCaddyBase(String nom, ArrayList<String> lesdisques ) {
       ResultSet rset = null;
       int cle =0;
//       String insertdisqueBD = "INSERT INTO commande (nomarticle,client)VALUES (?,?)";
       String insertdisque = "INSERT INTO  commande (nomarticle, client) " + 
       		" SELECT ?, id as cid " + 
       		" FROM client WHERE nom = ?";
       
       try {
    	   pstmt= connexion.prepareStatement(insertdisque);
    	   Iterator <String> iter = lesdisques.iterator();
    	   	while (iter.hasNext()){
    	   	 pstmt.setString(1, (String)iter.next());
    	   	 pstmt.setString(2, nom);
    	   	 pstmt.executeUpdate();
    	    }

    	//  ********************************************************       
        //   ajoutez le contenu du caddie dans la  base de donn�es. � table commande � 
       	//   utilisez une PreparedStatement JDBC de pr�f�rence
        //  ********************************************************  

    	   
     	  
         //  ******************************************************
     	  }
         
       catch (Exception E) {        
             log(" - probl�me  ajoute " + E.getClass().getName() );
             E.printStackTrace();
          }	
    }
 
     protected void MontreCommandeBase(String nom, PrintWriter out, String repertoire) {
       ResultSet rset = null;
       ResultSet rs = null;
       Disque leDisque;
       int cle =0;
       String selectCommande = "SELECT commande.nomarticle FROM commande JOIN client on commande.client=client.id WHERE client.nom =?" ;
       try {
    	   pstmt= connexion.prepareStatement(selectCommande);
    	   pstmt.setString(1, nom);
    	   rset=pstmt.executeQuery();
    	   System.out.println(rset);
    	   out.println("<table border=1>");
    	   while (rset.next()) {
               String nomarticle = rset.getString("nomarticle");
               System.out.println(nomarticle);
               leDisque = Stock.trouveDisque(nomarticle);
               out.println( "<tr> <td>" + leDisque.getTitre() + "  ,  " + leDisque.getNom() + " ,  "  + leDisque.getPrix() + " Euros  ,  Ann�e :" + leDisque.getAnnee()  +"</td>");
	   	       out.println("<td> <IMG SRC= '" + repertoire + "/images/" + leDisque.getImage() +"'  BORDER=0> </A><br> </td> ");    	 
           }
    	   out.println("</tr> </table>");
       //  *********************************************************      
       //   affichez les disques que client a command�  dans la  base de donn�es. � table commande � 
       // vous pouvez utiliser "afficherDisquesDansBase" avec 3 parm�tre :
       //      -  une instance de "Resulset"  r�sultat de la recherche des disques du client courant dans la base de donn�es
   	   //      - le � PrintWriter � pour pouvoir rajouter ces disques dans la page de la r�ponse HTML,
   	   //      - et le repertoire courant de votre application  "request.getContextPath()"
       //  **********************************************************  
    	   
       }           
           catch (Exception E) {   
	           out.println("erreur base");         
               log(" - probeme ajoute " + E.getClass().getName() );
               E.printStackTrace();
  }	  }

 
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
       doGet(request, response);
    }
 

}
