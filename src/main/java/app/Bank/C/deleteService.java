package main.java.app.Bank.C;

import main.java.app.Bank.M.CustomerDao;


public class deleteService {
    CustomerDao customerDao;
    public deleteService(CustomerDao customerDao){
        this.customerDao = customerDao;
    }
    public void delete(String id){
        customerDao.deleteCustomer(id);
    }
}
