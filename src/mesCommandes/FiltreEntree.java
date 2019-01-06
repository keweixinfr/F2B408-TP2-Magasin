package mesCommandes;
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class FiltreEntree implements Filter {
	private FilterConfig filterConfig = null;

	public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
	}

	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		String nom, motPasse = null;
		HttpServletRequest hrequest = (HttpServletRequest) request;
		HttpServletResponse hresponse = (HttpServletResponse) response;
		Cookie[] cookies = hrequest.getCookies();
		HttpSession session = hrequest.getSession(); 
	
		//  ********************************************************************************************        
		//   s’il n’existe pas un cookie dont le nom est celui dans la variable « nom » de session
		//         vous pouvez utilisez la méthode «  rechercheValeurCookies » de la classe Util.java
		//   et qu’il n’existe pas  la variable de session « leStock » : appel de la servlet "FormulairesAcces" pour s'inscrire 
		//   Autrement on continue (chain.doFilter).
		//  ********************************************************************************************                   

 	
 	
	//  ********************************************************************************************	
		  chain.doFilter(request, response); 
	  
	 }

	public void destroy() {
		this.filterConfig = null;
	}

}
