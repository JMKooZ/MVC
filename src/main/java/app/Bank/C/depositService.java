package main.java.app.Bank.C;

import main.java.app.Bank.M.Customer;
import main.java.app.Bank.M.CustomerDao;

public class depositService {
    CustomerDao customerDao;

    public depositService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public void deposit(String id, int money) {
        Customer customer = this.customerDao.selectCustomer(id);

        try {
            customer.depositMoney(money);
        } catch (NumberFormatException var5) {
            System.out.println("숫자만 입력해주세요");
        }

    }
}
