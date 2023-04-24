package main.java.Bank.Dao;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
@Component
public class MemberDao {
    Map<String,MemberDto> members = new HashMap<>();
    Data data;
    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;
    public MemberDao(Data data){
        this.data = data;
    }
    public MemberDto selectById(String id){
        conn = null;
        pstmt = null;
        MemberDto member = null;
        try {
            conn = data.source().getConnection();
            pstmt = conn.prepareStatement("select * from member where id = ?");
            pstmt.setString(1,id);
            rs = pstmt.executeQuery();
            if(rs.next()){
                member = new MemberDto(rs.getString(1),rs.getString(2),
                        rs.getString(3),rs.getInt(4),rs.getString(5));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (pstmt != null)  pstmt.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return member;
    }
    public void insert(String id, MemberDto member){
        conn = null;
        pstmt = null;
        try {
            conn = data.source().getConnection();
            pstmt = conn.prepareStatement("insert into member values (?,?,?,0,?,now(),null)");
            pstmt.setString(1,id);
            pstmt.setString(2,member.getPw());
            pstmt.setString(3,member.getName());
            pstmt.setString(4,member.getAccount());
            pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (pstmt != null)  pstmt.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public MemberDto search(String name){
        conn = null;
        pstmt = null;
        MemberDto member = null;
        try {
            conn = data.source().getConnection();
            pstmt = conn.prepareStatement("select * from member where name = ?");
            pstmt.setString(1,name);
            rs = pstmt.executeQuery();
            if(rs.next()){
                member = new MemberDto(rs.getString(1),rs.getString(2),
                        rs.getString(3),rs.getInt(4),rs.getString(5));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (pstmt != null)  pstmt.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return member;
    }
    public void balance(String id, int money){
        conn = null;
        pstmt = null;
        try {
            conn = data.source().getConnection();
            pstmt = conn.prepareStatement("update member set money = ? where id = ?");
            pstmt.setInt(1,money);
            pstmt.setString(2,id);
            pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (pstmt != null)  pstmt.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public MemberDto transfer(String account){
        conn = null;
        pstmt = null;
        MemberDto member = null;
        try {
            conn = data.source().getConnection();
            pstmt = conn.prepareStatement("select * from member where account = ?");
            pstmt.setString(1,account);
            rs = pstmt.executeQuery();
            if(rs.next()){
                member = new MemberDto(rs.getString(1),rs.getString(2),
                        rs.getString(3),rs.getInt(4),rs.getString(5));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (pstmt != null)  pstmt.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return member;
    }
    public void changePw(String id, String newPw) {
        conn = null;
        pstmt = null;
        try {
            conn = data.source().getConnection();
            pstmt = conn.prepareStatement("update member set pw = ?, change_pw =now() where id = ?");
            pstmt.setString(1,newPw);
            pstmt.setString(2,id);
            pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (pstmt != null)  pstmt.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public void delete(String id) {
        conn = null;
        pstmt = null;
        try {
            conn = data.source().getConnection();
            pstmt = conn.prepareStatement("delete from member where id = ?");
            pstmt.setString(1,id);
            pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (pstmt != null)  pstmt.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public ArrayList<MemberDto> infoAll() {
        conn = null;
        pstmt = null;
        ArrayList<MemberDto> list = new ArrayList<>();
        MemberDto member = null;
        try {
            conn = data.source().getConnection();
            pstmt = conn.prepareStatement("select * from member order by cre_id");
            rs = pstmt.executeQuery();
            while (rs.next()){
                member = new MemberDto(rs.getString(1),rs.getString(2),
                        rs.getString(3),rs.getInt(4),rs.getString(5),rs.getDate(6));
                list.add(member);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (pstmt != null)  pstmt.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return list;
    }
}
