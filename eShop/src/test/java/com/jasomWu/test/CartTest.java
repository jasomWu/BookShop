package com.jasomWu.test;

import com.jasomWu.pojo.Cart;
import com.jasomWu.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author sunwu
 * @create 2021-02-05-21:08
 */
public class CartTest {

    @Test
    public void addCart() {
        Cart cart = new Cart();
        cart.addCart(new CartItem(1,"java",1,new BigDecimal(1000.99),new BigDecimal(1000.99)));
        cart.addCart(new CartItem(1,"java",1,new BigDecimal(1000.99),new BigDecimal(1000.99)));
        cart.addCart(new CartItem(2,"C",1,new BigDecimal(1277.99),new BigDecimal(1277.99)));
        System.out.println(cart);
    }

    @Test
    public void deleteCart() {
        Cart cart = new Cart();
        cart.addCart(new CartItem(1,"java",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addCart(new CartItem(1,"java",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addCart(new CartItem(2,"C",1,new BigDecimal(2000),new BigDecimal(2000)));
        cart.deleteItem(1);
        System.out.println(cart);

    }

    @Test
    public void clear() {
        Cart cart = new Cart();
        cart.addCart(new CartItem(1,"java",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addCart(new CartItem(1,"java",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addCart(new CartItem(2,"C",1,new BigDecimal(2000),new BigDecimal(2000)));
        cart.clear();
        System.out.println(cart);
    }

    @Test
    public void updateCount() {
        Cart cart = new Cart();
        cart.addCart(new CartItem(1,"java",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addCart(new CartItem(1,"java",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addCart(new CartItem(2,"C",1,new BigDecimal(2000),new BigDecimal(2000)));
        cart.updateCount(1,4);
        System.out.println(cart);
    }
}