package mesCommandes;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.*;
import javax.servlet.http.*;


public class FiltreSortie implements Filter {


	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		  String nom = null;
		  HttpServletRequest hrequest = (HttpServletRequest) request;
		  HttpSession session = hrequest.getSession(); 
  
		  //************************************************************
		  // ce filtre doit s'executer apr�s la servlet 
		  // il vide le caddy du client en cours
		  //************************************************************		  
		  chain.doFilter(request, response); 
		  nom = (String)session.getAttribute("name");
		  ArrayList<String> result = Magasin.lesCaddy.remove(nom);
		  if(result!=null) {
			  System.out.println("Successfully removed");
			  Iterator <String> iter = result.iterator();
				while (iter.hasNext()) {
					System.out.println(iter.next());
				}
		  }else {
			  System.out.println("Error");
		  }

		}
		  //************************************************************
	}



