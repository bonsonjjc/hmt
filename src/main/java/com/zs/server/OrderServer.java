package com.zs.server;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zs.comm.*;
import com.zs.mapper.OrderMapper;
import com.zs.model.DayStatistics;
import com.zs.model.Order;
import com.zs.model.Statistics;
import com.zs.vo.OrderQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServer {
    @Autowired
    private OrderMapper orderMapper;

    public PageBean list(OrderQuery query) {
        PageBean pageBean = new PageBean();
        PageHelper.offsetPage(query.getStart(), query.getPageSize());
        List<Order> list = orderMapper.list(query);
        PageInfo<Order> pageInfo = new PageInfo<>(list);
        pageBean.setStart(query.getStart());
        pageBean.setPageSize(query.getPageSize());
        pageBean.setTotal(pageInfo.getTotal());
        pageBean.setDates(list);
        return pageBean;
    }

    public Order getById(String id) {
        return orderMapper.findByIdOrFno(id, "");
    }

    public Order getByFno(String fno) {
        return orderMapper.findByIdOrFno("", fno);
    }

    public String add(Order order) {
        orderMapper.add(order);
        return order.getFresult();
    }

    public int update(Order order) {
        return orderMapper.update(order);
    }


    public Map<String, Object> toDay(OrderQuery query) {
        int totalCount = 0;
        double totalMoney = 0;
        int aliCount = 0;
        double aliMoney = 0;
        int wxCount = 0;
        double wxMoney = 0;
        List<DayStatistics> list = orderMapper.statistics(query);
        for (DayStatistics dayStatistics : list) {
            if (dayStatistics.getFstate() != 2) {
                continue;
            }
            totalCount++;
            totalMoney += dayStatistics.getFmoney();
            if (dayStatistics.getFpaytype() == 1) {
                aliCount++;
                aliMoney += dayStatistics.getFmoney();
            }
            if (dayStatistics.getFpaytype() == 2) {
                wxCount++;
                wxMoney += dayStatistics.getFmoney();
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("totalMoney", totalMoney);
        map.put("totalCount", totalCount);
        map.put("aliCount", aliCount);
        map.put("aliMoney", aliMoney);
        map.put("wxCount", wxCount);
        map.put("wxMoney", wxMoney);
        return map;
    }

    public Statistics custom(OrderQuery query) {
        Statistics chat = new Statistics();
        List<DayStatistics> list = orderMapper.statistics(query);
        Map<String, Double> map = DateUtil.fillDate(query.getFbegin(), query.getFend());

        for (DayStatistics statistics : list) {
            double money = map.get(statistics.getFctime());

            chat.totalCount++;

            if (statistics.getFpaytype() == 1) {
                chat.aliMoney += statistics.getFmoney();
            } else if (statistics.getFpaytype() == 2) {
                chat.wxMoney += statistics.getFmoney();
            } else if (statistics.getFpaytype() == 4) {
                chat.bankMoney += statistics.getFmoney();
            } else if (statistics.getFpaytype() == 5) {
                chat.ysfMoney += statistics.getFmoney();
            }

            chat.totalMoney += statistics.getFmoney();
            if (statistics.getFstate() == 4) {
                if (statistics.getFmoney() != statistics.getFremoney()) {
                    double refundMoney = statistics.getFmoney() - statistics.getFremoney();
                    double serviceFee = refundMoney * statistics.getFdeduction();
                    System.out.println(refundMoney + "-----" + serviceFee);
                    chat.settlementMoney += refundMoney - serviceFee;
                    chat.refund += statistics.getFremoney();
                } else {
                    chat.refund += statistics.getFremoney();
                }
                chat.refundCount += 1;
            } else {
                chat.settlementMoney += statistics.getFnmoney();
            }
            money += statistics.getFmoney();
            map.put(statistics.getFctime(), money);
        }
        chat.chart = map;
        chat.totalMoney = NumberUtil.roundHalfUp(chat.totalMoney, 2);
        chat.settlementMoney = NumberUtil.roundHalfUp(chat.settlementMoney, 2);
        chat.refund = NumberUtil.roundHalfUp(chat.refund, 2);
        chat.aliMoney = NumberUtil.roundHalfUp(chat.aliMoney, 2);
        chat.wxMoney = NumberUtil.roundHalfUp(chat.wxMoney, 2);
        chat.bankMoney = NumberUtil.roundHalfUp(chat.bankMoney, 2);
        chat.ysfMoney = NumberUtil.roundHalfUp(chat.ysfMoney, 2);
        return chat;
    }


    public int query(String payType, String fno) throws IOException {
        Order order = orderMapper.findByIdOrFno("", fno);
        if (order == null) {
            return -1;
        }
        if ("barcode".equals(payType)) {
            Map<String, Object> map = new HashMap<>();
//            String result = HttpUtil.post("", map);
            String result = "";
            return 1;
        } else {
            return order.getFstate();
        }
    }
}

