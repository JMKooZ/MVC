package main.java.Bank.Dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class MemberDao {
    Data data;
    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;
    private JdbcTemplate jdbcTemplate;

    public MemberDao(Data data) {
        this.jdbcTemplate = new JdbcTemplate(data.source());
        this.data = data;
    }

    public MemberDto selectById(String id) {
        List<MemberDto> list = jdbcTemplate.query("select * from member where id = ?", new RowMapper<MemberDto>() {
            @Override
            public MemberDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                MemberDto member = new MemberDto(rs.getString(1), rs.getString(2), rs.getString(3)
                        , rs.getInt(4), rs.getString(5));
                return member;
            }
        }, id);
        return list.isEmpty() ? null : list.get(0);
    }

    public void insert(String id, MemberDto member) {
        jdbcTemplate.update("insert into member values (?,?,?,0,?,now(),null)"
                , member.getId(), member.getPw(), member.getName(), member.getAccount());
    }

    public MemberDto search(String name) {
        List<MemberDto> list = jdbcTemplate.query("select * from member where name = ? order by name", new RowMapper<MemberDto>() {
            @Override
            public MemberDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                MemberDto member = new MemberDto(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getInt(4), rs.getString(5));
                return member;
            }
        }, name);
        return list.isEmpty() ? null : list.get(0);
    }

    public void balance(String id, int money) {
        jdbcTemplate.update("update member set money = ? where id =? ", money, id);
    }

    public MemberDto transfer(String account) {
        List<MemberDto> list = jdbcTemplate.query("select * from member where account = ?", new RowMapper<MemberDto>() {
            @Override
            public MemberDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                MemberDto member = new MemberDto(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getInt(4), rs.getString(5));
                return member;
            }
        }, account);
        return list.isEmpty() ? null : list.get(0);
    }

    public void changePw(String id, String newPw) {
        jdbcTemplate.update("update member set pw = ? where id =?", newPw, id);
    }

    public void delete(String id) {
        jdbcTemplate.update("delete from member where id = ? ", id);
    }

    public Collection<MemberDto> infoAll() {
        List<MemberDto> list;
        list = jdbcTemplate.query("select * from member", new RowMapper<MemberDto>() {
            @Override
            public MemberDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                MemberDto member = new MemberDto(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getInt(4), rs.getString(5), rs.getDate(6));
                return member;
            }
        });
        return list;
    }
}
