package main.java.Bank.M;

import java.util.HashMap;
import java.util.Map;

public class CustomerDao {
    private Map<String, Customer> map = new HashMap();

    public CustomerDao() {
    }

    public void insertCustomer(String id, Customer customer) {
        this.map.put(id, customer);
    }

    public Customer selectCustomer(String id) {
        return (Customer)this.map.get(id);
    }
    public void deleteCustomer(String id){map.remove(id);}
    public void change(String id, Customer newCustomer){map.put(id,newCustomer);}
}