package core.ws;


import java.io.IOException;
import java.sql.Date;
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
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import CouponSystem.CouponSystem;
import CouponSystemExceptions.CouponSystemException;
import Facade.ClientType;
import Facade.CompanyFacade;
import JavaBeans.Company;
import JavaBeans.Coupon;
import JavaBeans.CouponType;
import businessDelegate.Helper;
import entity.Income;
import entity.IncomeType;

@Path("/CompanyService")
public class CompanyService {
	@Context
	private HttpServletRequest request;
	@Context
	private HttpServletResponse response;
//	CouponSystem cs = CouponSystem.getInstance();
	//CompanyFacade cf =  (CompanyFacade) cs.login("dell", "1111", ClientType.COMPANY);
	
	@Path("CreateCoupon")
	@POST
	public void createCoupon(@FormParam("title")String title,
			@FormParam("start_date") Date start_date,
			@FormParam("end_date") Date end_date,
			@FormParam("amount") int amount,
			@FormParam("type") JavaBeans.Coupon.CouponType type,
			@FormParam("message") String message,
			@FormParam("price") double price,
			@FormParam("image") String image) 
	{
		HttpSession session = request.getSession(false);
		CompanyFacade cf=  (CompanyFacade) session.getAttribute("facade");
		Coupon coupon;
		try {
			coupon = new Coupon(title, start_date, end_date, amount, type, message, price, image);
			cf.createCoupon(coupon);
//			Helper helper = new Helper();
//			Income income = new Income();
//			income.setName(cf.getCompany().getCompName());
//			income.setDate(new java.util.Date());
//			income.setDescription(IncomeType.COMPANY_NEW_COUPON);
//			income.setAmount(100);
//			helper.storeIncome(income);
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
	}

	
	
	

	@Path("DeleteCoupon")
	@GET
	public void deleteCoupon(@QueryParam("id") long id)
	{
		HttpSession session = request.getSession(false);
		CompanyFacade cf=  (CompanyFacade) session.getAttribute("facade");
		try {
			Coupon coupon = cf.readCoupon(id);
			cf.deleteCoupon(coupon);
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Path("UpdateCoupon")
	@GET
	public void updateCoupon(@QueryParam("id") long id, @QueryParam("end_date") Date end_date, @QueryParam("price") double price)
	{
		HttpSession session = request.getSession(false);
		CompanyFacade cf=  (CompanyFacade) session.getAttribute("facade");
		try {
			Coupon coupon = cf.readCoupon(id);
			coupon.setEndDate(end_date);
			coupon.setPrice(price);
			cf.updateCoupon(coupon);
//			Helper helper = new Helper();
//			Income income = new Income();
//			income.setName(cf.getCompany().getCompName());
//			income.setDate(new java.util.Date());
//			income.setDescription(IncomeType.COMPANY_UPDATE_COUPON);
//			income.setAmount(10);
//			helper.storeIncome(income);
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Path("ReadCoupon")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Coupon readCoupon(@QueryParam("id") long id) 
	{
		HttpSession session = request.getSession(false);
		CompanyFacade cf=  (CompanyFacade) session.getAttribute("facade");
		Coupon coupon=new Coupon();
		
			try {
				coupon = cf.readCoupon(id);
			} catch (CouponSystemException e) {
				e.printStackTrace();
			}
		
		return coupon;
	}
	
	
	/*
	@Path("ViewIncomeByCompany")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
		
		public Collection<Income> viewIncomeByCompany() 
		{
			HttpSession session = request.getSession(false);
			CompanyFacade cf=  (CompanyFacade) session.getAttribute("facade");
			Helper helper = new Helper();
			try {
		
				return 	helper.viewIncomeByCompany(cf.getCompany().getCompName());
				} catch (Exception e) {
					e.printStackTrace();
				}
		
			return null;
		}
		
		
		*/
	//	HttpSession session = request.getSession(false);
	//	CompanyFacade cf=  (CompanyFacade) session.getAttribute("facade");
		
	
	
	@Path("ReadAllCoupons")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Coupon> readAllCoupons() 
	{
		HttpSession session = request.getSession(false);
		CompanyFacade cf=  (CompanyFacade) session.getAttribute("facade");
		Collection<Coupon> couponsList = new ArrayList<>();
		try {
			couponsList = cf.readAllCoupon();
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
		return couponsList;
	}
	
	
	
	@Path("ReadAllCouponsByType")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Coupon> readAllCouponsByType(@QueryParam("type") JavaBeans.Coupon.CouponType type) 
	{
		HttpSession session = request.getSession(false);
		CompanyFacade cf=  (CompanyFacade) session.getAttribute("facade");
		Collection<Coupon> couponsList = new ArrayList<>();
		try {
			couponsList = cf.readAllCouponByType(type);
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
		return couponsList;
	}
	
	
	@Path("ReadAllCouponsByUntilPrice")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Coupon> readAllCouponsByUntilPrice(@QueryParam("price") double price) 
	{
		HttpSession session = request.getSession(false);
		CompanyFacade cf=  (CompanyFacade) session.getAttribute("facade");
		Collection<Coupon> couponsList = new ArrayList<>();
		try {
			couponsList = cf.readAllCouponByUntilPrice(price);
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
		return couponsList;
	}
	
	
	@Path("ReadAllCouponsByUntilDate")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Coupon> readAllCouponsByUntilDate(@QueryParam("end_date") String end_date) 
	{
		HttpSession session = request.getSession(false);
		CompanyFacade cf=  (CompanyFacade) session.getAttribute("facade");
		Collection<Coupon> couponsList = new ArrayList<>();
		try {
			couponsList = cf.readAllCouponByUntilDate(end_date);
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

			} catch (IOException e) {
				e.printStackTrace();
			}
			
		
		}
	}
	
	
	
	
}
