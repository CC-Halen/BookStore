package test;

import org.junit.Test;
import pojo.Book;
import pojo.Page;
import service.BookService;
import service.impl.BookServiceImpl;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author: cdw
 * @date: 2021/11/15 19:33
 * @description:
 */
public class BookServiceImplTest {
    BookService bookService = new BookServiceImpl();

    @Test
    public void addBook() {
        bookService.addBook(new Book());
    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(22);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(20,"巴拉巴拉巴巴巴巴拉","cc",new BigDecimal("99.999"),1,99,"static/img/default.jpg"));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookService.queryBookById(5));
    }

    @Test
    public void queryBooks() {
        List<Book> list = bookService.queryBooks();
        list.forEach(System.out::println);
    }

    @Test
    public void page(){
        Page<Book> bookPage = bookService.page(2, 8);
        List<Book> items = bookPage.getItems();
        items.forEach(System.out::println);
    }
}