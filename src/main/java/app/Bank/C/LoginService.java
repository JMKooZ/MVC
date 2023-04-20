package main.java.app.Bank.C;

import main.java.app.Bank.M.CustomerDao;

public class LoginService {
    CustomerDao customerDao;

    public LoginService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public void login(String id, String pw) {
        System.out.println("서비스 요청");
        if (id.equals(this.customerDao.selectCustomer(id).getId()) && pw.equals(this.customerDao.selectCustomer(id).getPw())) {
            System.out.println("로그인 성공");
        }

    }
}
