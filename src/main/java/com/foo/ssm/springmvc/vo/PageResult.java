package com.foo.ssm.springmvc.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * Created by zhaozheng.zhao on 2016/11/4.
 */
@SuppressWarnings("all")
public class PageResult<T> {
    private int total; // 总条数
    private int totalPage; // 总页数
    private int current; // 当前页数
    private int pageSize; // 页面显示条数
    private List<T> list;

    public static <E> PageResult<E> build(int totalCount, int totalPage, int currentPageNo, int pageSize,
            List<E> data) {
        return new PageResult<E>().setTotal(totalCount).setTotalPage(totalPage).setCurrent(currentPageNo)
                .setPageSize(pageSize).setList(data);
    }

    public int getTotal() {
        return total;
    }

    public PageResult<T> setTotal(int total) {
        this.total = total;
        return this;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public PageResult<T> setTotalPage(int totalPage) {
        this.totalPage = totalPage;
        return this;
    }

    public int getCurrent() {
        return current;
    }

    public PageResult<T> setCurrent(int current) {
        this.current = current;
        return this;
    }

    public int getPageSize() {
        return pageSize;
    }

    public PageResult<T> setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public List<T> getList() {
        return list;
    }

    public PageResult<T> setList(List<T> list) {
        this.list = list;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("total", total).append("totalPage", totalPage)
                .append("current", current).append("pageSize", pageSize).append("list", list).toString();
    }
}
