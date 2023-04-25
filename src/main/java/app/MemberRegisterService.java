package main.java.app;


import java.time.LocalDateTime;
import java.util.Date;

public class MemberRegisterService {
    private MemberDao memberDao;
    public MemberRegisterService(MemberDao memberDao){
        this.memberDao = memberDao;
    }

    public Long regist(RegisterRequest req){
        Member member = memberDao.selectByEmail(req.getEmail());
        Date date = new Date();
        if(member != null){
            throw new DuplicateMemberException(req.getEmail()+"은 중복된 이메일입니다.");
        }
        Member newMember = new Member(0,req.getEmail(), req.getPassword(), req.getName(), date);
        memberDao.insert(newMember);
        return newMember.getId();
    }

}
