package service.impl;

import dao.impl.BookDaoImpl;
import dao.impl.OrderDaoImpl;
import dao.impl.OrderItemDaoImpl;
import pojo.*;
import service.OrderService;
import utils.DateUtils;

import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: cdw
 * @date: 2021/11/19 21:48
 * @description:
 */
public class OrderServiceImpl implements OrderService {

    private final OrderDaoImpl orderDao = new OrderDaoImpl();
    private final OrderItemDaoImpl orderItemDao = new OrderItemDaoImpl();
    private final BookDaoImpl bookDao = new BookDaoImpl();


    @Override
    public String createOrder(Cart cart, Integer userId) {
        //订单号的唯一性
        String order_id = System.currentTimeMillis() + "" + userId;
        //创建一个订单对象
        Order order = new Order(order_id, new Date(System.currentTimeMillis()), cart.getTotalPrice(), 0, userId);
        //保存订单
        orderDao.saveOrder(order);


//        int i = 20/0;

        //遍历购物车中的每一件商品转换为订单项保存到数据库中
        for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()) {
            //获取每一个商品项并保存到数据库中
            CartItem item = entry.getValue();
            OrderItem orderItem = new OrderItem(null, item.getName(), item.getCount(), item.getPrice(), item.getTotalPrice(), order_id);
            orderItemDao.saveOrderItem(orderItem);

            //更新库存和销量
            Book book = bookDao.queryBookById(item.getId());
            book.setSales(book.getSales() + item.getCount());
            book.setStock(book.getStock() - item.getCount());
            bookDao.updateBook(book);
        }

        //生成订单之后清空购物车
        cart.clear();

        return order_id;
    }

    @Override
    public List<Order> showAllOrders() {
        return orderDao.queryOrders();
    }

    @Override
    public void sendOrder(String order_id) {
        orderDao.changeOrderStatus(order_id, 1);
    }

    @Override
    public List<OrderItem> showOrderDetail(String order_id) {
        return orderItemDao.queryOrderItemByOrder_id(order_id);
    }

    @Override
    public List<Order> showMyOrders(Integer userId) {
        return orderDao.queryOrderByUserId(userId);
    }

    @Override
    public void receiveOrder(String order_id) {
        orderDao.changeOrderStatus(order_id, 2);
    }
}
