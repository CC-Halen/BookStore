package web;

import dao.impl.BookDaoImpl;
import pojo.Book;
import pojo.Cart;
import pojo.CartItem;
import service.BookService;
import service.impl.BookServiceImpl;
import utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: cdw
 * @date: 2021/11/19 16:36
 * @description:
 */
public class CartServlet extends BaseServlet {

    private final BookServiceImpl bookService = new BookServiceImpl();

    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        Book book = bookService.queryBookById(id);
        CartItem item = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());

        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(item);

        System.out.println(cart);

        //获取请求的地址，根据请求地址进行重定向
        String path = req.getHeader("Referer");
        resp.sendRedirect(path);

        req.getSession().setAttribute("lastName", item.getName());

    }


    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        Cart cart = (Cart) req.getSession().getAttribute("cart");

        if (cart != null)
            cart.deleteItem(id);

        resp.sendRedirect(req.getHeader("Referer"));

    }

    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null)
            cart.clear();
//        req.getRequestDispatcher("/pages/cart/cart.jsp").forward(req, resp); //效果一样
        resp.sendRedirect(req.getHeader("Referer"));
    }

    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        int count = WebUtils.parseInt(req.getParameter("count"), 1);

        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null)
            cart.updateCount(id, count);
//        req.getRequestDispatcher("/pages/cart/cart.jsp").forward(req, resp); //效果一样
        resp.sendRedirect(req.getHeader("Referer"));
    }

}
