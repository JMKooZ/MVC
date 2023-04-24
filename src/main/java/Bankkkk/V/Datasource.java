package main.java.Bank.V;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Component
public class Datasource {
    @Autowired
    public DataSource source(){
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass("com.mysql.jdbc.Driver");
        }catch (PropertyVetoException e){
            e.printStackTrace();
        }
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/jspbookdb");
        dataSource.setUser("root");
        dataSource.setPassword("java");

        return dataSource;
    }
    //    private static HikariConfig config = new HikariConfig();
//    private static HikariDataSource hds;
//    static {
//        config.setJdbcUrl("jdbc:mysql://localhost:3306/jspbookdb");
//        config.setUsername("root");
//        config.setPassword("java");
//        config.setDriverClassName("com.mysql.jdbc.Driver");
//        hds = new HikariDataSource(config);
//    }
//    @Autowired
//    private  Datasource(){}
//    public static Connection getConnection() throws SQLException{
//        return hds.getConnection();
//    }
}
