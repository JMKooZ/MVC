package main.java.Bank.M;

import main.java.Bank.V.Datasource;
import org.springframework.stereotype.Component;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Spliterator;

@Component
public class CustomerDao {
    private Map<String, Customer> map = new HashMap<>();
    private Datasource dataSource;
    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;
    public CustomerDao(Datasource datasource){
        this.dataSource = datasource;
    }
    public void insertCustomer(String id, Customer customer) {
        conn= null;
        pstmt = null;
        try {
            conn = dataSource.source().getConnection();
            pstmt = conn.prepareStatement("insert into customer values (?,?,?,?,0,?,now(),now())");
            pstmt.setString(1,id);
            pstmt.setString(2,customer.getPw());
            pstmt.setString(3,customer.getName());
            pstmt.setInt(4,customer.getBirth());
            pstmt.setString(5,customer.getAccount());
            pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(pstmt != null) pstmt.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        this.map.put(id, customer);
    }
    public Customer selectCustomer(String id) {
        conn = null;
        pstmt = null;
        rs = null;
        Customer customer = null;
        try {
            conn = dataSource.source().getConnection();
            pstmt =conn.prepareStatement("select * from customer where id = ?");
            pstmt.setString(1,id);
            rs = pstmt.executeQuery();
            if(rs.next()){
                customer = new Customer(rs.getString(1),rs.getString(2),rs.getString(3),
                        rs.getInt(4),rs.getInt(5),rs.getString(6));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(pstmt != null) pstmt.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return customer;
    }
    public void deleteCustomer(String id){
        pstmt = null;
        conn = null;
        try {
            conn = dataSource.source().getConnection();
            pstmt = conn.prepareStatement("delete from customer where id = ?");
            pstmt.setString(1,id);
            pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(pstmt != null) pstmt.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public void change(String id,String newPw){
        conn = null ;
        pstmt = null;
        try {
            conn = dataSource.source().getConnection();
            pstmt = conn.prepareStatement("update customer set pw = ?, newPwTime = now() where id = ?");
            pstmt.setString(1,newPw);
            pstmt.setString(2,id);
            pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(pstmt != null) pstmt.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public Customer accountCustomer(String account){
        Customer c = null;
        conn = null;
        pstmt = null;
        rs = null;
        try {
            conn = dataSource.source().getConnection();
            pstmt =conn.prepareStatement("select * from customer where account = ?");
            pstmt.setString(1, account);
            rs = pstmt.executeQuery();
            if(rs.next()){
                c = new Customer(rs.getString(1),rs.getString(2),rs.getString(3),
                        rs.getInt(4),rs.getInt(5),rs.getString(6));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(pstmt != null) pstmt.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

//        for(Customer customer : map.values()){
//            if(customer.getAccount().equals(account)) {
//                c = customer;
//            }
//        }
        return c;
    }
    public void balance(String id , int money){
        conn = null ;
        pstmt = null;
        try {
            conn = dataSource.source().getConnection();
            pstmt = conn.prepareStatement("update customer set money = ? where id = ?");
            pstmt.setInt(1,money);
            pstmt.setString(2,id);
            pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(pstmt != null) pstmt.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
