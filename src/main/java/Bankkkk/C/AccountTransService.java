package main.java.Bank.C;

import main.java.Bank.M.Customer;
import main.java.Bank.M.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AccountTransService {
    CustomerDao customerDao;
    @Autowired
    public AccountTransService(CustomerDao customerDao){
        this.customerDao = customerDao;
    }
    public void transfer(String id,String account) throws IOException {
//        try {
//            if (account.equals(customerDao.accountCustomer(account).getAccount())) {
//                System.out.println(customerDao.accountCustomer(account).getId() + "님이 맞습니까?");
//                System.out.println("입금하실 금액을 입력해주세요");
//                String money = reader.readLine();
//                if (pattern(money)) {
//                    if (customerDao.selectCustomer(id).getMoney() < Integer.parseInt(money)) {
//                        System.out.println("잔액이 부족합니다.");
//                    } else {
//                        customerDao.selectCustomer(id).withdrawMoney(Integer.parseInt(money));
//                        customerDao.accountCustomer(account).depositMoney(Integer.parseInt(money));
//                        System.out.println("송금에 성공했습니다.");
//                    }
//                } else {
//                    System.out.println("입력 값을 확인해주세요");
//                }
//            } else {
//                System.out.println("계좌가 일치하지 않습니다.");
//            }
//        } catch (NullPointerException e) {
//            System.out.println("입력하신 계좌는 없는 계좌번호입니다.");
//        }
    }
}
