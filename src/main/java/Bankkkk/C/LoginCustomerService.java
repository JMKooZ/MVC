package main.java.Bank.C;

import main.java.Bank.M.Customer;
import main.java.Bank.M.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginCustomerService {
    CustomerDao customerDao;
@Autowired
    public LoginCustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public Customer loginView(String id) {
        Customer customer = this.customerDao.selectCustomer(id);
        return customer;
    }
}
