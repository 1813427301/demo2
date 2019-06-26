package com.example.demo2.service.impl;

import com.example.demo2.domian.User;
import com.example.demo2.mapper.UserMapper;
import com.example.demo2.service.UserService;
import com.example.demo2.shiro.md5.ShiroUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.soap.Text;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private User user;

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public User findByid(Long id) {
       user = userMapper.findById(id);
        return user;
    }


    @Override
    public Map<String, Object> create(String username, String password, String password2, String email,String city) {
        Map<String, Object> map = new HashMap<>();
        map.put("ok", false);
        if (password.equals(password2)) {
            User user1 = userMapper.findByName(username);
            if (user1 == null) {
                user.setUsername(username);
                //加盐加密
                String salt = ShiroUtil.createSalt();
                String passwordBySalt = ShiroUtil.createPwdBySalt(password, salt);
                user.setPassword(passwordBySalt);
                user.setSalt(salt);
                user.setEmail(email);
                user.setUrlHead("img/3.png");
                user.setSex("保密");
                user.setStatus(1);
                user.setType(Integer.parseInt(city)==0?0:1);
                user.setCreateTime(new Timestamp(new Date().getTime()));
                try {
                    userMapper.create(user);
                } catch (Exception e) {
                    map.put("error", "插入数据报错，创建失败！");
                }
                map.put("ok", true);
                map.put("error", "创建成功！");
            } else {
                map.put("error", "用户已被创建！");
            }

        }
        return map;
    }

    @Override
    public Map<String, Object> login(String username, String password) {
        System.out.println(username+":"+password);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ok", false);
        //使用shiro的认证
        Subject subject = SecurityUtils.getSubject();
        //创建令牌
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
            map.put("ok", true);
            //从subject中获取当前登录用户的对象
            User user = (User) subject.getPrincipal();
            map.put("user", user);
        } catch ( IncorrectCredentialsException e) {
            map.put("error", "登录失败！密码错误！");
        } catch (AuthenticationException e) {
            map.put("error", "登录失败！用户不存在！");
        } catch (Exception e) {
            map.put("error", "登录失败！内部错误！");
        }
        return map;
    }

    @Override
    public Map<String, Object>  updateUser(Long id,String username, String password, String password2, String email, String city,String date) {
        Map<String, Object> map = new HashMap<>();
        map.put("ok", false);
        if (password.equals(password2)) {
            User user1 = userMapper.findById(id);
            user.setUsername(username);
            //加盐加密
            String salt = ShiroUtil.createSalt();
            String passwordBySalt = ShiroUtil.createPwdBySalt(password, salt);
            user.setPassword(passwordBySalt);
            user.setSalt(salt);
            user.setEmail(email);
            user.setUrlHead("img/3.png");
            user.setSex("保密");
            user.setStatus(1);
            user.setType(Integer.parseInt(city)==0?0:1);
            user.setCreateTime(Timestamp.valueOf(date));
            if (user1 == null) {
                try {
                    userMapper.create(user);
                } catch (Exception e) {
                    map.put("error", "插入数据报错，创建失败！");
                }
                map.put("ok", true);
                map.put("error", "创建成功！");
            } else {
                try {
                    userMapper.update(user);
                    map.put("ok", true);
                    map.put("error", "用户修改成功！");
                }catch (Exception e){
                    map.put("error", "修改失败！");
                }
            }

        }
        return map;
    }

    @Override
    public Map<String, Object> updateImg(String finalImg,String id) {
        Map<String, Object> map = new HashMap<>();
        try {
            user.setUrlHead(finalImg);
            user.setId(Long.valueOf(id));
            userMapper.updateImg(user);
            map.put("ok",true);
        }catch (Exception e){
            map.put("ok",false);
            System.out.println(e);
        }
        return map;
    }

    @Override
    public Map<String, Object> deleteUser(Long id) {
        Map<String, Object> map = new HashMap<>();
        map.put("ok", false);
        User byId = userMapper.findById(id);
        byId.setStatus(0);
        if(byId!=null){
            try {
                userMapper.update(byId);
                map.put("ok",true);
            }catch (Exception e){
                map.put("error","删除失败！");
            }
        }
        return map;
    }

    @Override
    public List<User> dim(User keyname) {

        return userMapper.dim(keyname);
    }

    @Override
    public List<User> paging(User user) {
        return userMapper.paging(user);
    }
}
