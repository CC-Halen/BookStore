package service;

import pojo.Cart;
import pojo.Order;
import pojo.OrderItem;

import java.util.List;

/**
 * @author: cdw
 * @date: 2021/11/19 21:48
 * @description:
 */
public interface OrderService {
    public String createOrder(Cart cart,Integer userId);

    public List<Order> showAllOrders();

    public void sendOrder(String order_id);

    public List<OrderItem> showOrderDetail(String order_id);

    public List<Order> showMyOrders(Integer userId);

    public void receiveOrder(String order_id);
}
