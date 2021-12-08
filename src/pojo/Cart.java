package pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author: cdw
 * @date: 2021/11/19 15:41
 * @description:
 */
public class Cart {

    //key是商品的id，value是商品的具体信息
    private Map<Integer, CartItem> items = new LinkedHashMap<Integer, CartItem>();

    /**
     * 添加商品
     *
     * @param cartItem
     */
    public void addItem(CartItem cartItem) {
        //先检查购物车中是否已经添加了商品，若添加就增加数量和总金额，若没有就放到集合中就行
        CartItem item = items.get(cartItem.getId());

        if (item == null) {
            //说明没添加过商品
            items.put(cartItem.getId(), cartItem);
        } else {
            item.setCount(item.getCount() + 1);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }

    /**
     * 删除商品
     *
     * @param id
     */
    public void deleteItem(Integer id) {
        items.remove(id);
    }


    /**
     * 清空购物车
     */
    public void clear() {
        items.clear();
    }


    /**
     * 修改商品数量
     *
     * @param id
     * @param count
     */
    public void updateCount(Integer id, int count) {
        if (count == 0) {
            items.remove(id);
        }else {
            CartItem item = items.get(id);
            if (item != null) {
                item.setCount(count);
                item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
            }
        }
    }


    public Integer getTotalCount() {
        Integer totalCount = 0;
        for (Map.Entry<Integer, CartItem> entry : items.entrySet())
            totalCount += entry.getValue().getCount();
        return totalCount;

    }


    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);

        for (Map.Entry<Integer, CartItem> entry : items.entrySet())
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());

        return totalPrice;
    }


    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                " totalCount=" + getTotalCount() +
                " totalPrice=" + getTotalPrice() +
                " items=" + items +
                '}';
    }
}
