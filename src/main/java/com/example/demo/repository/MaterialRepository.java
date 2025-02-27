package com.example.demo.repository;

import com.example.demo.entity.Material;
import com.example.demo.util.DataUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MaterialRepository {

    public List<Material> findAll() {
        String sql = "SELECT * FROM material";
        return DataUtil.getJdbcTemplate().query(sql, new BeanPropertyRowMapper<>(Material.class));
    }

    public List<Material> findAllByWuliaobh(int wuliaobh) {
        String sql = "SELECT * FROM material where wuliaobh = ?";
        return DataUtil.getJdbcTemplate().query(sql, new Object[]{wuliaobh}, new BeanPropertyRowMapper<>(Material.class));
    }

    public Material findById(Long id) {
        String sql = "SELECT * FROM material WHERE id = ?";
        return DataUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Material.class));
    }

    public int insert(Material material) {
        System.out.println("start insert Material");
        String sql = "INSERT INTO material (wuliaobh, wuliaolx, wuliaocd, beizhuxx) VALUES (?, ?,?,?)";
        return DataUtil.getJdbcTemplate().update(sql, material.getWuliaobh(), material.getWuliaolx(), material.getWuliaocd(), material.getBeizhuxx());
    }

//    public int update(Material material) {
//        String sql = "UPDATE material SET name = ?, email = ? WHERE id = ?";
//        return DataUtil.getJdbcTemplate().update(sql, material.getChanpdma(), material.getKschpmch(), material.getId());
//    }

    public int delete(Long id) {
        String sql = "DELETE FROM material WHERE id = ?";
        return DataUtil.getJdbcTemplate().update(sql, id);
    }
}

