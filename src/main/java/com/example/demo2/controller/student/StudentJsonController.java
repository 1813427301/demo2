package com.example.demo2.controller.student;

import com.example.demo2.domian.User;
import com.example.demo2.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/studentJson")
public class StudentJsonController {

    @Autowired
    private UserService userService;

    @RequestMapping("updatePssword")
    public Map<Object,Object> updatePssword(String psword,String id){
        Map<Object,Object> map = new HashMap<>();
        User user = userService.findByid(Long.parseLong(id));
        //使用shiro的认证
        Subject subject = SecurityUtils.getSubject();
        //创建令牌
        map.put("ok",true);
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), psword);
            subject.login(token);
        }catch (Exception e){
            map.put("ok",false);
        }
        return map;
    }
}
