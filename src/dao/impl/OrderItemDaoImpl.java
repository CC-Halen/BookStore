package dao.impl;

import dao.OrderItemDao;
import pojo.OrderItem;

import java.util.List;

/**
 * @author: cdw
 * @date: 2021/11/19 20:14
 * @description:
 */
public class OrderItemDaoImpl extends BaseDao<OrderItem> implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(name,count,price,total_price,order_id) values(?,?,?,?,?)";
        return update(sql, orderItem.getName(), orderItem.getCount(), orderItem.getPrice(), orderItem.getTotal_price(), orderItem.getOrder_id());
    }

    @Override
    public List<OrderItem> queryOrderItemByOrder_id(String order_id) {
        String sql = "select * from t_order_item where order_id = ?";
        return queryForList(sql,order_id);
    }
}
