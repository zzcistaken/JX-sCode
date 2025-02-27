package com.example.demo.repository;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderSimple;
import com.example.demo.util.DataUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepository {

    public List<Order> findAllOrders() {
        String sql = "SELECT * FROM orders";
        return DataUtil.getJdbcTemplate().query(sql, new BeanPropertyRowMapper<>(Order.class));
    }

    public List<OrderSimple> findAllOrderSimple() {
        String sql = "SELECT * FROM orderSimple order by dingdanh";
        return DataUtil.getJdbcTemplate().query(sql, new BeanPropertyRowMapper<>(OrderSimple.class));
    }

    public List<Order> findByDingdanh(String dingdanh) {
        String sql = "SELECT * FROM orders where dingdanh = ? ";
        return DataUtil.getJdbcTemplate().query(sql, new Object[]{dingdanh}, new BeanPropertyRowMapper<>(Order.class));
    }

    public List<OrderSimple> findOrderSimpleByDingdanh(String dingdanh) {
        String sql = "SELECT * FROM orderSimple where dingdanh = ?";
        return DataUtil.getJdbcTemplate().query(sql, new Object[]{dingdanh}, new BeanPropertyRowMapper<>(OrderSimple.class));
    }

    public List<Order> findOneByDingdanhChanpdma(String dingdanh, String chanpdma) {
        String sql = "SELECT * FROM orders where dingdanh = ? and chanpdma = ?";
        return DataUtil.getJdbcTemplate().query(sql, new Object[]{dingdanh,chanpdma}, new BeanPropertyRowMapper<>(Order.class));
    }

    public Order findById(Long id) {
        String sql = "SELECT * FROM orders WHERE id = ?";
        return DataUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Order.class));
    }

    public int insert(Order order) {
        System.out.println("start insert Order");
        String sql = "INSERT INTO orders (dingdanh,dingdanm, chanpdma,chanpmch,chanpshl) VALUES (?, ?,?,?,?)";
        return DataUtil.getJdbcTemplate().update(sql, order.getDingdanh(),order.getDingdanm(),order.getChanpdma(), order.getChanpmch(), order.getChanpshl());
    }

    public int insert(OrderSimple order) {
        System.out.println("start insert OrderSimple");
        String sql = "INSERT INTO orderSimple (dingdanh,dingdanm,beizhuxx) VALUES (?, ?,?)";
        return DataUtil.getJdbcTemplate().update(sql, order.getDingdanh(),order.getDingdanm(),order.getBeizhuxx());
    }

    public int update(Order order) {
        String sql = "UPDATE orders SET chanpdma = ?, chanpshl = ? WHERE id = ?";
        return DataUtil.getJdbcTemplate().update(sql, order.getChanpdma(), order.getChanpshl(), order.getId());
    }

    public int delete(Long id) {
        String sql = "DELETE FROM orders WHERE id = ?";
        return DataUtil.getJdbcTemplate().update(sql, id);
    }
}

