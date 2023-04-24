package main.java.Bank.Controller;

import main.java.Bank.Dao.MemberDao;
import main.java.Bank.Dao.MemberDto;
import main.java.Bank.Service.MainService;
import main.java.Bank.View.MainView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainController {
    MemberDao memberDao;
    MainService mainService;
    MainService service;

    @Autowired
    public MainController(MemberDao memberDao, MainService mainService) {
        this.memberDao = memberDao;
        this.mainService = mainService;
    }
    public String login(String id, String pw) {
        service = MainView.context.getBean("mainService", MainService.class);
        String result = service.loginS(id, pw);
        try {
            if (result.equals("success")) {
                System.out.println("로그인 성공");
                return "success";
            } else {
                System.out.println("정보가 일치하지 않습니다.");
                return "fail";
            }
        } catch (NullPointerException e) {
            System.out.println("회원정보가 없습니다.");
            return "fail";
        }
    }
    public void create(String id, String pw, String name) {
        service = MainView.context.getBean("mainService", MainService.class);
        if (service.insert(id, pw, name).equals("fail")) {
            System.out.println("중복된 아이디입니다.");
        } else {
            System.out.println("회원가입 성공!");
        }
    }
    public void search(String name) {
        service = MainView.context.getBean("mainService", MainService.class);
        MemberDto m = service.searchID(name);
        if (m != null) {
            System.out.println("ID: " + m.getId() + " PW: " + m.getPw() + " 잔고: " + m.getMoney() + " 계좌: " + m.getAccount());
        } else {
            System.out.println("회원정보가 존재하지 않습니다.");

        }
    }
}
