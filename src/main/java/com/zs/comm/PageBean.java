package com.zs.comm;

import java.util.List;

public class PageBean {
    private int start;
    private int pageSize;
    private long total;
    private List dates;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List getDates() {
        return dates;
    }

    public void setDates(List dates) {
        this.dates = dates;
    }
}
