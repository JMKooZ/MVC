package main.java.app.Bank.C;

import main.java.app.Bank.M.CustomerDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Context {
    public Context() {
    }

    @Bean
    public CustomerDao customerDao() {
        return new CustomerDao();
    }

    @Bean
    public LoginService loginService() {
        return new LoginService(this.customerDao());
    }

    @Bean
    public InsertService insertService() {
        return new InsertService(this.customerDao());
    }

    @Bean
    public depositService depositService() {
        return new depositService(this.customerDao());
    }

    @Bean
    public withdrawMoney withdrawMoney() {
        return new withdrawMoney(this.customerDao());
    }
    @Bean
    public infoService infoService(){
        return new infoService(customerDao());
    }
    public deleteService deleteService(){
        return new deleteService(customerDao());
    }
    @Bean
    public changeService change(){
        return new changeService(customerDao());
    }
    @Bean
    public Controller controller() {
        return new Controller(this.loginService(), this.insertService(), this.customerDao(), this.depositService(), this.withdrawMoney(), infoService()
        ,deleteService(), change());
    }
}

