package main.java.Bank.V;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import main.java.Bank.C.Controller;

public class MenuView {
    Controller controller;

    public MenuView(String id) throws Exception {
        this.controller = (Controller) Controller.context.getBean("controller", Controller.class);
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        boolean run = true;

        while (run) {
            System.out.println("-----------");
            System.out.println("1. 입금 | 2. 출금 | 3. 잔고 | 4. 회원정보 | 5. 비밀번호 변경 | 6. 회원탈퇴 | 7.로그아웃");
            System.out.println("-----------");
            int num = Integer.parseInt(sc.readLine());
            int money;
            switch (num) {
                case 1:
                    System.out.print("입금액> ");
                    money = Integer.parseInt(sc.readLine());
                    if (this.controller.depositMoney(id, money).equals("success")) {
                        System.out.println("입금성공 입금금액: " + money);
                    }
                    break;
                case 2:
                    System.out.print("출금액> ");
                    money = Integer.parseInt(sc.readLine());
                    controller.withdrawMoney(id, money);
                    break;
                case 3:
                    System.out.print("잔고> ");
                    this.controller.balance(id);
                    break;
                case 4:
                    controller.information(id);
                    break;
                case 6:
                    controller.delete(id);
                    System.out.println("탈퇴 되었습니다.");
                    run = false;
                    break;
                case 5:
                    System.out.print("현재 비밀번호를 입력해주세요> ");
                    String pw = sc.readLine();
                    System.out.print("바꿀 비밀번호를 입력해주세요> ");
                    String newPw = sc.readLine();
                    if(controller.change(id,pw,newPw).equals("fail")){
                        break;
                    }else{
                        return;
                    }
                case 7:
                    System.out.println("로그아웃완료");
                    run = false;
                    break;
                default:
                    System.out.println("메뉴를 확인해주세요");
            }
        }
    }
}
