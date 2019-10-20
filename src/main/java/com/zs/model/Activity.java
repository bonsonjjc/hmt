package com.zs.model;

/**
 * 活动详情对象（参加活动）
 *
 * @author yex
 */
public class Activity {
    private String fid;
    private String fpic; // 宣传海报
    private String ftitle; // 标题
    private String ftext; // 内容
    private String fbegin; // 开始时间
    private String fend; // 结束时间
    private String fclass; // 分类类型
    private String fctime; // 发布时间
    private String active; //是否参加过

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getFpic() {
        return fpic;
    }

    public void setFpic(String fpic) {
        this.fpic = fpic;
    }

    public String getFtitle() {
        return ftitle;
    }

    public void setFtitle(String ftitle) {
        this.ftitle = ftitle;
    }

    public String getFtext() {
        return ftext;
    }

    public void setFtext(String ftext) {
        this.ftext = ftext;
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

    public String getFctime() {
        return fctime;
    }

    public void setFctime(String fctime) {
        this.fctime = fctime;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getFclass() {
        return fclass;
    }

    public void setFclass(String fclass) {
        this.fclass = fclass;
    }
}
