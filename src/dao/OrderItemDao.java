package dao;

import pojo.OrderItem;

import java.util.List;

/**
 * @author: cdw
 * @date: 2021/11/19 20:12
 * @description:
 */
public interface OrderItemDao {
    public int saveOrderItem(OrderItem orderItem);

    public List<OrderItem> queryOrderItemByOrder_id(String order_id);
}
