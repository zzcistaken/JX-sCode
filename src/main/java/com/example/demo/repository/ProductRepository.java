package com.example.demo.repository;

import com.example.demo.entity.Product;
import com.example.demo.util.DataUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {

    public List<Product> findAll() {
        String sql = "SELECT * FROM products";
        return DataUtil.getJdbcTemplate().query(sql, new BeanPropertyRowMapper<>(Product.class));
    }

    public List<Product> findAllByChanpdma(String chanpdma) {
        String sql = "SELECT * FROM products where chanpdma = ?";
        return DataUtil.getJdbcTemplate().query(sql, new Object[]{chanpdma}, new BeanPropertyRowMapper<>(Product.class));
    }
    public Product findOneByChanpdma(String chanpdma) {
        String sql = "SELECT * FROM products where chanpdma = ?";
        return DataUtil.getJdbcTemplate().queryForObject(sql, new Object[]{chanpdma}, new BeanPropertyRowMapper<>(Product.class));
    }


    public Product findById(Long id) {
        String sql = "SELECT * FROM products WHERE id = ?";
        return DataUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Product.class));
    }

    public int insert(Product product) {
        System.out.println("start insert Product");
        String sql = "INSERT INTO products (chanpdma, chanpmch) VALUES (?, ?)";
        return DataUtil.getJdbcTemplate().update(sql, product.getChanpdma(), product.getChanpmch());
    }

    public int update(Product product) {
        String sql = "UPDATE products SET chanpdma = ?, chanpmch = ? WHERE id = ?";
        return DataUtil.getJdbcTemplate().update(sql, product.getChanpdma(), product.getId());
    }

    public int delete(Long id) {
        String sql = "DELETE FROM products WHERE id = ?";
        return DataUtil.getJdbcTemplate().update(sql, id);
    }
}

