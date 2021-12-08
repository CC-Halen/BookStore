package web;

import pojo.Cart;
import pojo.Order;
import pojo.OrderItem;
import pojo.User;
import service.impl.OrderServiceImpl;
import utils.JdbcUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author: cdw
 * @date: 2021/11/20 13:21
 * @description:
 */
public class OrderServlet extends BaseServlet {
    private final OrderServiceImpl orderService = new OrderServiceImpl();

    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取购物车中的商品信息
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //获取用户的id
        User loginUser = (User) req.getSession().getAttribute("user");

        if (loginUser == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            return;
        }

        Integer userId = loginUser.getId();


        String order_id = orderService.createOrder(cart, userId);


        req.getSession().setAttribute("order_id", order_id);

        resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");

    }


    protected void showAllOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> orders = orderService.showAllOrders();
        req.getSession().setAttribute("allOrders", orders);
        resp.sendRedirect(req.getContextPath() + "/pages/order/order.jsp");
    }

    protected void showOrderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String order_id = (String) req.getSession().getAttribute("order_id");
        String create_time = (String) req.getSession().getAttribute("create_time");
        List<OrderItem> orderItems = orderService.showOrderDetail(order_id);
        req.getSession().setAttribute("orderItems", orderItems);
        req.getSession().setAttribute("create_time", create_time);
        req.getSession().setAttribute("order_id",order_id);
        resp.sendRedirect(req.getContextPath() + "/pages/manager/order_manager.jsp");
    }

    protected void sendOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String order_id = (String) req.getSession().getAttribute("order_id");
        orderService.sendOrder(order_id);
        String path = req.getHeader("Referer");
        resp.sendRedirect(path);
    }

}
