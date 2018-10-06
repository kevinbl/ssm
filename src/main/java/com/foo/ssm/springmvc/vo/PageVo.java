package com.foo.ssm.springmvc.vo;

import com.google.common.base.Preconditions;

import java.util.List;

/**
 * created by foolish on 16-11-16.
 */
public class PageVo<T> {
    private String info;
    private int currentPage;
    private int pageCount;
    private int pageSize;
    private int recordCount;
    private int start = -1;
    private List<T> recordList;

    public PageVo(int currentPage, int pageSize, int recordCount, List<T> recordList) {
        Preconditions.checkArgument(currentPage != 0, "页码不能为零");
        Preconditions.checkArgument(pageSize != 0, "每页大小不能为零");
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.recordCount = recordCount;
        this.pageCount = (recordCount + pageSize - 1) / pageSize;
        this.recordList = recordList;
    }

    public PageVo(String info, int currentPage, int pageSize, int recordCount, List<T> recordList) {
        Preconditions.checkArgument(currentPage != 0, "页码不能为零");
        Preconditions.checkArgument(pageSize != 0, "每页大小不能为零");
        this.info = info;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.recordCount = recordCount;
        this.pageCount = (recordCount + pageSize - 1) / pageSize;
        this.recordList = recordList;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public List<T> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<T> recordList) {
        this.recordList = recordList;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }
}
