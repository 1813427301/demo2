package com.example.demo2.controller.student;

import com.example.demo2.domian.Resultss;
import com.example.demo2.domian.Stu_cour;
import com.example.demo2.domian.User;
import com.example.demo2.service.StudentService;
import com.example.demo2.service.UserService;
import com.example.demo2.service.serviceDao.ResultsService2;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/studentJson")
public class StudentJsonController {

    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ResultsService2 resultsService2;

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

    @RequestMapping("studentKey")
    public Map<String,Object> studentKey(String Key){
        Resultss byKey = resultsService2.findByKey(Key);
        Stu_cour byId2 = studentService.findById2(Long.parseLong(Key));
        Map<String,Object> map= new HashMap<>();
        map.put("byKey",byKey);
        map.put("byId2",byId2);
        return map;
    }

}
