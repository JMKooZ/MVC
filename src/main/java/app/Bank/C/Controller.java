package main.java.app.Bank.C;

import main.java.app.Bank.M.CustomerDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Controller {
    public static AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Context.class);
    LoginService loginService;
    InsertService insertService;
    CustomerDao customerDao;
    depositService depositService;
    withdrawMoney withdrawMoney;
    infoService infoService;
    deleteService deleteService;
    changeService changeService;

    public Controller(LoginService loginService, InsertService insertService, CustomerDao customerDao,
                      depositService depositService, withdrawMoney withdrawMoney,infoService infoService, deleteService deleteService,
                      changeService changeService) {
        this.loginService = loginService;
        this.insertService = insertService;
        this.customerDao = customerDao;
        this.depositService = depositService;
        this.withdrawMoney = withdrawMoney;
        this.infoService = infoService;
        this.deleteService = deleteService;
        this.changeService = changeService;
    }

    public String login(String id, String pw) {
        LoginService loginService = (LoginService)context.getBean("loginService", LoginService.class);
        try {
            loginService.login(id, pw);
            return "success";
        } catch (Exception e) {
            System.out.println("회원정보가 일치하지않습니다.");
            return "fail";
        }
    }

    public String insert(String id, String pw, String name, int birth) {
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

    public void withdrawMoney(String id, int money) {
        withdrawMoney withdrawMoney = (withdrawMoney)context.getBean("withdrawMoney", withdrawMoney.class);
        if( withdrawMoney.withdrawMoney(id, money).equals("fail")) {
            System.out.println("잔고보다 출금액이 많을수 없다.");
        }else{
            System.out.println("출금에 성공했다.");
        }
    }

    public void balance(String id) {
        System.out.println(this.customerDao.selectCustomer(id).getMoney());
    }

    public void information(String id){
        infoService infoService = context.getBean("infoService", infoService.class);
        infoService.info(id);
    }
    public void delete(String id){
        customerDao.deleteCustomer(id);
    }
    public String change(String id, String pw ,String newPw){
        changeService changeService = context.getBean("change",changeService.class);
        if(!pw.equals(customerDao.selectCustomer(id).getPw())) {
            System.out.println("비밀번호가 일치하지 않습니다.");
            return "fail";
        }else {
            changeService.change(id, newPw);
            System.out.println("변경완료했습니다.");
            return "success";
        }
    }
}
