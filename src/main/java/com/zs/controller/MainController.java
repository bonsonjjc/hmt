package com.zs.controller;

import com.zs.config.Code;
import com.zs.comm.Respond;
import com.zs.model.User;
import com.zs.server.OrderServer;
import com.zs.server.UserServer;
import com.zs.vo.OrderQuery;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private UserServer userService;

    @Autowired
    private OrderServer orderServer;

    @ResponseBody
    @PostMapping("/login")
    public Respond login(String faccount, String fpasswd) {
        Respond respond;
        AuthenticationToken token = new UsernamePasswordToken(faccount, fpasswd);
        try {
            SecurityUtils.getSubject().login(token);
            respond = Respond.success("登录成功");
        } catch (AuthenticationException e) {
            respond = Respond.error(Code.AUTH_FAIL);
        }
        return respond;
    }


    @ResponseBody
    @GetMapping("/login")
    public Respond noLogin() {
        return Respond.error(Code.NO_LOGIN);
    }

    @ResponseBody
    @GetMapping("/logout")
    public Respond logout() {
        SecurityUtils.getSubject().logout();
        return Respond.success("退出成功");
    }

    @ResponseBody
    @PostMapping("/password")
    public Respond password(String oldpwd, String newpwd) {
        int result = userService.password(oldpwd, newpwd);
        if (result == 1) {
            return Respond.success("密码修改成功");
        } else {
            return Respond.error(Code.AUTH_FAIL.getCode(), "旧密码错误");
        }
    }

    @GetMapping("/index")
    @ResponseBody
    public Respond index() {
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        String toDay = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        OrderQuery query = new OrderQuery();
        query.setFeid(user.getFeid());
        query.setFbegin(toDay);
        query.setFend(toDay);
        Map<String, Object> data = orderServer.toDay(query);
        return Respond.success(data);
    }

}
