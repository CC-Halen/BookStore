package service.impl;

import dao.BookDao;
import dao.impl.BookDaoImpl;
import pojo.Book;
import pojo.Page;
import service.BookService;

import java.util.List;

/**
 * @author: cdw
 * @date: 2021/11/15 19:29
 * @description:
 */
public class BookServiceImpl implements BookService {
    private final BookDaoImpl bookDao = new BookDaoImpl();


    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(int id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(int id) {
        return bookDao.queryBookById(id);

    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> bookPage = new Page<>();
        //设置每页的记录数
        bookPage.setPageSize(pageSize);

        //查询当前的记录总数并设置
        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        bookPage.setPageTotalCount(pageTotalCount);

        //设置总页数
        int pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal += 1;
        }
        bookPage.setPageTotal(pageTotal);

        //设置当前页码
        bookPage.setPageNo(pageNo);

        //求当前页面数据的开始索引
        int begin = (bookPage.getPageNo() - 1) * pageSize;

        //设置当前页面的数据
        List<Book> books = bookDao.queryForPageItems(begin, pageSize);
        bookPage.setItems(books);

        return bookPage;

    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Book> bookPage = new Page<>();
        //设置每页的记录数量
        bookPage.setPageSize(pageSize);
        //查询价格区间里的记录总数并设置
        Integer pageTotalCount = bookDao.queryForPageTotalCount(min, max);
        bookPage.setPageTotalCount(pageTotalCount);

        //设置总页数
        int pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0)
            pageTotal += 1;
        bookPage.setPageTotal(pageTotal);

        //设置当前页码
        bookPage.setPageNo(pageNo);

        //求取当前页面数据的开始索引
        int begin = (bookPage.getPageNo() - 1) * pageSize;
        List<Book> books = bookDao.queryForPageItems(begin, pageSize, min, max);
        bookPage.setItems(books);

        return bookPage;

    }
}
