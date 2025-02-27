package com.example.demo.repository;

import com.example.demo.entity.User;
import com.example.demo.entity.UserSubject;
import com.example.demo.util.DataUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserSubjectRepository {

    public List<UserSubject> findAll() {
        String sql = "SELECT * FROM userSubject order by id";
        return DataUtil.getJdbcTemplate().query(sql, new BeanPropertyRowMapper<>(UserSubject.class));
    }

    public UserSubject findById(int id) {
        String sql = "SELECT * FROM userSubject WHERE id = ?";
        return DataUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(UserSubject.class));
    }

    public List<UserSubject> findByName(String name) {
        String sql = "SELECT * FROM userSubject WHERE name = ?";
        return DataUtil.getJdbcTemplate().query(sql, new Object[]{name}, new BeanPropertyRowMapper<>(UserSubject.class));
    }

    public int insert(UserSubject user) {
        String sql = "INSERT INTO userSubject (name, subject) VALUES (?, ?)";
        return DataUtil.getJdbcTemplate().update(sql, user.getName(), user.getSubject());
    }

    public int update(UserSubject user) {
        String sql = "UPDATE userSubject SET name = ?, subject = ? WHERE id = ?";
        return DataUtil.getJdbcTemplate().update(sql, user.getName(), user.getSubject(), user.getId());
    }

    public int delete(int id) {
        String sql = "DELETE FROM userSubject WHERE id = ?";
        return DataUtil.getJdbcTemplate().update(sql, id);
    }
}

