package main.java.app.Bank.C;

import main.java.app.Bank.M.Customer;
import main.java.app.Bank.M.CustomerDao;

public class LoginCustomerService {
    CustomerDao customerDao;

    public LoginCustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public Customer loginView(String id) {
        Customer customer = this.customerDao.selectCustomer(id);
        return customer;
    }
}
