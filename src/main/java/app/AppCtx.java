package main.java.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppCtx {
    @Bean
    public MemberDao memberDao(){
        return new MemberDao();
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
