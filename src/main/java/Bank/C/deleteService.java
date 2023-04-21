package main.java.Bank.C;

import main.java.Bank.M.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class deleteService {
    CustomerDao customerDao;
    @Autowired
    public deleteService(CustomerDao customerDao){
        this.customerDao = customerDao;
    }
    public void delete(String id){
        customerDao.deleteCustomer(id);
    }
}
