package main.java.app;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MemberDao {
    private DataSource dataSource;
    private static long nextId = 0;
    private Map<String,Member> map = new HashMap<>();
    Connection conn;
    PreparedStatement pstmt;
    public MemberDao(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public Member selectByEmail(String email) {
        return map.get(email);
    }

    public void insert(Member member) {
        member.setId(++nextId);
        map.put(member.getEmail(),member);
        conn = null;
        pstmt = null;
        try {
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement("insert into app VALUES (?,?,?,now())");
            pstmt.setString(1,member.getEmail());
            pstmt.setString(2,member.getPassword());
            pstmt.setString(3,member.getName());
            pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(pstmt != null) pstmt.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public void update(Member member,String newPw){
        map.put(member.getEmail(),member);
        conn = null;
        pstmt = null;
        try {
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement("update app set pw = ? where id = ?");
            pstmt.setString(1,newPw);
            pstmt.setString(2,member.getEmail());
            pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(pstmt != null) pstmt.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public Collection<Member> selectAll(){
       conn = null;
       pstmt = null;
        ResultSet rs =null;
        Member member = null;
        ArrayList<Member> list = new ArrayList<>();
       try {
           conn = dataSource.getConnection();
           pstmt = conn.prepareStatement("select * from app");
           rs = pstmt.executeQuery();
           while(rs.next()){
                member = new Member(rs.getString(1),rs.getString(2),rs.getString(3),rs.getDate(4));
                list.add(member);
           }
       }catch (Exception e){
           e.printStackTrace();
       }
        return list;
    }
    public void remove(String id){
        map.remove(id);
    }
}
