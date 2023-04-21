package main.java.Bank.C;

import main.java.Bank.M.Customer;
import main.java.Bank.M.CustomerDao;

public class infoService {
    CustomerDao customerDao;
    public infoService(CustomerDao customerDao){
        this.customerDao = customerDao;
    }
    public void info(String id){
        Customer customer = customerDao.selectCustomer(id);
        System.out.println("ID: "+customer.getId() + " PW: "+customer.getPw() + " NAME: "+customer.getName() + " BIRTH: "+customer.getBirth()+" 잔고: "+customer.getMoney());
    }
}
