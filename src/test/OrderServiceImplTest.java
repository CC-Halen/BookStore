package test;

import dao.impl.OrderItemDaoImpl;
import org.junit.Test;
import pojo.Cart;
import pojo.CartItem;
import service.impl.OrderServiceImpl;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author: cdw
 * @date: 2021/11/19 22:21
 * @description:
 */
public class OrderServiceImplTest {

    @Test
    public void createOrder() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "java 从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1, "java 从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "数据结构与算法", 1, new BigDecimal(100),new BigDecimal(100)));

        OrderServiceImpl orderService = new OrderServiceImpl();
        System.out.println("订单号是："+orderService.createOrder(cart,8));
    }
}