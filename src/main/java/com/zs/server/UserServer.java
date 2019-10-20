package com.zs.server;

import com.zs.mapper.UserMapper;
import com.zs.model.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServer {
    @Autowired
    private UserMapper userMapper;

    public User login(User user) {
        User user1 = userMapper.find(user.getFaccount(), user.getFpasswd());
        return user1;
    }

    public int password(String oldPassword, String newPassword) {
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        return userMapper.password(user.getFid(), oldPassword, newPassword);
    }
}
