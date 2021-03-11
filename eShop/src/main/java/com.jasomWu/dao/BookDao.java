package com.jasomWu.dao;

import com.jasomWu.pojo.Book;

import java.util.List;

/**
 * @author sunwu
 * @create 2021-02-03-21:46
 */
public interface BookDao {
    public int addBook(Book book);

    public int deleteBook(Integer id);

    public int updateBook(Book book);

    public Book queryBookById(Integer id);

    public List<Book> queryBooks();

    Integer queryForPageTotalCount();

    List<Book> queryForPageItems(int begin, int pageSize);

    Integer queryForPageTotalCountByPrice(int min, int max);

    List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max);
}
