package com.zs.controller;

import com.zs.config.Code;
import com.zs.comm.PageBean;
import com.zs.comm.Respond;
import com.zs.model.Order;
import com.zs.model.Statistics;
import com.zs.model.User;
import com.zs.server.OrderServer;
import com.zs.vo.OrderQuery;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderServer orderServer;

    @ResponseBody
    @GetMapping("/list")
    public Respond list(OrderQuery query) {
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        query.setFeid(user.getFeid());
        PageBean list = orderServer.list(query);
        return Respond.success(list);
    }

    @ResponseBody
    @GetMapping("/info/{id}")
    public Respond get(@PathVariable("id") String id) {
        Order order = orderServer.getByFno(id);
        if (order == null) {
            return Respond.error(Code.NO_EXITS);
        }
        return Respond.success(order);
    }

    @ResponseBody
    @GetMapping("/chart/{type}")
    public Respond chart(@PathVariable String type, String start, String end) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        if ("today".equals(type)) {
            end = start = dateFormat.format(calendar.getTime());
        } else if ("week".equals(type)) {
            calendar.setFirstDayOfWeek(Calendar.MONDAY);
            int dayWeek = calendar.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
            if (1 == dayWeek) {
                calendar.add(Calendar.DAY_OF_MONTH, -1);
            }
            int day = calendar.get(Calendar.DAY_OF_WEEK);
            calendar.add(Calendar.DATE, calendar.getFirstDayOfWeek() - day);
            start = dateFormat.format(calendar.getTime());
            calendar.add(Calendar.DATE, 6);
            end = dateFormat.format(calendar.getTime());
        } else if ("month".equals(type)) {
            calendar.set(Calendar.DATE, 1);
            start = dateFormat.format(calendar.getTime());
            calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            end = dateFormat.format(calendar.getTime());
        }
        if (StringUtils.isEmpty(start) || StringUtils.isEmpty(end)) {
            return Respond.error(Code.NO_EXITS);
        }
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        OrderQuery query = new OrderQuery();
        query.setFeid(user.getFeid());
        query.setFbegin(start);
        query.setFend(end);
        Statistics statistics = orderServer.custom(query);
        return Respond.success(statistics);
    }


    @GetMapping("/query/{payType}")
    @ResponseBody
    public Respond query(@PathVariable("payType") String payType, String fno) {
        int query = 0;
        try {
            query = orderServer.query(payType, fno);
        } catch (IOException e) {
            e.printStackTrace();
            query = -1;
        }
        if (query == 0) {
            return Respond.success("查询成功");
        } else {
            return Respond.error(Code.NO_EXITS);
        }
    }
}
