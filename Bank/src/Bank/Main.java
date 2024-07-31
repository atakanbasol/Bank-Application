package Bank;

//import java.time.LocalDate;
import java.util.Scanner;

import Bank.BusinessAccess.CustomerManager;
import Bank.DataAccess.JdbcCustomerDal;
import Bank.Entity.Customer;

public class Main {

	public static void main(String[] args) {

		CustomerManager customerManager = new CustomerManager(new JdbcCustomerDal());
		
		Scanner scanner = new Scanner(System.in);
        
		System.out.println("Welcome to Bank");
		System.out.println("1-Sing In\n" + "2-Sing Up\n");
		
		int process = scanner.nextInt();
		scanner.nextLine();  // Consume newline (burada buffer'daki newline karakterini temizler)
		
		switch(process) {
			case 1:
				customerManager.handleSingIn(scanner);
                break;
				
			case 2:
				customerManager.handleSingUp(scanner);
                break;	
		}

	}

}
