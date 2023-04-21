package main.java.Bank.C;

import main.java.Bank.M.Customer;
import main.java.Bank.M.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class changeService {
    CustomerDao customerDao;
   @Autowired
    public changeService(CustomerDao customerDao){
        this.customerDao = customerDao;
    }
    public void change(String id, String newPw){
        Customer c = customerDao.selectCustomer(id);
        Customer changeCustomer = new Customer(id,newPw,c.getName(),c.getBirth(),c.getMoney(),c.getAccount());
        customerDao.change(id,changeCustomer);
    }
}
