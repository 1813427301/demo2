package com.example.demo2.service.impl;

import com.example.demo2.dao.UserRepository;
import com.example.demo2.domian.Student;
import com.example.demo2.domian.Teacher;
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
import org.springframework.transaction.annotation.Transactional;

import javax.xml.soap.Text;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private User user;

    @Autowired
    private UserRepository userRepository;

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
    public Map<String, Object> create(String student_id,String username, String password, String password2, String email, String city) {
        Map<String, Object> map = new HashMap<>();
        map.put("ok", false);
        if (password.equals(password2)) {
            User user1 = userMapper.findByName(username);
            if (user1 == null) {
                user.setUsername(username);
                //加盐加密
                String salt = ShiroUtil.createSalt();
                String passwordBySalt = ShiroUtil.createPwdBySalt(password, salt);
                Student student = new Student();
                student.setSid(Long.parseLong(student_id));
                user.setPassword(passwordBySalt);
                user.setSalt(salt);
                user.setEmail(email);
                user.setUrlHead("img/3.png");
                user.setSex("保密");
                user.setStatus(1);
                user.setType(Integer.parseInt(city));
                user.setCreateTime(new Timestamp(new Date().getTime()));
                user.setUstudent(student);
                int row = userMapper.create(user);
                if (row > 0) {
                    map.put("ok", true);
                    map.put("error", "创建成功！");
                } else {
                    map.put("error", "插入数据报错，创建失败！");
                }

            } else {
                map.put("error", "用户已被创建！");
            }

        }
        return map;
    }

    @Override
    public Map<String, Object> teacherCreate(String teacher_id, String username, String password, String password2, String email, String city) {
        Map<String, Object> map = new HashMap<>();
        map.put("ok", false);
        if (password.equals(password2)) {
            User user1 = userMapper.findByName(username);
            if (user1 == null) {
                user.setUsername(username);
                //加盐加密
                String salt = ShiroUtil.createSalt();
                String passwordBySalt = ShiroUtil.createPwdBySalt(password, salt);
                Teacher teacher = new Teacher();
                teacher.setTid(Long.parseLong(teacher_id));
                user.setPassword(passwordBySalt);
                user.setSalt(salt);
                user.setEmail(email);
                user.setUrlHead("img/3.png");
                user.setSex("保密");
                user.setStatus(1);
                user.setType(Integer.parseInt(city));
                user.setCreateTime(new Timestamp(new Date().getTime()));
                user.setUteacher(teacher);
                int row = userMapper.teacherCreate(user);
                if (row > 0) {
                    map.put("ok", true);
                    map.put("error", "创建成功！");
                } else {
                    map.put("error", "插入数据报错，创建失败！");
                }

            } else {
                map.put("error", "用户已被创建！");
            }

        }
        return map;
    }

    @Override
    public Map<String, Object> adminCreate(String username, String password, String password2, String email, String city) {
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
                user.setType(Integer.parseInt(city));
                user.setCreateTime(new Timestamp(new Date().getTime()));
                int row =userMapper.adminCreate(user);
                if (row > 0) {
                    map.put("ok", true);
                    map.put("error", "创建成功！");
                } else {
                    map.put("error", "插入数据报错，创建失败！");
                }

            } else {
                map.put("error", "用户已被创建！");
            }

        }
        return map;
    }

    @Override
    public Map<String, Object> login(String username, String password) {
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
        } catch (IncorrectCredentialsException e) {
            map.put("error", "登录失败！密码错误！");
        } catch (AuthenticationException e) {
            map.put("error", "登录失败！用户不存在！");
        } catch (Exception e) {
            map.put("error", "登录失败！内部错误！");
        }
        return map;
    }

    @Override
    public Map<String, Object> updateUser(Long id, String username, String password, String password2, String email, String city, String date) {
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
            user.setType(Integer.parseInt(city));
            user.setCreateTime(Timestamp.valueOf(date));
            if (user1 == null) {
                int row = userMapper.create(user);
                if (row > 0) {
                    map.put("ok", true);
                    map.put("error", "创建成功！");
                } else {
                    map.put("error", "插入数据报错，创建失败！");
                }
            } else {

                int row = userMapper.update(user);
                if (row > 0) {
                    map.put("ok", true);
                    map.put("error", "用户修改成功！");
                } else {
                    map.put("error", "修改失败！");
                }

            }

        }
        return map;
    }

    @Override
    public Map<String, Object> updateImg(String finalImg, String id) {
        Map<String, Object> map = new HashMap<>();
        map.put("ok", false);
        user.setUrlHead(finalImg);
        user.setId(Long.valueOf(id));
        int row = userMapper.updateImg(user);
        if (row > 0) {
            map.put("ok", true);
        }
        return map;
    }

    @Override
    public Map<String, Object> deleteUser(Long id) {
        Map<String, Object> map = new HashMap<>();
        map.put("ok", false);
        map.put("error", "删除失败！");
        User byId = userMapper.findById(id);
        byId.setStatus(0);
        if (byId != null) {
            int row = userMapper.update2(byId);
            if (row > 0) {
                map.put("ok", true);
                map.put("error", "删除成功！");
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
