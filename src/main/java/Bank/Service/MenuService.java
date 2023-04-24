package main.java.Bank.Service;

import main.java.Bank.Dao.MemberDao;
import main.java.Bank.Dao.MemberDto;
import main.java.Bank.View.MenuView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

@Component
public class MenuService {
    MemberDao memberDao;
    BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

    @Autowired
    public MenuService(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public String deposit(String id, String money) {
        MemberDto m = memberDao.selectById(id);
        try {
            int deposit = Integer.parseInt(money);
            int result = m.depositMoney(deposit);
            memberDao.balance(id, result);
        } catch (NumberFormatException e) {
            return "fail";
        }
        return "success";
    }

    public String withdraw(String id, String money) {
        MemberDto m = memberDao.selectById(id);
        try {
            int deposit = Integer.parseInt(money);
            int result = m.withdrawMoney(deposit);
            memberDao.balance(id, result);
        } catch (NumberFormatException e) {
            return "fail";
        }
        return "success";
    }

    public String transfer(String id, String account) throws Exception {
        // 계좌의 주인이 누구인지 일단 보냄
        MemberDto member;
        try {
            member = memberDao.transfer(account);
            System.out.print(member.getName() + "님이 맞습니까? --- 맞으면 yes 입력");
            String ok = read.readLine();
            if (ok.equalsIgnoreCase("yes")) {
                System.out.print("송금 금액> ");
                String money = read.readLine();
                int m = Integer.parseInt(money);
                if(memberDao.selectById(id).getMoney() < m){
                    System.out.println("잔액이 부족합니다.");
                    return "fail";
                }else {
                    withdraw(id, money);
                    deposit(member.getId(), money);
                    System.out.println(member.getName() + "님의 계좌로 " + money + "원 입금 완료");
                    return "success";
                }
            }else {
                return "fail";
            }

        } catch (Exception e) {
            System.out.println("등록된 계좌가 아닙니다.");
            return "fail";
        }
    }
    public void info(String id) {
        MemberDto member = memberDao.selectById(id);
        System.out.println("ID: "+ member.getId()+" PW: "+member.getPw()+" MONEY: "+member.getMoney()
                +" ACCOUNT: "+member.getAccount());
    }

    public void changePw(String id, String pw) throws IOException {
        MemberDto member =memberDao.selectById(id);
        if(member.getPw().equals(pw)){
            System.out.print("바꿀 비밀번호를 입력해주세요");
            String newPw = read.readLine();
            memberDao.changePw(id,newPw);
            System.out.println("변경이 완료되었습니다. 재로그인 해주세요");
            MenuView.run = false;
        }else{
            System.out.println("비밀번호가 일치하지않습니다.");
        }
    }

    public void delete(String id) throws IOException{
        memberDao.delete(id);
    }

    public void infoAll() {
        ArrayList<MemberDto> list = memberDao.infoAll();
        for(int i = 0 ; i < list.size() ; i++) {
            System.out.println("ID:\t "+ list.get(i).getId()+" PW:\t "+list.get(i).getPw()+" NAME:\t "+list.get(i).getName()
                    +" MONEY:\t "+list.get(i).getMoney() +" ACCOUNT:\t "+list.get(i).getAccount()+" CRE_ID:\t "+list.get(i).getCre_id());
        }
    }
}
