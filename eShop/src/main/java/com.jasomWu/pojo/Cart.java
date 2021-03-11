package com.jasomWu.pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**购物车对象
 * @author sunwu
 * @create 2021-02-05-19:24
 */
public class Cart {
//    private Integer totalCount;
//    private BigDecimal totalPrice;
    private Map<Integer,CartItem> items = new LinkedHashMap<>();

    /**
     * 增加商品
     * @param cartItem
     */
   public void addCart(CartItem cartItem){
       CartItem item = items.get(cartItem.getId());

       if (item==null){   //没有此商品
           items.put(cartItem.getId(),cartItem);
       }else{      //已有此商品
           item.setCount(item.getCount()+1); //商品数量增加
           item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));//总价更新
       }

   }

    /**
     * 删除商品
     * @param id
     */
   public void deleteItem(Integer id){
       items.remove(id);

   }

    /**
     * 清空购物车
     */
   public void clear(){
       items.clear();
   }
    /**
     * 修改商品数量
     */
   public void updateCount(Integer id,Integer count){
       CartItem cartItem = items.get(id);

       if(cartItem!=null){
           cartItem.setCount(count);//修改数量
           cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));//总价更新

       }
   }


    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }

    public Integer getTotalCount() {
      Integer totalCount = 0;

       for (Map.Entry<Integer,CartItem>entry : items.entrySet()){
           totalCount+=entry.getValue().getCount();
       }

        return totalCount;
    }

    public BigDecimal getTotalPrice() {
       BigDecimal totalPrice = new BigDecimal(0);
        for (Map.Entry<Integer,CartItem>entry : items.entrySet()){
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }

        return totalPrice;
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }
}
