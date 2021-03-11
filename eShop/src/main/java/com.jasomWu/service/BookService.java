package com.jasomWu.service;

import com.jasomWu.pojo.Book;
import com.jasomWu.pojo.Page;

import java.util.List;

/**
 * @author sunwu
 * @create 2021-02-03-22:25
 */
public interface BookService {
    public void addBook(Book book);

    public void deleteBook(Integer id);

    public void updateBook(Book book);

    public Book queryBookById(Integer id);

    public List<Book> queryBooks();


    Page<Book> page(int pageNo, int pageSize);

    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
