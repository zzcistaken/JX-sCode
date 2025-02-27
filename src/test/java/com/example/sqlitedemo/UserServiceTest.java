package com.example.sqlitedemo;

import com.example.demo.entity.User;
import com.example.demo.util.DataUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

public class UserServiceTest {

    public static void main(String[] args) {
        testQueryUser();
//        testAddUser();
    }

    public static void testQueryUser() {
        System.out.println("start test");
        String sql = "SELECT * FROM users WHERE id = ?";
        User u = DataUtil.getJdbcTemplate().queryForObject(sql, new Object[]{1}, new BeanPropertyRowMapper<>(User.class));
        System.out.println("test: " + u.toString());
    }

    public static void testAddUser() {
        System.out.println("start test");
        String sql = "INSERT INTO users (name, email) VALUES (?, ?)";
//        int res = DataUtil.getJdbcTemplate().update(sql, "Bill", "Bill2@163.com");
//        System.out.println("test: " + res);

        sql = "SELECT * FROM users WHERE email = ?";
        User u = DataUtil.getJdbcTemplate().queryForObject(sql, new Object[]{"Bill@163.com"}, new BeanPropertyRowMapper<>(User.class));
        System.out.println("test: " + u.toString());
    }

}
