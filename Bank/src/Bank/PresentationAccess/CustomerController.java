package Bank.PresentationAccess;

import Bank.BusinessAccess.CustomerManager;
import Bank.DataAccess.JdbcCustomerDal;
import Bank.Entity.Customer;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import java.io.IOException;

public class CustomerController extends HttpServlet {

    private CustomerManager customerManager;
    private Gson gson = new Gson();

    @Override
    public void init() throws ServletException {
        this.customerManager = new CustomerManager(new JdbcCustomerDal());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        if ("/signup".equals(path)) {
            handleSignUp(request, response);
        } else if ("/signin".equals(path)) {
            handleSignIn(request, response);
        }
    }

    private void handleSignUp(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Customer customer = gson.fromJson(request.getReader(), Customer.class);
        boolean isAdded = customerManager.singUp(customer);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(gson.toJson(new Response(isAdded)));
    }

    private void handleSignIn(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Customer customer = gson.fromJson(request.getReader(), Customer.class);
        Customer authenticatedCustomer = customerManager.singIn(customer.getIdentificationNumber(), customer.getPassword());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(gson.toJson(new Response(authenticatedCustomer != null)));
    }

    class Response {
        boolean success;
        Response(boolean success) {
            this.success = success;
        }
    }
}
