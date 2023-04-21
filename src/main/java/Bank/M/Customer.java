package main.java.Bank.M;

public class Customer {
    private String id;
    private String pw;
    private String name;
    private int birth;
    private int money;

    public Customer(String id, String pw, String name, int birth, int money) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.birth = birth;
        this.money = money;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return this.pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirth() {
        return this.birth;
    }

    public void setBirth(int birth) {
        this.birth = birth;
    }

    public int getMoney() {
        return this.money;
    }

    public void depositMoney(int money) {
        this.money += money;
    }

    public void withdrawMoney(int money) {
        this.money -= money;
    }
}
