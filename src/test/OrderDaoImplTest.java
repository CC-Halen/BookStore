package test;

import dao.impl.OrderDaoImpl;
import org.junit.Test;
import pojo.Order;
import utils.DateUtils;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author: cdw
 * @date: 2021/11/20 14:10
 * @description:
 */
public class OrderDaoImplTest {
    private final OrderDaoImpl dao = new OrderDaoImpl();


    @Test
    public void saveOrder() {

        dao.saveOrder(new Order("2545456", new Date(System.currentTimeMillis()), new BigDecimal(10), 0, 1));
    }

    @Test
    public void queryOrders() {
        List<Order> orders = dao.queryOrders();
        orders.forEach(System.out::println);
    }

    @Test
    public void changeOrderStatus() {
        dao.changeOrderStatus("11111",2);
    }

    @Test
    public void queryOrderByUserId() {
        List<Order> orders = dao.queryOrderByUserId(13);
        orders.forEach(System.out::println);
    }

    @Test
    public void queryOrderByOrder_id(){
        Order order = dao.queryOrderByOrder_id("163741363827613");
        System.out.println(order);
    }
}