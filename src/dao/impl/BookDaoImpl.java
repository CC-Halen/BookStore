package dao.impl;

import dao.BookDao;
import pojo.Book;

import java.util.List;

/**
 * @author: cdw
 * @date: 2021/11/15 18:41
 * @description:
 */
public class BookDaoImpl extends BaseDao<Book> implements BookDao {
    @Override
    public int addBook(Book book) {
        String sql = "insert into t_book(name,author,price,sales,stock,img_path) values(?,?,?,?,?,?)";
        return update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath());
    }

    @Override
    public int deleteBookById(int id) {
        String sql = "delete from t_book where id = ?";
        return update(sql, id);
    }

    @Override
    public int updateBook(Book book) {
        String sql = "update t_book set name=?,author=?,price=?,sales=?,stock=?,img_path=? where id =?";
        return update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath(), book.getId());
    }

    @Override
    public Book queryBookById(int id) {
        String sql = "select * from t_book where id =?";
        return queryForOne(sql, id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql = "select * from t_book";
        return queryForList(sql);
    }

    @Override
    public Integer queryForPageTotalCount() {
        String sql = "select count(*) from t_book";
        Long count = (Long) queryForSingleValue(sql);
        return count.intValue();
    }


    @Override
    public Integer queryForPageTotalCount(int min, int max) {
        String sql = "select count(*) from t_book where price between ? and ?";
        Long count = (Long) queryForSingleValue(sql, min, max);
        return count.intValue();
    }

    @Override
    public List<Book> queryForPageItems(int begin, int pageSize) {
        String sql = "select id,name,author,price,sales,stock,img_path from t_book limit ?,?";
        return queryForList(sql, begin, pageSize);
    }

    @Override
    public List<Book> queryForPageItems(int begin, int pageSize, int min, int max) {
        String sql = "select id,name,author,price,sales,stock,img_path from t_book where price between ? and ? order by price limit ?,?";
        return queryForList(sql, min, max, begin, pageSize);
    }
}
