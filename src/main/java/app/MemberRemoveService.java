package main.java.app;

import java.awt.*;

public class MemberRemoveService {
    MemberDao memberDao;
    public MemberRemoveService(MemberDao memberDao){
        this.memberDao = memberDao;
    }
    public void remove(String id,String pw){
        Member member = memberDao.selectByEmail(id);
        if(member != null){
            if(member.getPassword().equals(pw)){
                memberDao.remove(id);
                System.out.println("삭제되었습니다.");
            }else {
                throw new WrongPasswordException();
            }
        }else{
            throw new MemberNotFoundException();
        }

//        if(id.equals(memberDao.selectByEmail(id).getEmail()) && pw.equals(memberDao.selectByEmail(id).getPassword())) {
//            memberDao.remove(id);
//        }else{
//            System.out.println("실패");
//        }
    }
}
