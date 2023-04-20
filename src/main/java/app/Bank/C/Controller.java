package main.java.app.Bank.C;

import main.java.app.Bank.M.CustomerDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Controller {
    public static AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(new Class[]{Context.class});
    LoginService loginService;
    InsertService insertService;
    CustomerDao customerDao;
    depositService depositService;
    withdrawMoney withdrawMoney;

    public Controller(LoginService loginService, InsertService insertService, CustomerDao customerDao, depositService depositService, withdrawMoney withdrawMoney) {
        this.loginService = loginService;
        this.insertService = insertService;
        this.customerDao = customerDao;
        this.depositService = depositService;
        this.withdrawMoney = withdrawMoney;
    }

    public String login(String id, String pw) {
        System.out.println("컨트롤러 요청중");
        LoginService loginService = (LoginService)context.getBean("loginService", LoginService.class);

        try {
            loginService.login(id, pw);
            return "success";
        } catch (NullPointerException var5) {
            System.out.println("아이디가 존재하지않습니다.");
            return "fail";
        }
    }

    public String insert(String id, String pw, String name, int birth) {
        System.out.println("컨트롤러 요청중");
        InsertService insertService = (InsertService)context.getBean("insertService", InsertService.class);

        try {
            if (id.equals(this.customerDao.selectCustomer(id).getId())) {
                System.out.println("중복된 아이디입니다.");
                return "fail";
            } else {
                return null;
            }
        } catch (Exception var7) {
            insertService.insert(id, pw, name, birth, 0);
            return "success";
        }
    }

    public String depositMoney(String id, int money) {
        depositService depositService = (depositService)context.getBean("depositService", depositService.class);

        try {
            depositService.deposit(id, money);
            return "success";
        } catch (NumberFormatException var5) {
            System.out.println("입금하실 금액을 입력해주세요");
            return "fail";
        }
    }

    public String withdrawMoney(String id, int money) {
        withdrawMoney withdrawMoney = (withdrawMoney)context.getBean("withdrawMoney", withdrawMoney.class);

        try {
            withdrawMoney.withdrawMoney(id, money);
            return "success";
        } catch (Exception var5) {
            System.out.println("출금하실 금액을 입력해주세요");
            return "fail";
        }
    }

    public void balance(String id) {
        System.out.println(this.customerDao.selectCustomer(id).getMoney());
    }
}
