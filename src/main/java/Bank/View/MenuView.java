package main.java.Bank.View;

import main.java.Bank.Controller.MenuController;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MenuView {
    BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
    public static boolean run = true;

    public MenuView(String id) throws Exception {
        while (run) {
            System.out.println("=======================");
            System.out.println("1.입금 | 2.출금 | 3.송금 | 4.내정보 | 0.로그아웃");
            System.out.println("5.회원목록 | 6.비밀번호 변경 | 7.회원탈퇴");
            System.out.println("=======================");
            String menuNum = read.readLine();
            switch (menuNum) {
                case "1":
                    depositProcess(id);
                    break;
                case "2":
                    withdrawProcess(id);
                    break;
                case "3":
                    transferProcess(id);
                    break;
                case "4":
                    informationProcess(id);
                    break;
                case "5":
                    adminProcess();
                    break;
                case "6":
                    changePwProcess(id);
                    break;
                case "7":
                    deleteProcess(id);
                    run = false;
                    break;
                case "0":
                    run = false;
                    break;
                default:
                    System.out.println("번호를 확인해주세요");
                    break;
            }
        }
    }

    private void informationProcess(String id) {
        MenuController controller = MainView.context.getBean("menuController",MenuController.class);
        controller.info(id);
    }

    private void deleteProcess(String id) throws Exception {
        MenuController controller = MainView.context.getBean("menuController", MenuController.class);
        System.out.println("탈퇴 후 복구 불가합니다. 탈퇴 진행 하시려면 yes 를 입력해주세요.");
        String ok = read.readLine();
        if(ok.equalsIgnoreCase("yes")) {
            controller.delete(id);
            System.out.println("정상적으로 회원 탈퇴 되었습니다.");
        }
    }

    private void changePwProcess(String id) throws Exception{
        MenuController controller = MainView.context.getBean("menuController", MenuController.class);
        System.out.print("현재 비밀번호를 입력해주세요> ");
        String pw = read.readLine();
        controller.changePW(id,pw);
    }

    private void adminProcess() {
        MenuController controller = MainView.context.getBean("menuController", MenuController.class);
        controller.infoAll();
    }

    private void transferProcess(String id) throws Exception{
        MenuController controller = MainView.context.getBean("menuController",MenuController.class);
        System.out.print("계좌 입력> ");
        String account = read.readLine();
        controller.transferMoney(id,account);
    }

    private void withdrawProcess(String id) throws Exception{
        MenuController controller = MainView.context.getBean("menuController",MenuController.class);
        System.out.print("출금 금액> ");
        String money = read.readLine();
        moneyPattern(money);
        controller.withdraw(id,money);
    }

    private void depositProcess(String id) throws Exception {
        MenuController controller = MainView.context.getBean("menuController", MenuController.class);
        System.out.print("입금 금액> ");
        String money = read.readLine();
        moneyPattern(money);
        controller.deposit(id,money);
    }

    public void moneyPattern(String money) {
        String reg = "^[1-9]\\d*$";
        if(money.isEmpty()){
            System.out.println("금액을 입력해주세요");
        } else if (! money.matches(reg)) {
            System.out.println("금액은 첫자리 0이 아닌 숫자만 가능합니다.");
        }
    }
}
