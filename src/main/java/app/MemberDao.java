package main.java.app;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class MemberDao {
    private DataSource dataSource;
    private static long nextId = 0;
    private Map<String,Member> map = new HashMap<>();
    Connection conn;
    PreparedStatement pstmt;
    private JdbcTemplate jdbcTemplate;
    public MemberDao(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    public Member selectByEmail(String email){
        List<Member> list = jdbcTemplate.query("select * from app where id = ?", new RowMapper<Member>() {
            @Override
            public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                Member member = new Member(rs.getLong(1),rs.getString(2),
                        rs.getString(3),rs.getString(4),rs.getTime(5));
                return member;
            }
        } , email );
        return list.isEmpty() ? null : list.get(0);
    }
        public void insert(Member member){
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                    PreparedStatement pstmt = con.prepareStatement(
                            "insert into app values (?,?,?,now())" , new String[]{"ID"});
                    pstmt.setString(1,member.getEmail());
                    pstmt.setString(2,member.getPassword());
                    pstmt.setString(3,member.getName());
                    return pstmt;
                }
            }, keyHolder);
            Number keyValue = keyHolder.getKey();
            member.setId(keyValue.longValue());
        }



//    public Member selectByEmail(String email) {
//        return map.get(email);
//    }
//    public void insert(Member member) {
//        member.setId(++nextId);
//        map.put(member.getEmail(),member);
//        conn = null;
//        pstmt = null;
//        try {
//            conn = dataSource.getConnection();
//            pstmt = conn.prepareStatement("insert into app VALUES (?,?,?,now())");
//            pstmt.setString(1,member.getEmail());
//            pstmt.setString(2,member.getPassword());
//            pstmt.setString(3,member.getName());
//            pstmt.executeUpdate();
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            try {
//                if(pstmt != null) pstmt.close();
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//    }
    public void update(Member member, String newPw){
        jdbcTemplate.update("update app set pw = ? where email = ?", newPw, member.getEmail());
        }

//    public void update(Member member,String newPw){
//        map.put(member.getEmail(),member);
//        conn = null;
//        pstmt = null;
//        try {
//            conn = dataSource.getConnection();
//            pstmt = conn.prepareStatement("update app set pw = ? where id = ?");
//            pstmt.setString(1,newPw);
//            pstmt.setString(2,member.getEmail());
//            pstmt.executeUpdate();
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            try {
//                if(pstmt != null) pstmt.close();
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//    }
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
                member = new Member(rs.getLong(1),rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getDate(5));
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
