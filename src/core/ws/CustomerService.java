package core.ws;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import CouponSystem.CouponSystem;
import CouponSystemExceptions.CouponSystemException;
import Facade.ClientType;
import Facade.CustomerFacade;
import JavaBeans.Coupon;
import businessDelegate.Helper;
import entity.Income;
import entity.IncomeType;

@Path("/CustomerService")
public class CustomerService {
	@Context
	private HttpServletRequest request;
	@Context
	private HttpServletResponse response;
	
	
	@Path("PurchaseCoupon")
	@POST
	public void purchaseCoupon(@FormParam("id")long id) 
	{
		HttpSession session = request.getSession(false);
		CustomerFacade cuf= (CustomerFacade) session.getAttribute("facade");
		try {
			Coupon coupon= new Coupon();
			coupon.setId(id);
			cuf.purchaseCoupon(coupon);
//			Helper helper = new Helper();
//			Income income= new Income();
//			income.setName(cuf.getCustomer().getCustName());
//			income.setDate(new Date());
//			income.setDescription(IncomeType.CUSTOMER_PURCHASE);
//			income.setAmount(coupon.getPrice());
//			helper.storeIncome(income);
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Path("ReadAllPurchasedCoupons")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Coupon> readAllCoupons() 
	{
		HttpSession session = request.getSession(false);
		CustomerFacade cuf= (CustomerFacade) session.getAttribute("facade");
		Collection<Coupon> couponsList = new ArrayList<>();
		try {
			couponsList = cuf.getAllPurchasedCoupon();
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
		return couponsList;
	}
	
	
	
	@Path("ReadAllPurchasedCouponsByType")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Coupon> readAllCouponsByType(@QueryParam("type") JavaBeans.Coupon.CouponType type) 
	{
		HttpSession session = request.getSession(false);
		CustomerFacade cuf= (CustomerFacade) session.getAttribute("facade");
		Collection<Coupon> couponsList = new ArrayList<>();
		try {
			couponsList = cuf.getAllPurchasedCouponByType(type);
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
		return couponsList;
	}
	
	
	@Path("ReadAllPurchasedCouponsByUntilPrice")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Coupon> readAllCouponsByUntilPrice(@QueryParam("price") double price) 
	{
		HttpSession session = request.getSession(false);
		CustomerFacade cuf= (CustomerFacade) session.getAttribute("facade");
		Collection<Coupon> couponsList = new ArrayList<>();
		try {
			couponsList = cuf.getAllPurchasedCouponByPrice(price);
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
		return couponsList;
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
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		
		}
	}
	
	
}
