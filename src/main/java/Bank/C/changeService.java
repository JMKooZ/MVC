package main.java.Bank.C;

import main.java.Bank.M.Customer;
import main.java.Bank.M.CustomerDao;

public class changeService {
    CustomerDao customerDao;
    public changeService(CustomerDao customerDao){
        this.customerDao = customerDao;
    }
    public void change(String id, String newPw){
        Customer c = customerDao.selectCustomer(id);
        Customer changeCustomer = new Customer(id,newPw,c.getName(),c.getBirth(),c.getMoney());
        customerDao.change(id,changeCustomer);
    }
}
