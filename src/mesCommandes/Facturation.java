package mesCommandes;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Facturation
 */
@WebServlet("/Facturation")
public class Facturation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection connexion=null;
    Statement stmt=null;
    PreparedStatement pstmt=null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Facturation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom = null;
		PrintWriter out = response.getWriter();
		ArrayList<String>  leCaddy = null;
   	 	String repertoire = request.getContextPath();
		 HttpSession session = request.getSession();
		 nom = session.getAttribute("name").toString();
		 leCaddy = Magasin.lesCaddy.get(nom);
		OuvreBase();
        response.setContentType("text/html");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>  votre commande </title>");
        out.println("<meta http-equiv='Content-Type' content='text/html; charset=iso-8859-1' >");
        out.println("</head>");
     
        out.println("<body bgcolor=\"white\">");
        out.println("<h3>" + "voici " + nom.toUpperCase()  + "  Voici  l'ensemble de tes commandes  enregistr�es " + "</h3>");
        MontreCommandeBase(nom, out, request.getContextPath());
        out.println("</body>");
        out.println("</html>");
	}
	protected void MontreCommandeBase(String nom, PrintWriter out, String repertoire) {
	       ResultSet rset = null;
	       ResultSet rs = null;
	       int totalPrice = 0;
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
	               System.out.println("----------------------------------");
	               System.out.println(nomarticle);
	               leDisque = Stock.trouveDisque(nomarticle);
	               out.println( "<tr> <td>" + leDisque.getTitre() + "  ,  " + leDisque.getNom() + " ,  "  + leDisque.getPrix() + " Euros  ,  Ann�e :" + leDisque.getAnnee()  +"</td>");
		   	       out.println("<td> <IMG SRC= '" + repertoire + "/images/" + leDisque.getImage() +"'  BORDER=0> </A><br> </td> ");
		   	       totalPrice += leDisque.getPrix();
	           }
	    	   out.println("</tr> </table>");
	    	   out.println("<p> The total price: " + Integer.toString(totalPrice) + "</p>");
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
