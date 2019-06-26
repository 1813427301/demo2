package com.example.demo2;

import com.example.demo2.domian.User;
import com.example.demo2.mapper.UserMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo2ApplicationTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testUser() {
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken("admin", "123456");
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 执行认证登陆
        subject.login(token);
        //根据权限，指定返回数据
        User user = userMapper.findByName(token.getUsername());
        String role = user.getUsername();
        if ("user".equals(role)) {
            System.out.println("1");
        }
        if ("admin".equals(role)) {
            System.out.println("2");
        }
    }
}
