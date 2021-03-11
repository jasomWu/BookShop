package com.jasomWu.service.impl;

import com.jasomWu.dao.BookDao;
import com.jasomWu.dao.impl.BookDaoImpl;
import com.jasomWu.pojo.Book;
import com.jasomWu.pojo.Page;
import com.jasomWu.service.BookService;

import java.util.List;

/**
 * @author sunwu
 * @create 2021-02-03-22:30
 */
public class BookServiceImpl implements BookService {
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBook(Integer id) {
        bookDao.deleteBook(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
         Page<Book> page = new Page<>();

         //设置当前页面
         page.setPageNo(pageNo);

         //设置显示数量
         page.setPageSize(pageSize);

         //总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCount();

        //总页码记录数
        page.setPageTotalCount(pageTotalCount);
        Integer pageTotal = pageTotalCount/pageSize+(pageTotalCount%pageSize!=0?1:0);

        //总页码
        page.setPageTotal(pageTotal);

        int begin =(page.getPageNo()-1)*page.getPageSize();
        //当前页数据
        List<Book> items = bookDao.queryForPageItems(begin,pageSize);
        //设置当前页数据
        page.setItems(items);

        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Book> page = new Page<>();

        //设置当前页面
        page.setPageNo(pageNo);

        //设置显示数量
        page.setPageSize(pageSize);

        //总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCountByPrice(min,max);

        //总页码记录数
        page.setPageTotalCount(pageTotalCount);
        Integer pageTotal = pageTotalCount/pageSize+(pageTotalCount%pageSize!=0?1:0);

        //总页码
        page.setPageTotal(pageTotal);

        int begin =(page.getPageNo()-1)*page.getPageSize();
        //当前页数据
        List<Book> items = bookDao.queryForPageItemsByPrice(begin,pageSize,min,max);
        //设置当前页数据
        page.setItems(items);

        return page;
    }
}
