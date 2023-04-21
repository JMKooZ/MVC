package main.java.Bank.C;

import main.java.Bank.M.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginService {
    CustomerDao customerDao;
@Autowired
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
