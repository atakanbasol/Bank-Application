package Bank.DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import Bank.Entity.Customer;

public class JdbcCustomerDal implements CustomerDal{
	
    private DbHelper helper;
    private Connection connection;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private ResultSet resultSet2 = null;
    
    // Constructor
    public JdbcCustomerDal() {
        helper = new DbHelper();
        try {
            connection = helper.getConnection();
            System.out.println("Bağlantı oluşturuldu.");
        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        }
    }
    
    public void connectionClose() {
    	try {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        }
    	
    }

	@Override
	public void getAll() {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM customers");
            ArrayList<Customer> customerInfo = new ArrayList<Customer>();
            while (resultSet.next()) {
            	LocalDate dob = resultSet.getDate("dob").toLocalDate();
                customerInfo.add(new Customer(resultSet.getInt("id"), 
                							  resultSet.getString("firstname"),
                							  resultSet.getString("lastname"), 
                							  resultSet.getString("email"),
                							  dob,
                							  resultSet.getString("password"),
                							  resultSet.getString("adress"),
                							  resultSet.getString("gender"),
                							  resultSet.getString("identificationNumber"),
                							  resultSet.getString("job")));
            }
            for (Customer customer : customerInfo) {
                System.out.println(customer);
            }

        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        }
		
	}

    @Override
    public boolean singUp(Customer customer) {
        PreparedStatement preparedStatement = null;

        try {
            String sql = "INSERT INTO customers (firstname, lastname, email, dob, password, adress, gender, identificationNumber, job) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, customer.getFirstname());
            preparedStatement.setString(2, customer.getLastname());
            preparedStatement.setString(3, customer.getEmail());
            preparedStatement.setDate(4, java.sql.Date.valueOf(customer.getDob()));
            preparedStatement.setString(5, customer.getPassword());
            preparedStatement.setString(6, customer.getAdress());
            preparedStatement.setString(7, customer.getGender());
            preparedStatement.setString(8, customer.getIdentificationNumber());
            preparedStatement.setString(9, customer.getJob());
            int result = preparedStatement.executeUpdate(); // SQL komutunu çalıştırır ve etkilenen satır sayısını döner
            System.out.println("Kayıt eklendi, etkilenen satır sayısı: " + result);
            return result == 1;
        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
            return false;
        }
    }
	

    @Override
    public Customer singIn(String userIdentificationNumber, String userPassword) {
        Customer customer = null;
        String sql = "SELECT * FROM customers WHERE identificationNumber = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userIdentificationNumber);
            statement.setString(2, userPassword);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                customer = new Customer(
                    resultSet.getInt("id"),
                    resultSet.getString("firstname"),
                    resultSet.getString("lastname"),
                    resultSet.getString("email"),
                    resultSet.getDate("dob").toLocalDate(),
                    resultSet.getString("password"),
                    resultSet.getString("adress"),
                    resultSet.getString("gender"),
                    resultSet.getString("identificationNumber"),
                    resultSet.getString("job")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }
	
	//Şifremi unuttum butonu
	@Override
	public void updatePassword(Customer customer) {
        PreparedStatement preparedStatement = null;

        try {
            String sql = "update customers set password = ? where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,customer.getPassword());
            preparedStatement.setInt(2,customer.getId());
            int result = preparedStatement.executeUpdate(); //SQL komutunu çalıştırı ve etkilenen satır sayısını döner
            System.out.println("Kayıt güncellendi, etkilenen satır sayısı: " + result);
        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        } 
		
	}

	@Override
	public void deleteCustomer(Customer customer) {
		PreparedStatement preparedStatement = null;

        try {
            String sql = "delete from customers where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,customer.getId());
            int result = preparedStatement.executeUpdate(); //SQL komutunu çalıştırı ve etkilenen satır sayısını döner
            if(result>0) {
            	System.out.println("Kayıt silindi, etkilenen satır sayısı: " + result);
            }
            else {
            	System.out.println(customer.getId()+ " " + "Numaralı müşteri kaydı bulunamadı.");
            }
            
        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        }
		
	}
	





}
