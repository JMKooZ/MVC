package main.java.Bank.C;

import main.java.Bank.M.CustomerDao;
import main.java.Bank.V.MenuView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
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
    AccountTransService accountTransService;

    @Autowired
    public Controller(LoginService loginService, InsertService insertService, CustomerDao customerDao,
                      depositService depositService, withdrawMoney withdrawMoney, infoService infoService, deleteService deleteService,
                      changeService changeService, AccountTransService accountTransService) {
        this.loginService = loginService;
        this.insertService = insertService;
        this.customerDao = customerDao;
        this.depositService = depositService;
        this.withdrawMoney = withdrawMoney;
        this.infoService = infoService;
        this.deleteService = deleteService;
        this.changeService = changeService;
        this.accountTransService = accountTransService;
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public String login(String id, String pw) {
        LoginService loginService = (LoginService) context.getBean("loginService", LoginService.class);
            try {
                if (loginService.login_service(id, pw).equals("s")) {
                    loginService.login_service(id, pw);
                    return "success";
                } else {
                    return "f";
                }
            } catch (Exception e) {
                System.out.println("회원정보가 일치하지않습니다.");
                return "fail";
            }
        }

    public String insert(String id, String pw, String name, int birth) {
        InsertService insertService = (InsertService) context.getBean("insertService", InsertService.class);
        if(idPattern(id)) {
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
        }else{
            return "fail";
        }
    }

    public void depositMoney(String id, String money) {
        depositService depositService = (depositService) context.getBean("depositService", depositService.class);
        if (pattern(money)) {
            depositService.deposit(id, money);
            System.out.println(money + "원 입금에 성공하였습니다.");
        } else {
            System.out.println("입력 값을 확인해주세요");
        }
    }

    public void withdrawMoney(String id, String money) {
        withdrawMoney withdrawMoney = (withdrawMoney) context.getBean("withdrawMoney", withdrawMoney.class);
        if (pattern(money)) {
            if (withdrawMoney.withdrawMoney(id, money).equals("fail")) {
                System.out.println("잔고보다 출금액이 많을수 없다.");
            } else {
                System.out.println(money + "원 출금에 성공했다.");
            }
        } else {
            System.out.println("입력 값을 확인해주세요");
        }
    }

    public void balance(String id) {
        System.out.println(this.customerDao.selectCustomer(id).getMoney());
    }

    public void information(String id) {
        infoService infoService = context.getBean("infoService", infoService.class);
        infoService.info(id);
    }

    public void delete(String id) {
        customerDao.deleteCustomer(id);
    }

    public String change(String id, String pw, String newPw) {
        changeService changeService = context.getBean("change", changeService.class);
        if (!pw.equals(customerDao.selectCustomer(id).getPw())) {
            System.out.println("비밀번호가 일치하지 않습니다.");
            return "fail";
        } else {
            changeService.change(id, newPw);
            System.out.println("변경완료했습니다.");
            System.out.println("다시 로그인 해주세요.");
            return "success";
        }
    }

    public void accountTrans(String id,String account) throws IOException {
        AccountTransService accountTransService = context.getBean("accountTransService", AccountTransService.class);
        try {
            if (account.equals(customerDao.accountCustomer(account).getAccount())) {
                System.out.println(customerDao.accountCustomer(account).getId() + "님이 맞습니까?");
                System.out.println("입금하실 금액을 입력해주세요");
                String money = reader.readLine();
                if (pattern(money)) {
                    if (customerDao.selectCustomer(id).getMoney() < Integer.parseInt(money)) {
                        System.out.println("잔액이 부족합니다.");
                    } else {
                        customerDao.selectCustomer(id).withdrawMoney(Integer.parseInt(money));
                        customerDao.accountCustomer(account).depositMoney(Integer.parseInt(money));
                        System.out.println("송금에 성공했습니다.");
                    }
                } else {
                    System.out.println("입력 값을 확인해주세요");
                }
            } else {
                System.out.println("계좌가 일치하지 않습니다.");
            }
        } catch (NullPointerException e) {
            System.out.println("입력하신 계좌는 없는 계좌번호입니다.");
        }
//        accountTransService.transfer(id,account);
    }

    public static boolean pattern(String money) {
        String reg = "^[1-9]\\d*$";
        if (money.isEmpty()) {
            System.out.println("입금하실 금액을 입력해주세요");
            return false;
        } else if (money.equals("") || money.matches(reg)) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean idPattern(String id){
        String regExpId =  "^[a-zA-Z0-9-]{1,20}$";
        if(id.isEmpty()){
            System.out.println("아이디를 입력해주세요");
            return false;
        }else if(id.equals("") || id.matches(regExpId)){
            return true;
        }else {
            System.out.println("아이디 형식을 맞춰주세요");
            return false;
        }
    }
}
