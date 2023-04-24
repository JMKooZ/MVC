package main.java.Bank.Service;

import main.java.Bank.Dao.MemberDao;
import main.java.Bank.Dao.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Random;

@Component
public class MainService {
    MemberDao memberDao;

    @Autowired
    public MainService(MemberDao memberDao) {
        this.memberDao = memberDao;
    }
    public String loginS(String id, String pw) {
        if (id.equals(memberDao.selectById(id).getId()) && pw.equals(memberDao.selectById(id).getPw())) {
            return "success";
        } else {
            return "fail";
        }
    }
    public String insert(String id, String pw , String name){
        Random account = new Random();
        String acc = String.valueOf(account.nextInt(1000));
        try {
            if(id.equals(memberDao.selectById(id).getId())){
                return "fail";
            }
        }catch (NullPointerException e){
            MemberDto memberDto = new MemberDto(id, pw, name, 0, acc);
            memberDao.insert(id, memberDto);
            return "success";
        }
        return "success";
    }
    public MemberDto searchID(String name){
        return memberDao.search(name);
    }
}
