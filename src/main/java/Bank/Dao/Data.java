package main.java.Bank.Dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Component
public class Data {
    @Autowired
    public DataSource source(){
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass("com.mysql.jdbc.Driver");
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/jspbookdb");
        dataSource.setUser("root");
        dataSource.setPassword("java");

        return dataSource;
    }

}
