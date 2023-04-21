package main.java.Bank.C;

import main.java.Bank.M.CustomerDao;


public class deleteService {
    CustomerDao customerDao;
    public deleteService(CustomerDao customerDao){
        this.customerDao = customerDao;
    }
    public void delete(String id){
        customerDao.deleteCustomer(id);
    }
}
