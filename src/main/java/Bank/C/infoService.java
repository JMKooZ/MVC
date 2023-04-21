package main.java.Bank.C;

import main.java.Bank.M.Customer;
import main.java.Bank.M.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class infoService {
    CustomerDao customerDao;
   @Autowired
    public infoService(CustomerDao customerDao){
        this.customerDao = customerDao;
    }
    public void info(String id){
        Customer customer = customerDao.selectCustomer(id);
        System.out.println("ID: "+customer.getId() + " PW: "+customer.getPw() + " NAME: "+customer.getName() + " BIRTH: "+customer.getBirth()+" 잔고: "+customer.getMoney());
    }
}
