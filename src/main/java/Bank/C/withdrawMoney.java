package main.java.Bank.C;

import main.java.Bank.M.Customer;
import main.java.Bank.M.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class withdrawMoney {
    CustomerDao customerDao;
@Autowired
    public withdrawMoney(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public String withdrawMoney(String id, int money) {
        Customer customer = this.customerDao.selectCustomer(id);
        if(customerDao.selectCustomer(id).getMoney() < money){
            return "fail";
        }else{
            customer.withdrawMoney(money);
            return "success";
        }
    }
}
