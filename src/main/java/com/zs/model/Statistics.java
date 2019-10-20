package com.zs.model;

import java.util.Map;

public class Statistics {
    public double totalMoney = 0;
    public int totalCount = 0;
    public double settlementMoney = 0;
    public double refund = 0;
    public int refundCount = 0;
    public double aliMoney = 0;
    public double wxMoney = 0;
    public double bankMoney = 0;
    public double ysfMoney = 0;

    public Map<String,Double> chart;

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public double getSettlementMoney() {
        return settlementMoney;
    }

    public void setSettlementMoney(double settlementMoney) {
        this.settlementMoney = settlementMoney;
    }

    public double getRefund() {
        return refund;
    }

    public void setRefund(double refund) {
        this.refund = refund;
    }

    public int getRefundCount() {
        return refundCount;
    }

    public void setRefundCount(int refundCount) {
        this.refundCount = refundCount;
    }

    public double getAliMoney() {
        return aliMoney;
    }

    public void setAliMoney(double aliMoney) {
        this.aliMoney = aliMoney;
    }

    public double getWxMoney() {
        return wxMoney;
    }

    public void setWxMoney(double wxMoney) {
        this.wxMoney = wxMoney;
    }

    public double getBankMoney() {
        return bankMoney;
    }

    public void setBankMoney(double bankMoney) {
        this.bankMoney = bankMoney;
    }

    public double getYsfMoney() {
        return ysfMoney;
    }

    public void setYsfMoney(double ysfMoney) {
        this.ysfMoney = ysfMoney;
    }

    public Map<String, Double> getChart() {
        return chart;
    }

    public void setChart(Map<String, Double> chart) {
        this.chart = chart;
    }
}
