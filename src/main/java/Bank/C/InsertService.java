package main.java.Bank.C;

import main.java.Bank.M.Customer;
import main.java.Bank.M.CustomerDao;

public class InsertService {
    CustomerDao customerDao;

    public InsertService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public void insert(String id, String pw, String name, int birth, int money) {
        Customer customer = new Customer(id, pw, name, birth, 0);
        this.customerDao.insertCustomer(id, customer);
        System.out.println("회원가입 성공");
    }
}

