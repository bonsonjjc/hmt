package com.zs.shiro;

import com.zs.model.User;
import com.zs.server.UserServer;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginAuthenticationRealm extends AuthorizingRealm {
    @Autowired
    private UserServer userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String password = String.valueOf(token.getPassword());
        User user = new User();
        user.setFaccount(token.getUsername());
        user.setFpasswd(password);
        User login = userService.login(user);
        if (login == null) {
            return null;
        }
        SecurityUtils.getSubject().getSession().setAttribute("user", login);
        return new SimpleAuthenticationInfo(token.getUsername(), password, "login");
    }
}
