package core.ws;



import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import CouponSystem.CouponSystem;
import CouponSystemExceptions.CouponSystemException;
import Facade.AdminFacade;
import Facade.ClientType;
import Facade.CompanyFacade;
import JavaBeans.Company;
import JavaBeans.Customer;
import businessDelegate.Helper;
import entity.Income;


@Path("/AdminService")
public class AdminService {

//	CouponSystem cs = CouponSystem.getInstance();
//	AdminFacade af = (AdminFacade) cs.login("admin", "1234", ClientType.ADMIN);
			//login("admin", "1234", ClientType.ADMIN);
@Context
private HttpServletRequest request;
@Context
private HttpServletResponse response;
	

	@Path("CreateCompany")
	@POST
	public void createCompany(@FormParam("compName")String compName,@FormParam("password") String password, @FormParam("email") String email) 
	{
		Company company;
		HttpSession session = request.getSession(false);
		AdminFacade af= (AdminFacade) session.getAttribute("facade");
		try {
			company = new Company(compName, password, email);
			af.createCompany(company);
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
	}

	
	@Path("CreateCustomer")
	@POST
	public void createCustomer(@FormParam("custName") String custName, @FormParam("password") String password)
	{
		HttpSession session = request.getSession(false);
		AdminFacade af= (AdminFacade) session.getAttribute("facade");
		Customer customer;
		customer = new Customer(custName, password);
		try {
			af.createCustomer(customer);
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Path("DeleteCompany")
	@GET
	public void deleteCompany(@QueryParam("id") long id)
	{
		HttpSession session = request.getSession(false);
		AdminFacade af= (AdminFacade) session.getAttribute("facade");
		try {
			Company company = af.readCompany(id);
			af.deleteCompany(company);
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
	}


	@Path("DeleteCustomer")
	@GET
	public void deleteCustomer(@QueryParam("id") long id)
	{
		HttpSession session = request.getSession(false);
		AdminFacade af= (AdminFacade) session.getAttribute("facade");
		try {
			Customer customer = af.readCustomer(id);
			af.deleteCustomer(customer);
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
	}
	
	@Path("UpdateCompany")
	@GET
	public void updateCompany(@QueryParam("id") long id, @QueryParam("password") String password, @QueryParam("email") String email)
	{
		HttpSession session = request.getSession(false);
		AdminFacade af= (AdminFacade) session.getAttribute("facade");
		try {
			Company company = af.readCompany(id);
			company.setPassword(password);
			company.setEmail(email);
			af.updateCompany(company);
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
	}

	
	@Path("UpdateCustomer")
	@GET
	public void updateCustomer(@QueryParam("id") long id, @QueryParam("password") String password)
	{
		HttpSession session = request.getSession(false);
		AdminFacade af= (AdminFacade) session.getAttribute("facade");
		try {
			Customer customer = af.readCustomer(id);
			customer.setPassword(password);
			af.updateCustomer(customer);
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
	}

	
	
	@Path("ReadCompany")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Company readCompany(@QueryParam("id") long id) 
	{
		HttpSession session = request.getSession(false);
		AdminFacade af= (AdminFacade) session.getAttribute("facade");
		Company company=new Company();
		
			try {
				company = af.readCompany(id);
			} catch (CouponSystemException e) {
				e.printStackTrace();
			}
		
		return company;
	}


	@Path("ReadCustomer")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Customer readCustomer(@QueryParam("id") long id)
	{
		HttpSession session = request.getSession(false);
		AdminFacade af= (AdminFacade) session.getAttribute("facade");
		Customer customer=new Customer();
		try {
			customer = af.readCustomer(id);
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
		return customer;
	}
	
	@Path("ReadAllCompanies")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Company> readAllCompany() 
	{
		HttpSession session = request.getSession(false);
		AdminFacade af= (AdminFacade) session.getAttribute("facade");
		Collection<Company> companiesList = new ArrayList<>();
		try {
			companiesList = af.readAllCompany();
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
		return companiesList;
	}




@Path("ReadAllCustomers")
@GET
@Produces(MediaType.APPLICATION_JSON)
public Collection<Customer> readAllCustomer()
{
	HttpSession session = request.getSession(false);
	AdminFacade af= (AdminFacade) session.getAttribute("facade");
	Collection<Customer> customersList = new ArrayList<>();
	try {
	
	customersList = af.readAllCustomer();
	} catch (CouponSystemException e) {
e.printStackTrace();
	}
	return customersList;
}

@Path("LogOut")
@GET
public void logOut() throws ServletException
{
	HttpSession session = request.getSession(false);
	if (session != null) {
		session.invalidate();
		
		try {
			response.sendRedirect("../../../CouponSystem_Phase2/index.html");

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	
	}
}



@Path("ViewIncomeByCompany")
@GET
@Produces(MediaType.APPLICATION_JSON)
	
	public Collection<Income> viewIncomeByCompany(@QueryParam("compName") String compName) 
	{
		HttpSession session = request.getSession(false);
		AdminFacade af= (AdminFacade) session.getAttribute("facade");
		Helper helper = new Helper();
		try {
	
			return 	helper.viewIncomeByCompany(compName);
			} catch (Exception e) {
				e.printStackTrace();
			}
	
		return null;
	}



@Path("ViewIncomeByCustomer")
@GET
@Produces(MediaType.APPLICATION_JSON)
	
	public Collection<Income> viewIncomeByCustomer(@QueryParam("custName") String custName) 
	{
		HttpSession session = request.getSession(false);
		AdminFacade af= (AdminFacade) session.getAttribute("facade");
		Helper helper = new Helper();
		try {
	
			return 	helper.viewIncomeByCustomer(custName);
			} catch (Exception e) {
				e.printStackTrace();
			}
	
		return null;
	}


@Path("ViewAllIncome")
@GET
@Produces(MediaType.APPLICATION_JSON)
	
	public Collection<Income> viewAllIncome() 
	{
		HttpSession session = request.getSession(false);
		AdminFacade af= (AdminFacade) session.getAttribute("facade");
		Helper helper = new Helper();
		try {
	
			return 	helper.viewAllIncome();
			} catch (Exception e) {
				e.printStackTrace();
			}
	
		return null;
	}

}


