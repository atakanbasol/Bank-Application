package Bank.Entity;

import java.time.LocalDate;

public class Customer {
	
	private int id;
	private String firstname;
	private String lastname;
	private String email;
	private LocalDate dob;
	private String password;
	private String adress;
	private String gender;
	private String identificationNumber;
	private String job;
	

    // Constructor
    public Customer(int id, String firstname, String lastname, String email, LocalDate dob,
                    String password, String address, String gender, String identificationNumber, String job) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.dob = dob;
        this.password = password;
        this.adress = address;
        this.gender = gender;
        this.identificationNumber = identificationNumber;
        this.job = job;
    }

    // Constructor (without id)
    public Customer(String firstname, String lastname, String email, LocalDate dob,
                    String password, String address, String gender, String identificationNumber, String job) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.dob = dob;
        this.password = password;
        this.adress = address;
        this.gender = gender;
        this.identificationNumber = identificationNumber;
        this.job = job;
    }
    
    // Constructor password and identificationNumber
    public Customer(String identificationNumber, String password) {
        this.password = password;
        this.identificationNumber = identificationNumber;
    }
    
    // Constructor id and password
    public Customer(int id, String password) {
        this.id = id;
        this.password = password;
    }
    
    // Constructor only id
    public Customer(int id) {
        this.id = id;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getIdentificationNumber() {
		return identificationNumber;
	}

	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}
	
    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", password='" + password + '\'' +
                ", address='" + adress + '\'' +
                ", gender='" + gender + '\'' +
                ", identificationNumber='" + identificationNumber + '\'' +
                ", job='" + job + '\'' +
                '}';
    }
	

	
}
