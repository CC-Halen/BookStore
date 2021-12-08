package test;

import org.junit.Test;
import pojo.Cart;
import pojo.CartItem;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author: cdw
 * @date: 2021/11/19 16:17
 * @description:
 */
public class CartTest {
    private final Cart cart = new Cart();


    public void showItems(){
        Map<Integer, CartItem> items = cart.getItems();
        for (Map.Entry<Integer,CartItem> entry : items.entrySet())
            System.out.println(entry);
    }

    public void addItem() {
        cart.addItem(new CartItem(1, "碳烤牛肉", 1, new BigDecimal(99), new BigDecimal(99)));
        cart.addItem(new CartItem(2, "碳烤猪肉", 1, new BigDecimal(101), new BigDecimal(101)));

    }

    @Test
    public void deleteItem() {

        addItem();
        System.out.println(cart.getTotalCount());
        cart.deleteItem(2);
        System.out.println(cart.getTotalCount());
    }

    @Test
    public void clear() {
        cart.addItem(new CartItem(1, "碳烤牛肉", 1, new BigDecimal(99), new BigDecimal(99)));
        cart.addItem(new CartItem(2, "碳烤猪肉", 1, new BigDecimal(101), new BigDecimal(101)));

        System.out.println(cart.getTotalCount());
        cart.clear();
        System.out.println(cart.getTotalCount());
    }

    @Test
    public void updateCount() {
        addItem();
        showItems();
        System.out.println(cart.getTotalPrice());
        cart.updateCount(1,90);
        System.out.println(cart.getTotalPrice());
        showItems();

    }

    @Test
    public void getTotalCount() {
        addItem();
        System.out.println(cart.getTotalCount());
    }

    @Test
    public void getTotalPrice() {
        addItem();
        System.out.println(cart.getTotalPrice());
    }

    @Test
    public void getItems() {
        addItem();
        showItems();
    }

    @Test
    public void setItems() {
    }

    @Test
    public void testToString() {
        addItem();
        System.out.println(cart);
    }
}