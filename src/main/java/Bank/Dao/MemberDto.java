package main.java.Bank.Dao;

import java.util.Date;

public class MemberDto {
    String id;
    String pw;
    String name;
    int money;
    String account;
    Date cre_id;

    public MemberDto(String id, String pw, String name, int money, String account, Date cre_id) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.money = money;
        this.account = account;
        this.cre_id = cre_id;
    }

    public MemberDto(String id, String pw, String name, int money, String account) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.money = money;
        this.account = account;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }
    public int depositMoney(int money){
        return this.money += money;
    }
    public int withdrawMoney(int money){
        return this.money -= money;
    }
    public String getAccount() {
        return account;
    }

    public Date getCre_id() {
        return cre_id;
    }
}
