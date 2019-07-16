package com.example.demo2.shiro.config;

import com.example.demo2.domian.User;
import com.example.demo2.mapper.UserMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;


public class MyShiroRealm extends  AuthorizingRealm{
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
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user , user.getPassword(), saltBytes, getName());
            return simpleAuthenticationInfo;
        }
        return null;
    }

    /*授权*/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("Shiro授权··············");
        //简单授权
        User user = (User) principalCollection.getPrimaryPrincipal();
        System.out.println(user.toString());
        //组装返回数据
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        if (user.getType()==2){
            //组装返回数据
            simpleAuthorizationInfo.addRole(user.getType()+"");
        }else  if (user.getType()==1){
            simpleAuthorizationInfo.addRole(user.getType()+"");
        }else if(user.getType()==0){
            simpleAuthorizationInfo.addRole(user.getType()+"");
        }
        return simpleAuthorizationInfo;
    }

}
