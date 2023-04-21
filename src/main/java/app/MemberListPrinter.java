package main.java.app;

import java.util.Collection;

public class MemberListPrinter {
    MemberDao memberDao;
    MemberPrinter memberPrinter;

    public MemberListPrinter(MemberDao memberDao, MemberPrinter memberPrinter) {
        this.memberDao = memberDao;
        this.memberPrinter = memberPrinter;
    }

    public void printAll(){
        Collection<Member> members = memberDao.selectAll();
        for (Member member : members) {
            memberPrinter.print(member);
        }
    }
}
