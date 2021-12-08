package dao;

import pojo.Book;

import java.security.PrivateKey;
import java.util.List;

/**
 * @author: cdw
 * @date: 2021/11/15 18:36
 * @description:
 */
public interface BookDao {
    /**
     * 添加图书
     *
     * @param book
     * @return
     */
    public int addBook(Book book);

    /**
     * 通过id删除图书
     *
     * @param id
     * @return
     */
    public int deleteBookById(int id);

    /**
     * 更新图书
     *
     * @param book
     * @return
     */
    public int updateBook(Book book);

    /**
     * 查询图书
     * @param id
     * @return
     */
    public Book queryBookById(int id);

    /**
     * 查询所有的图书
     *
     * @return
     */
    public List<Book> queryBooks();

    /**
     * 查询总记录数
     * @return
     */
    public Integer queryForPageTotalCount();


    /**
     * 根据价格区间查询总的记录数
     * @param min
     * @param max
     * @return
     */
    public Integer queryForPageTotalCount(int min, int max);

    /**
     * 查询页面中的数据
     * @param begin
     * @param pageSize
     * @return
     */
    public List<Book> queryForPageItems(int begin,int pageSize);


    /**
     * 价格检索
     * @param begin
     * @param pageSize
     * @param min
     * @param max
     * @return
     */
    public List<Book> queryForPageItems(int begin, int pageSize, int min, int max);
}
