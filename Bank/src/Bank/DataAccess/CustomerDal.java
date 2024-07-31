package Bank.DataAccess;

import Bank.Entity.*;
public interface CustomerDal {
	
	void getAll();
	
	boolean singUp(Customer customer);
	
	Customer singIn(String userIdentificationNumber, String userPassword);
	
	void updatePassword(Customer customer);
	
	void deleteCustomer(Customer customer);
	


}
