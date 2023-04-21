package main.java.Bank.C;

import main.java.Bank.M.Customer;
import main.java.Bank.M.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class depositService {
    CustomerDao customerDao;
@Autowired
    public depositService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public void deposit(String id, String money) {
        Customer customer = this.customerDao.selectCustomer(id);
        int m = Integer.parseInt(money);
        try {
            customer.depositMoney(m);
        } catch (NumberFormatException var5) {
            System.out.println("숫자만 입력해주세요");
        }

    }
}
