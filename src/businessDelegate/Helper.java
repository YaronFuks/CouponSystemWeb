package businessDelegate;

import java.util.Collection;
import java.util.Hashtable;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;


import couponSystem.IncomeService;
import entity.Income;




public class Helper {
	private IncomeService stub;
{
		InitialContext ctx=getInitialContext();
		Object ref=null;
		try {
			ref = ctx.lookup("IncomeServiceEJB/remote");
		} catch (NamingException e) {
			System.out.println("Lookup Failed");
			e.printStackTrace();
		}
		 stub=(IncomeService)PortableRemoteObject.narrow(ref, IncomeService.class);
		
			
		}
		
public void storeIncome(Income income) {
	stub.storeIncome(income);
		
	}

public Collection<Income> viewAllIncome(){
	return stub.viewAllIncome();
	
}


public Collection<Income> viewIncomeByCompany(String companyName){
	return stub.viewIncomeByCompany(companyName);
	
}

public Collection<Income> viewIncomeByCustomer(String customerName){
	return stub.viewIncomeByCustomer(customerName);
	
}

	
	public static InitialContext getInitialContext(){
		Hashtable<String,String> h=new Hashtable<String,String>();
		h.put("java.naming.factory.initial","org.jnp.interfaces.NamingContextFactory");
		h.put("java.naming.provider.url","localhost");
		try {
			return new InitialContext(h);
		} catch (NamingException e) {
			System.out.println("Cannot generate InitialContext");
			e.printStackTrace();
		}
		return null;
	}

}
