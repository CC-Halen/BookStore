package dao;

import pojo.Order;

import java.util.List;

/**
 * @author: cdw
 * @date: 2021/11/19 19:55
 * @description:
 */
public interface OrderDao {
    public int saveOrder(Order order);

    /**
     * 查询所有订单
     *
     * @return
     */
    public List<Order> queryOrders();

    public void changeOrderStatus(String order_id, Integer status);

    public List<Order> queryOrderByUserId(Integer userId);

    public Order queryOrderByOrder_id(String order_id);
}
