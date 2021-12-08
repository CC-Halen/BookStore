package web;

import pojo.Book;
import pojo.Page;
import service.BookService;
import service.impl.BookServiceImpl;
import utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author: cdw
 * @date: 2021/11/18 16:02
 * @description:
 */
public class ClientBookServlet extends BaseServlet {

    private final BookService bookService = new BookServiceImpl();

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数 pageNo 和pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        //调用bookService的page方法生成每一页的数据并封装到Page对象中
        Page<Book> bookPage = bookService.page(pageNo, pageSize);
        bookPage.setUrl("client/bookServlet?action=page");

        //将Page对象保存到request域中
        req.setAttribute("page", bookPage);

        //请求转发到pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);


    }


    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数 pageNo 和pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebUtils.parseInt(req.getParameter("min"), 0);
        int max = WebUtils.parseInt(req.getParameter("max"), Integer.MAX_VALUE);

        //调用bookService的page方法生成每一页的数据并封装到Page对象中
        Page<Book> bookPage = bookService.pageByPrice(pageNo, pageSize, min, max);

        StringBuilder stringBuilder = new StringBuilder("client/bookServlet?action=pageByPrice");

        if (req.getParameter("min")!=null){
            stringBuilder.append("&min=").append(req.getParameter("min"));
        }

        if (req.getParameter("max")!=null){
            stringBuilder.append("&max=").append(req.getParameter("max"));
        }

        bookPage.setUrl(stringBuilder.toString());

        //将Page对象保存到request域中
        req.setAttribute("page", bookPage);

        //请求转发到pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);


    }
}
