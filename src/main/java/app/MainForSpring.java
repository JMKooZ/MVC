package main.java.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainForSpring {
    private static ApplicationContext ctx = null;

    public static void main(String[] args) throws IOException {
        ctx = new GenericXmlApplicationContext("classpath:appCtx.xml");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            System.out.println("명령어 입력하세요: ");
            String command = reader.readLine();
            if(command.equalsIgnoreCase("exit")){
                System.out.println("종료합니다");
                break;
            }
            if (command.startsWith("new ")) {
                processNewCommand(command.split(" "));
                continue;
            } else if (command.startsWith("change ")) {
                processChangeCommand(command.split(" "));
                continue;
            } else if(command.equals("list")){
                //1. 기능을 확장해 보세요.
                processListCommand();
                continue;
            } else if(command.equals("version")){
                //2. 기능을 확장해 보세요.
                processVersionCommand();
                continue;
            }
            printHelp();
        }
    }

    private static void processVersionCommand() {
        VersionPrinter versionPrinter = ctx.getBean("versionPrinter", VersionPrinter.class);
        versionPrinter.print();
    }

    private static void processListCommand() {
        MemberListPrinter listPrinter = ctx.getBean("listPrinter", MemberListPrinter.class);
        listPrinter.printAll();
    }

    private static void processNewCommand(String[] arg) {
        if (arg.length != 5) {
            printHelp();
            return;
        }
        MemberRegisterService regSvc = ctx.getBean("memberRegSvc",MemberRegisterService.class);
        RegisterRequest req = new RegisterRequest();
        req.setEmail(arg[1]);
        req.setName(arg[2]);
        req.setPassword(arg[3]);
        req.setConfirmPassword(arg[4]);
        if (!req.isPasswordEqualToConfirmPassword()) {
            System.out.println("암호와 확인이 일치하지 않습니다\n");
            return;
        }
        try {
            regSvc.regist(req);
            System.out.println("등록했습니다.\n");
        } catch (DuplicateMemberException e) {
//            e.printStackTrace();
            System.out.println(e.getMessage());
//            System.out.println("이미 존재하는 이메일입니다.");
        }
    }

    private static void processChangeCommand(String[] arg) {
        if(arg.length != 4){
            printHelp();
            return;
        }
        ChangePasswordService changePwdSvc = ctx.getBean("changePwdSvc", ChangePasswordService.class);
        try{
            changePwdSvc.changePassword(arg[1],arg[2],arg[3]);
            System.out.println("암호를 변경했습니다.\n");
        }catch(MemberNotFoundException e){
            System.out.println("존재하지 않는 이메일입니다.\n");
        }catch(WrongPasswordException e){
            System.out.println("이메일과 암호가 일치하지 않습니다.\n");
        }
    }

    private static void printHelp() {
        System.out.println("잘못된 명령입니다. 아래 명령어 사용법을 확인하세요.");
        System.out.println("명령어 사용법");
        System.out.println("new 이메일 이름 비밀번호 비밀번호확인");
        System.out.println("change 이메일 비밀번호 새비밀번호");
        System.out.println("멤버 리스트 찾기 > list < ");
    }

}
