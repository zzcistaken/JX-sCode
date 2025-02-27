package com.example.demo.repository;

import com.example.demo.entity.Product;
import com.example.demo.entity.ProductMaterial;
import com.example.demo.util.DataUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductMaterialRepository {

    public List<ProductMaterial> findAll() {
        String sql = "SELECT * FROM productMaterial";
        return DataUtil.getJdbcTemplate().query(sql, new BeanPropertyRowMapper<>(ProductMaterial.class));
    }

    public ProductMaterial findById(Long id) {
        String sql = "SELECT * FROM productMaterial WHERE id = ?";
        return DataUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(ProductMaterial.class));
    }

    public List<ProductMaterial> findByChanpdma(String chanpdma) {
        String sql = "SELECT * FROM productMaterial WHERE chanpdma = ?";
        return DataUtil.getJdbcTemplate().query(sql, new Object[]{chanpdma}, new BeanPropertyRowMapper<>(ProductMaterial.class));
    }

    public List<ProductMaterial> findByChanpdmaWuliaobh(String chanpdma, int wuliaobh) {
        String sql = "SELECT * FROM productMaterial WHERE chanpdma = ? and wuliaobh = ?";
        return DataUtil.getJdbcTemplate().query(sql, new Object[]{chanpdma, wuliaobh}, new BeanPropertyRowMapper<>(ProductMaterial.class));
    }


    public int insert(ProductMaterial product) {
        System.out.println("start insert productMaterial");
        String sql = "INSERT INTO ProductMaterial (chanpdma, chanpmch, wuliaobh, wuliaocd, wuliaosl, beizhuxx) VALUES (?, ?,?,?,?,?)";
        return DataUtil.getJdbcTemplate().update(sql, product.getChanpdma(), product.getChanpmch(), product.getWuliaobh(), product.getWuliaocd(),product.getWuliaosl(),product.getBeizhuxx());
    }

    public int update(ProductMaterial product) {
        String sql = "UPDATE ProductMaterial SET chanpdma = ?, chanpmch = ?, wuliaobh=?, wuliaocd=?,wuliaosl=?,beizhuxx=? WHERE id = ?";
        return DataUtil.getJdbcTemplate().update(sql, product.getChanpdma(),product.getChanpmch(), product.getWuliaobh(), product.getWuliaocd(),product.getWuliaosl(),product.getBeizhuxx(), product.getId());
    }

    public int delete(Long id) {
        String sql = "DELETE FROM ProductMaterial WHERE id = ?";
        return DataUtil.getJdbcTemplate().update(sql, id);
    }
}

