package test;

import dao.impl.BookDaoImpl;
import org.junit.Test;
import pojo.Book;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author: cdw
 * @date: 2021/11/15 19:17
 * @description:
 */
public class BookDaoImplTest {
    private final BookDaoImpl dao =new BookDaoImpl();

    @Test
    public void addBook() {
        dao.addBook(new Book());
    }

    @Test
    public void deleteBookById() {
        dao.deleteBookById(21);
    }

    @Test
    public void updateBook() {
        dao.updateBook(new Book(20,"巴拉巴拉","cc",new BigDecimal("99.999"),1,99,"static/img/default.jpg"));
    }

    @Test
    public void queryBookById() {
        Book book = dao.queryBookById(17);
        System.out.println(book);
    }

    @Test
    public void queryBooks() {
        List<Book> list = dao.queryBooks();
        list.forEach(System.out::println);
    }
    @Test
    public void query(){
        Integer integer = dao.queryForPageTotalCount(101, 600);
        System.out.println(integer);
    }
}