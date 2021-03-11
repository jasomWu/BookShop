package com.jasomWu.pojo;

import java.util.List;

/**
 *  页面列表的分页展示
 * @author sunwu
 * @create 2021-02-04-10:40
 */
public class Page<T> {
    public static final Integer PAGE_SIZE =4;    //页面的展示数量

    private Integer pageNo;     //当前页码
    private Integer PageSize=PAGE_SIZE;

    private Integer pageTotal;      //总页码

    private Integer pageTotalCount;     //总记录数

    private List<T> items;       //当前页数据

    private String url;     //分页条的请求地址

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", pageTotalCount=" + pageTotalCount +
                ", items=" + items +
                '}';
    }
    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {

//        /* 数据边界的有效检查 */
//        if (pageNo < 1) {
//            pageNo = 1;
//        }
//        if (pageNo > pageTotal) {
//            pageNo = pageTotal;
//        }

        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return PageSize;
    }

    public void setPageSize(Integer pageSize) {
        PageSize = pageSize;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
