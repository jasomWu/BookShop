package com.jasomWu.test;

import com.jasomWu.dao.BookDao;
import com.jasomWu.dao.impl.BookDaoImpl;
import com.jasomWu.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author sunwu
 * @create 2021-02-03-22:10
 */
public class BookDaoTest {
    private BookDao bookDao = new BookDaoImpl();

    @Test
    public void addBook() {
        bookDao.addBook(new Book(0,"天龙八部","金庸",new BigDecimal(99.99),1300,1000,null));
    }

    @Test
    public void deleteBook() {
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(20,"人间神话","未知",new BigDecimal(88.88),400,1000,null));
    }

    @Test
    public void queryBookById() {
        Book book = bookDao.queryBookById(21);
        System.out.println(book);
    }

    @Test
    public void queryBook() {
        List<Book> books = bookDao.queryBooks();
        books.forEach(System.out::println);
    }
    @Test
    public void queryForPageTotalCount() {
        System.out.println(bookDao.queryForPageTotalCount());
    }

    @Test
    public void  queryForPageItems() {
        List<Book> list = bookDao.queryForPageItems(8, 4);
        list.forEach(System.out::println);
    }
    @Test
    public void queryForPageTotalCountByPrice() {
        Integer integer = bookDao.queryForPageTotalCountByPrice(21, 99);
        System.out.println(integer);
    }

    @Test
    public void  queryForPageItemsByPrice() {
        List<Book> list = bookDao.queryForPageItemsByPrice(0, 4, 10, 50);
        list.forEach(System.out::println);

    }
}