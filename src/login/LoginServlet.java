package login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import CouponSystem.CouponSystem;
import CouponSystemExceptions.CouponSystemException;
import Facade.ClientFacade;
import Facade.ClientType;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	HttpSession session = request.getSession(false);
	// check the session state
	if (session != null)
	{
		session.invalidate();
	}
	
	// check the CouponSystem login
	String name = request.getParameter("userName");
	String password = request.getParameter("password");
	ClientType type = ClientType.valueOf(request.getParameter("type"));
	

	try {
    ClientFacade facade;
		facade = CouponSystem.getInstance().login(name, password, type);
	System.out.println(facade);
	session = request.getSession(true);
	session.setMaxInactiveInterval(600);
	session.setAttribute("facade", facade);

	if (facade == null)
	{
		 response.sendRedirect("LoginError.html");
	}else {
		
		
		switch (type) {
		case ADMIN:
			
			response.sendRedirect("admin.html");	
			break;
		case COMPANY:
			response.sendRedirect("company.html");		
			break;
		case CUSTOMER:
			response.sendRedirect("customer.html");		
			break;
			
			
		default:
			break;
		}
		
	
   
    }
	}catch (CouponSystemException e) {
		response.sendRedirect("LoginError.html");		
	}
	}
}


	
	
	
	
	



