package main.java.app.Bank.V;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import main.java.app.Bank.C.Controller;

public class MainView {
    boolean a;
    String id;
    Controller controller;

    public MainView() throws Exception {
        this.controller = (Controller)Controller.context.getBean("controller", Controller.class);
        this.a = true;
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));

        while(this.a) {
            System.out.println("--------------");
            System.out.println("1.로그인 | 2. 회원가입 3. 로그아웃");
            System.out.println("--------------");
            int num = Integer.parseInt(sc.readLine());
            String pw;
            switch (num) {
                case 1:
                    System.out.print("아이디 입력> ");
                    this.id = sc.readLine();
                    System.out.print("비밀번호 입력> ");
                    pw = sc.readLine();
                    if (this.controller.login(this.id, pw).equals("success")) {
                        System.out.println("서섹스");
                        this.a = false;
                        System.out.println(this.id + "님 환영띠");
                        new MenuView(this.id);
                    } else {
                        System.out.println("로그인 실패");
                    }
                    break;
                case 2:
                    System.out.print("아이디 입력> ");
                    this.id = sc.readLine();
                    System.out.print("비밀번호 입력> ");
                    pw = sc.readLine();
                    System.out.print("이름 입력> ");
                    String name = sc.readLine();
                    System.out.print("생년월일 입력> ");
                    int birth = Integer.parseInt(sc.readLine());
                    if (this.controller.insert(this.id, pw, name, birth).equals("success")) {
                        System.out.println("success");
                    } else {
                        System.out.println("fail");
                    }
                    break;
                case 3:
                    System.out.println("종료");
                    this.a = false;
                    break;
                default:
                    System.out.println("메뉴 확인");
            }
        }

    }

    public static void main(String[] args) throws Exception {
        new MainView();
    }
}