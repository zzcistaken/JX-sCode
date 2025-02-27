package com.example.demo.repository;

import com.example.demo.entity.User;
import com.example.demo.util.DataUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    public List<User> findAll() {
        String sql = "SELECT * FROM users order by id";
        return DataUtil.getJdbcTemplate().query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public User findById(Long id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        return DataUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(User.class));
    }

    public int insert(User user) {
        String sql = "INSERT INTO users (name, email) VALUES (?, ?)";
        return DataUtil.getJdbcTemplate().update(sql, user.getName(), user.getEmail());
    }

    public int update(User user) {
        String sql = "UPDATE users SET name = ?, email = ? WHERE id = ?";
        return DataUtil.getJdbcTemplate().update(sql, user.getName(), user.getEmail(), user.getId());
    }

    public int delete(Long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        return DataUtil.getJdbcTemplate().update(sql, id);
    }
}

