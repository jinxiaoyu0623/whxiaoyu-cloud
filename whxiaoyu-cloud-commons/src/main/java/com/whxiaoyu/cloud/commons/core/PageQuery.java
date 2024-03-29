package com.whxiaoyu.cloud.commons.core;

/**
 * 分页请求参数
 *
 * @author jinxiaoyu
 */
public class PageQuery extends Query {

    private static final long serialVersionUID = 598122879200050305L;

    public static final String ASC = "ASC";

    public static final String DESC = "DESC";

    private static final int DEFAULT_PAGE_SIZE = 10;

    private int pageNum = 1;

    private int pageSize = DEFAULT_PAGE_SIZE;

    private String orderBy;

    public int getPageNum() {
        if (pageNum < 1) {
            return 1;
        }
        return pageNum;
    }

    public PageQuery setPageNum(int pageNum) {
        this.pageNum = pageNum;
        return this;
    }

    public int getPageSize() {
        return pageSize;
    }

    public PageQuery setPageSize(int pageSize) {
        if (pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        this.pageSize = pageSize;
        return this;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
