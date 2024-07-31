package Bank.BusinessAccess;

import Bank.Entity.Customer;

import java.time.LocalDate;
import java.util.Scanner;

import Bank.DataAccess.CustomerDal;

public class CustomerManager implements CustomerService{
	
	private CustomerDal customerDal;

	public CustomerManager(CustomerDal customerDal) {
		this.customerDal = customerDal;
	}

	@Override
	public void getAll() {
		this.customerDal.getAll();		
	}

	@Override
	public boolean singUp(Customer customer) {
		return this.customerDal.singUp(customer);		
	}

    @Override
    public Customer singIn(String userIdentificationNumber, String userPassword) {
        return this.customerDal.singIn(userIdentificationNumber, userPassword);
    }

	@Override
	public void updatePassword(Customer customer) {
		this.customerDal.updatePassword(customer);	
	}

	@Override
	public void deleteCustomer(Customer customer) {
		this.customerDal.deleteCustomer(customer);	
	}

	
	public void handleSingIn(Scanner scanner) {
        String userIdentificationNumber;
        String userPassword;
        while(true) {
            System.out.println("TC'nizi girin : ");
            userIdentificationNumber = scanner.nextLine();
            
            if(userIdentificationNumber.length() == 11) {
                System.out.println("Şifrenizi girin : ");
                userPassword = scanner.nextLine();
                break;
            } else {
                System.out.println("Hatalı giriş yaptınız tekrar deneyiniz.");
            }
        }
        
        Customer customer = singIn(userIdentificationNumber, userPassword);
        if (customer != null) {
            System.out.println("Hoşgeldin, " + customer.getFirstname() + " " + customer.getLastname());
        } else {
            System.out.println("Geçersiz ID yada password.");
        }
    }
	
	
    public void handleSingUp(Scanner scanner) {
        System.out.println("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.println("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.println("Enter email: ");
        String email = scanner.nextLine();
        System.out.println("Enter date of birth (yyyy-mm-dd): ");
        LocalDate dateOfBirth = LocalDate.parse(scanner.nextLine());
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        System.out.println("Enter address: ");
        String address = scanner.nextLine();
        System.out.println("Enter gender: ");
        String gender = scanner.nextLine();
        System.out.println("Enter identification number: ");
        String identificationNumber = scanner.nextLine();
        System.out.println("Enter job: ");
        String job = scanner.nextLine();

        Customer newCustomer = new Customer(firstName, lastName, email, dateOfBirth, password, address, gender, identificationNumber, job);
        boolean isAdded = singUp(newCustomer);
        if (isAdded) {
            System.out.println("Müşteri kaydedildi.");
        } else {
            System.out.println("Müşteri kaydedilemedi.");
        }
    }
}
