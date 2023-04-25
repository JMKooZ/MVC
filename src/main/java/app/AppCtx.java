package main.java.app;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
public class AppCtx {
    @Bean
    public MemberDao memberDao(){
        return new MemberDao(dataSource());
    }
    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
        }catch (PropertyVetoException e){
            throw new RuntimeException(e);
        }
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/jspbookdb");
        dataSource.setUser("root");
        dataSource.setPassword("java");
        return dataSource;
    }

    @Bean
    public MemberRegisterService memberRegSvc(){
        return new MemberRegisterService(memberDao());
    }
    @Bean       // 세터주입
    public ChangePasswordService changePwdSvc(){
        ChangePasswordService changePasswordService = new ChangePasswordService();
        changePasswordService.setMemberDao(memberDao());
        return changePasswordService;
    }
    @Bean
    public MemberPrinter memberPrinter(){
        return new MemberPrinter();
    }
    @Bean       // 생성자 주입
    public MemberListService memberListService(){
        return new MemberListService(memberDao(),memberPrinter());
    }
    @Bean
    public VersionPrinter versionPrinter(){
        VersionPrinter v = new VersionPrinter();
        v.setMajorVersion(8);
        v.setMinorVersion(19);
        return v;
    }
    @Bean
    public MemberRemoveService removeService(){
        return new MemberRemoveService(memberDao());
    }
}
