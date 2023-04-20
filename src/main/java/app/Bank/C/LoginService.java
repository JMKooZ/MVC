package main.java.app.Bank.C;

import jdk.nashorn.internal.ir.ReturnNode;
import main.java.app.Bank.M.CustomerDao;

public class LoginService {
    CustomerDao customerDao;

    public LoginService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public String login_service(String id, String pw) {
        if (id.equals(this.customerDao.selectCustomer(id).getId()) && pw.equals(this.customerDao.selectCustomer(id).getPw())) {
            System.out.println("성공컨트롤로 이동중");
            return "s";
        }else{
            System.out.println("실패컨트롤로 이동중");
            return "f";
        }
    }
}
