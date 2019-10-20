package com.zs.model;

/**
 * 订单
 * 
 * @author Cjava
 */
public class Order {
	private String fid;// 主键

	private String feid;// 商户ID
	
	private String fsid;// 门店ID

	private String fno;// 订单号

	private String ftime;// 交易时间

	private String fname;// 商品名称

	private String fmoney;// 实收金额

	private String fremoney;// 退款金额

	private String fdid;// 支付产品类型（d_data主键）

	private int fstate;// 交易状态：0待收款，1收款中，2收款成功，3收款失败

	private String fbuyer;// 买家账号

	private String fpayno;// 交易号

	private String ftraceno;// 凭证号

	private String fpaytype;// 支付方式：0线下，1支付宝，2微信，3银联，4百度，5京东，6易宝

	private String feuser;// 营业员(t_user)主键

	private String famount;// 应收金额

	private String fctime;// 创建时间

	private String futime;// 修改时间

	private String fsname;

	private String fpname;// 支付方式

	private String fdname;// 支付产品

	private String fvname;// 会员姓名

	private String fmobile;

	private String frmoney;// 优惠额度

	private String frtype;// 卡券类型：1折扣券，2抵用券

	private String frdiscount;// 折扣

	private String fline;// 支付线路,1官方,2富友

	private String fdate;// 查询日期条件

	private String task;

	private String start;
	private String end;
	private String fresult;

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
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

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
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

	public String getFno() {
		return fno;
	}

	public void setFno(String fno) {
		this.fno = fno;
	}

	public String getFtime() {
		return ftime;
	}

	public void setFtime(String ftime) {
		this.ftime = ftime;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getFmoney() {
		return fmoney;
	}

	public void setFmoney(String fmoney) {
		this.fmoney = fmoney;
	}

	public String getFdid() {
		return fdid;
	}

	public void setFdid(String fdid) {
		this.fdid = fdid;
	}

	public int getFstate() {
		return fstate;
	}

	public void setFstate(int fstate) {
		this.fstate = fstate;
	}

	public String getFbuyer() {
		return fbuyer;
	}

	public void setFbuyer(String fbuyer) {
		this.fbuyer = fbuyer;
	}

	public String getFpayno() {
		return fpayno;
	}

	public void setFpayno(String fpayno) {
		this.fpayno = fpayno;
	}

	public String getFpaytype() {
		return fpaytype;
	}

	public void setFpaytype(String fpaytype) {
		this.fpaytype = fpaytype;
	}

	public String getFeuser() {
		return feuser;
	}

	public void setFeuser(String feuser) {
		this.feuser = feuser;
	}

	public String getFctime() {
		return fctime;
	}

	public void setFctime(String fctime) {
		this.fctime = fctime;
	}

	public String getFutime() {
		return futime;
	}

	public void setFutime(String futime) {
		this.futime = futime;
	}

	public String getFsname() {
		return fsname;
	}

	public void setFsname(String fsname) {
		this.fsname = fsname;
	}

	public String getFpname() {
		return fpname;
	}

	public void setFpname(String fpname) {
		this.fpname = fpname;
	}

	public String getFmobile() {
		return fmobile;
	}

	public void setFmobile(String fmobile) {
		this.fmobile = fmobile;
	}

	public String getFrmoney() {
		return frmoney;
	}

	public void setFrmoney(String frmoney) {
		this.frmoney = frmoney;
	}

	public String getFdname() {
		return fdname;
	}

	public void setFdname(String fdname) {
		this.fdname = fdname;
	}

	public String getFvname() {
		return fvname;
	}

	public void setFvname(String fvname) {
		this.fvname = fvname;
	}

	public String getFrtype() {
		return frtype;
	}

	public void setFrtype(String frtype) {
		this.frtype = frtype;
	}

	public String getFrdiscount() {
		return frdiscount;
	}

	public void setFrdiscount(String frdiscount) {
		this.frdiscount = frdiscount;
	}

	public String getFdate() {
		return fdate;
	}

	public void setFdate(String fdate) {
		this.fdate = fdate;
	}

	public String getFremoney() {
		return fremoney;
	}

	public void setFremoney(String fremoney) {
		this.fremoney = fremoney;
	}

	public String getFtraceno() {
		return ftraceno;
	}

	public void setFtraceno(String ftraceno) {
		this.ftraceno = ftraceno;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

    public String getFresult() {
        return fresult;
    }

    public void setFresult(String fresult) {
        this.fresult = fresult;
    }
}
