package com.example.demo2.shiro.config;

import com.example.demo2.domian.User;
import com.example.demo2.mapper.UserMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class MyShiroRealm extends AuthorizingRealm {
    @Autowired
    private UserMapper userMapper;

    /*认证*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("————身份认证方法————");

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        // 从数据库获取对应用户名密码的用户
        User user = userMapper.findByName(token.getUsername());
        if (user != null ) {
            String salt = user.getSalt();
            ByteSource saltBytes = ByteSource.Util.bytes(salt);
            //创建简单的认证信息对象
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user != null ? user : null, user != null ? user.getPassword() : null, saltBytes, getName());
            return simpleAuthenticationInfo;
        }
        return null;
    }

    /*授权*/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("Shiro授权··············");
        //简单授权
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        User user = null;
        // Manager manager = null;
        //捕获，不输出异常
        try {
            user = (User) principalCollection.getPrimaryPrincipal();
        } catch (Exception e) {
        }
        try {
            //manager = (Manager) principalCollection.getPrimaryPrincipal();
        } catch (Exception e) {
        }
        //构造权限
        List<String> list = new ArrayList<>();
        if (user != null) {//普通用户
            list.add("/user/**");
            simpleAuthorizationInfo.addRole("user");
            System.out.println("普通用户认证");
        }
        simpleAuthorizationInfo.addStringPermissions(list);
        return simpleAuthorizationInfo;
    }

}
