package service;

import pojo.Book;
import pojo.Page;

import java.util.List;

/**
 * @author: cdw
 * @date: 2021/11/15 19:25
 * @description:
 */
public interface BookService {
    public void addBook(Book book);

    public void deleteBookById(int id);

    public void updateBook(Book book);

    public Book queryBookById(int id);

    public List<Book> queryBooks();

    public Page<Book> page(int pageNo, int pageSize);

    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
