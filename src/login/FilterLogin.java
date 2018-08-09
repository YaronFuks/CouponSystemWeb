package login;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;

import Facade.ClientFacade;

public class FilterLogin implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		

		HttpServletRequest httpServletRequest =(HttpServletRequest) request;
		
		if (httpServletRequest.getSession(false) == null ) {
			// redirect
			System.out.println("login-Filter: no Session, user will be redirected.");
		//	RequestDispatcher rd = request.getRequestDispatcher("/index.html");
			//rd.forward(request, response);
			request.getRequestDispatcher("/index.html");
			
			
		
		}else {
			// Getting the reference to the session
			HttpSession session = httpServletRequest.getSession(false);
			
			//Receiving the facade by session.getAttribute
			ClientFacade facade = (ClientFacade) session.getAttribute("facade");
			
			 //checking if the facade exists:
			if (facade == null )
			{
						

				System.out.println("Login Filter: No Facade");
				request.getRequestDispatcher("/index.html");

			//	RequestDispatcher rd = request.getRequestDispatcher("/index.html");
				//rd.forward(request, response);
				
			}else 
			{
				System.out.println("Request Forward");
				chain.doFilter(request, response);
			}
			
		}
		
	
	}

	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}
	
	
	@Override
	public void destroy() {
		
	}

}
