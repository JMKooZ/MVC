package main.java.Bank.C;

import main.java.Bank.M.Customer;
import main.java.Bank.M.CustomerDao;

public class withdrawMoney {
    CustomerDao customerDao;

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
