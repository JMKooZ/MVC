package main.java.Bank.Controller;

import main.java.Bank.Dao.MemberDao;
import main.java.Bank.Service.MenuService;
import main.java.Bank.View.MainView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class MenuController {
    MemberDao memberDao;
    MenuService menuService;
    BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
    @Autowired
    public MenuController(MemberDao memberDao, MenuService menuService){
        this.memberDao = memberDao;
        this.menuService =menuService;
    }
    public void deposit(String id, String money){
        menuService = MainView.context.getBean("menuService",MenuService.class);
        if(menuService.deposit(id,money).equals("fail")){
            System.out.println("금액은 숫자만 가능합니다.");
        }else{
            System.out.println(money+"원 입금 완료.");
        }
    }
    public void withdraw(String id, String money){
        menuService = MainView.context.getBean("menuService",MenuService.class);
        if(menuService.withdraw(id,money).equals("fail")){
            System.out.println("금액은 숫자만 가능합니다.");
        }else{
            System.out.println(money+"원 출금 완료.");
        }
    }
    public void transferMoney(String id, String account) throws Exception{
        menuService = MainView.context.getBean("menuService", MenuService.class);
        menuService.transfer(id,account);
    }
    public void info(String id){
        menuService = MainView.context.getBean("menuService", MenuService.class);
        menuService.info(id);
    }

    public void changePW(String id, String pw) throws IOException {
        menuService = MainView.context.getBean("menuService", MenuService.class);
        menuService.changePw(id,pw);
    }

    public void delete(String id) throws IOException{
        menuService = MainView.context.getBean("menuService", MenuService.class);
        menuService.delete(id);
    }

    public void infoAll() {
        menuService = MainView.context.getBean("menuService", MenuService.class);
        menuService.infoAll();
    }
}
