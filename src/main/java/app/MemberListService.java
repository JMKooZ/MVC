package main.java.app;

public class MemberListService {
    private MemberDao memberDao;
    private MemberPrinter printer;

    public MemberListService(MemberDao memberDao, MemberPrinter printer){
        this.memberDao = memberDao;
        this.printer = printer;
    }



}
