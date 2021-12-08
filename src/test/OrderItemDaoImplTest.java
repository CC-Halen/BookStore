package test;

import dao.OrderItemDao;
import dao.impl.OrderItemDaoImpl;
import org.junit.Test;
import pojo.OrderItem;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author: cdw
 * @date: 2021/11/19 20:36
 * @description:
 */
public class OrderItemDaoImplTest {

    OrderItemDao orderItemDao = new OrderItemDaoImpl();
    @Test
    public void saveOrderItem() {


        //注意在输入测试数据时要保持外键的一致性才能成功测试
        orderItemDao.saveOrderItem(new OrderItem(0, "java 从入门到精通", 1, new BigDecimal(100), new
                BigDecimal(100), "11111"));
        orderItemDao.saveOrderItem(new OrderItem(2, "Netty 入门", 1, new BigDecimal(100), new
                BigDecimal(100), "000011"));
    }

    @Test
    public void queryOrderItemByOrder_id(){
        List<OrderItem> orderItems = orderItemDao.queryOrderItemByOrder_id("163741363827613");
        orderItems.forEach(System.out::println);
    }
}