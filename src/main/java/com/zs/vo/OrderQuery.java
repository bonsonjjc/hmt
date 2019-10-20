package com.zs.vo;

public class OrderQuery {
    private int start;
    private int pageSize;
    private String feid;
    private String feuser;
    private String fstate;
    private String fpaytype;
    private String fbegin;
    private String fend;


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

    public String getFeid() {
        return feid;
    }

    public void setFeid(String feid) {
        this.feid = feid;
    }

    public String getFeuser() {
        return feuser;
    }

    public void setFeuser(String feuser) {
        this.feuser = feuser;
    }

    public String getFstate() {
        return fstate;
    }

    public void setFstate(String fstate) {
        this.fstate = fstate;
    }

    public String getFpaytype() {
        return fpaytype;
    }

    public void setFpaytype(String fpaytype) {
        this.fpaytype = fpaytype;
    }

    public String getFbegin() {
        return fbegin;
    }

    public void setFbegin(String fbegin) {
        this.fbegin = fbegin;
    }

    public String getFend() {
        return fend;
    }

    public void setFend(String fend) {
        this.fend = fend;
    }
}
