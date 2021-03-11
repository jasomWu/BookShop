package com.jasomWu.test;

import com.jasomWu.pojo.Book;
import com.jasomWu.pojo.Page;
import com.jasomWu.service.BookService;
import com.jasomWu.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author sunwu
 * @create 2021-02-03-22:36
 */
public class BookServiceTest {
    private BookService service = new BookServiceImpl();

    @Test
    public void addBook() {
        service.addBook(new Book(21,"天龙八部","金庸",new BigDecimal(99.99),1990,2000,null));
    }

    @Test
    public void deleteBook() {
        service.deleteBook(21);
    }

    @Test
    public void updateBook() {
        service.updateBook(new Book(22,"悟哥大佬","大佬",new BigDecimal(99.99),1300,1300,null));
    }

    @Test
    public void queryBookById() {
        System.out.println(service.queryBookById(22));
    }

    @Test
    public void queryBooks() {
        List<Book> books = service.queryBooks();
        books.forEach(System.out::println);
    }
    @Test
    public void page(){
        System.out.println(service.page(1, 4));
    }
    @Test
    public void pageByPrice(){
        Page<Book> page = service.pageByPrice(1, 4, 10, 50);
        System.out.println(page);
    }
}