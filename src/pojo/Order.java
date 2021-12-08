package pojo;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * @author: cdw
 * @date: 2021/11/19 19:25
 * @description:
 */
public class Order {
    private String order_id;
    private Date create_time;
    private BigDecimal price;
    private Integer status=0;
    private int user_id;


    public Order(String order_id, Date createTime, BigDecimal price, Integer status, int userId) {
        this.order_id = order_id;
        this.create_time = createTime;
        this.price = price;
        this.status = status;
        this.user_id = userId;
    }

    public Order() {
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Order{" +
                "order_id='" + order_id + '\'' +
                ", createTime=" + create_time +
                ", price=" + price +
                ", status=" + status +
                ", userId=" + user_id +
                '}';
    }
}
