package dao.impl;

import dao.OrderDao;
import pojo.Order;

import java.util.List;

/**
 * @author: cdw
 * @date: 2021/11/19 19:56
 * @description:km kjnkjnkjnkadzi
 */
public class OrderDaoImpl extends BaseDao<Order> implements OrderDao {
    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(order_id,create_time,price,status,user_id) values(?,?,?,?,?)";
        return update(sql, order.getOrder_id(), order.getCreate_time(), order.getPrice(), order.getStatus(), order.getUser_id());
    }

    @Override
    public List<Order> queryOrders() {
        String sql = "select * from t_order";
        return queryForList(sql);
    }

    @Override
    public void changeOrderStatus(String order_id, Integer status) {
        String sql = "update t_order set status=? where order_id = ?";
        update(sql, status, order_id);
    }

    @Override
    public List<Order> queryOrderByUserId(Integer userId) {
        String sql = "select order_id,create_time,price,status,user_id from t_order where user_id = ?";
        return queryForList(sql, userId);
    }

    @Override
    public Order queryOrderByOrder_id(String order_id) {
        String sql = "select order_id,create_time,price,status,user_id from t_order where order_id = ?";
        return queryForOne(sql,order_id);
    }
}
