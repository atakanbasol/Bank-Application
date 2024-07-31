package Bank.BusinessAccess;

import Bank.Entity.Customer;

public interface CustomerService {

	void getAll();
	
	boolean singUp(Customer customer);
	
	Customer singIn(String userIdentificationNumber, String userPassword);
	
	void updatePassword(Customer customer);
	
	void deleteCustomer(Customer customer);
}
