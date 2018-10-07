package com.foo.ssm.springmvc.vo;

import org.apache.ibatis.session.RowBounds;

import java.io.Serializable;

/**
 * created by f on 16-10-10.
 */
public class PageParam implements Serializable {

    private static final long serialVersionUID = -6158600285706074541L;

    private int currentPage;

    private int pageSize;

    public RowBounds toRowBounds() {
        return new RowBounds((currentPage - 1) * pageSize, pageSize);
    }

    public PageParam() {

    }

    public PageParam(int currentPage, int pageSize) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "PageParam{" + "currentPage=" + currentPage + ", pageSize=" + pageSize + '}';
    }

}