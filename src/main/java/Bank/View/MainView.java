package main.java.Bank.View;

import main.java.Bank.Bean.Context;
import main.java.Bank.Controller.MainController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainView {
    BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
    public static AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Context.class);
    boolean run = true;
    public MainView() throws Exception {

        while (run) {
            System.out.println("환영합니다.");
            System.out.println("==============");
            System.out.println("1. 로그인 | 2. 회원가입 | 3. ID&PW찾기");
            System.out.println("==============");
            String menuNum = read.readLine();
            switch (menuNum) {
                case "1":
                    MenuView.run = true;
                    loginView();
                    break;
                case "2":
                    createView();
                    break;
                case "3":
                    searchIdPw();
                    break;
                default:
                    System.out.println("메뉴번호를 확인해주세요.");
                    break;
            }
        }
    }

    public void loginView() throws Exception {
        MainController controller = (MainController) context.getBean("mainController", MainController.class);
        System.out.print("아이디> ");
        String id = read.readLine();
        System.out.print("비밀번호> ");
        String pw = read.readLine();
        try {
            if(controller.login(id, pw).equals("success")){
                new MenuView(id);
            }
        }catch (NullPointerException e){
            System.out.println("회원정보가 존재하지 않습니다.");
        }

    }

    private void createView() throws Exception {
        MainController controller = (MainController) context.getBean("mainController", MainController.class);
        System.out.print("아이디> ");
        String id = read.readLine();
        System.out.print("비밀번호> ");
        String pw = read.readLine();
        System.out.print("이름> ");
        String name = read.readLine();
        controller.create(id, pw, name);
    }

    private void searchIdPw() throws Exception{
        MainController controller = (MainController) context.getBean("mainController", MainController.class);
        System.out.print("이름> ");
        String name = read.readLine();
        controller.search(name);
    }
}
