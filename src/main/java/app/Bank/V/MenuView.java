package main.java.app.Bank.V;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import main.java.app.Bank.C.Controller;

public class MenuView {
    Controller controller;

    public MenuView(String id) throws Exception {
        this.controller = (Controller)Controller.context.getBean("controller", Controller.class);
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            while(true) {
                System.out.println("-----------");
                System.out.println("1. 입금 | 2. 출금 | 3. 잔고 | 4. 로그아웃");
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
                        if (this.controller.withdrawMoney(id, money).equals("success")) {
                            System.out.println("출금성공 출금금액: " + money);
                        }
                        break;
                    case 3:
                        System.out.print("잔고> ");
                        this.controller.balance(id);
                        break;
                    case 4:
                        System.out.println("로그아웃완료");
                }
            }
        }
    }
}
