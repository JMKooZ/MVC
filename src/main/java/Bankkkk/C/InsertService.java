package main.java.Bank.C;

import main.java.Bank.M.Customer;
import main.java.Bank.M.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class InsertService {
    CustomerDao customerDao;
@Autowired
    public InsertService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public void insert(String id, String pw, String name, int birth, int money) {
        Random r = new Random();
        String account = String.valueOf(r.nextInt(100));
        Customer customer = new Customer(id, pw, name, birth, 0,account);
        this.customerDao.insertCustomer(id, customer);
        System.out.println("회원가입 성공");
    }
}
