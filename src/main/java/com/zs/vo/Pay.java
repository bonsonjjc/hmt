package com.zs.vo;

public class Pay {
    private String task;
    private String feid;
    private String fsid;
    private String fdid;
    private String feuser;
    private String fstoreid;
    private String fpaytype;
    private String fline;
    private String famount;
    private String fmoney;
    private String barcode;

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getFeid() {
        return feid;
    }

    public void setFeid(String feid) {
        this.feid = feid;
    }

    public String getFsid() {
        return fsid;
    }

    public void setFsid(String fsid) {
        this.fsid = fsid;
    }

    public String getFdid() {
        return fdid;
    }

    public void setFdid(String fdid) {
        this.fdid = fdid;
    }

    public String getFeuser() {
        return feuser;
    }

    public void setFeuser(String feuser) {
        this.feuser = feuser;
    }

    public String getFstoreid() {
        return fstoreid;
    }

    public void setFstoreid(String fstoreid) {
        this.fstoreid = fstoreid;
    }

    public String getFpaytype() {
        return fpaytype;
    }

    public void setFpaytype(String fpaytype) {
        this.fpaytype = fpaytype;
    }

    public String getFline() {
        return fline;
    }

    public void setFline(String fline) {
        this.fline = fline;
    }

    public String getFamount() {
        return famount;
    }

    public void setFamount(String famount) {
        this.famount = famount;
    }

    public String getFmoney() {
        return fmoney;
    }

    public void setFmoney(String fmoney) {
        this.fmoney = fmoney;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public static Pay barcode() {
        Pay pay = new Pay();
        return pay;
    }

    public static Pay qrcode() {
        Pay pay = new Pay();
        return pay;
    }

    public static Pay bankCard() {
        Pay pay = new Pay();
        return pay;
    }
}
