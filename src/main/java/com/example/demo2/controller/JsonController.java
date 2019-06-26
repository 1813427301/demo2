package com.example.demo2.controller;

import com.example.demo2.domian.User;
import com.example.demo2.domian.UserDetails;
import com.example.demo2.service.CourseService;
import com.example.demo2.service.StudentService;
import com.example.demo2.service.UserDetailsService;
import com.example.demo2.service.UserService;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Json")
public class JsonController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private User user;

    @Autowired
    private CourseService courseService;

    @RequestMapping("regist")
    public Map<String, Object> jsonRegist(String username, String password, String password2, String email) {
        Map<String, Object> map = new HashMap<>();
        if (username == null || username.equals("")) {
            map.put("nameError", "用户名不能留空");
        }
        if (password == null || password.equals("")) {
            map.put("padError", "密码不能留空");
        }
        if (password2 == null || password2.equals("")) {
            map.put("pad2Error", "第二次密码不能留空");
        }
        if (email == null || email.equals("")) {
            map.put("emailError", "邮箱不能留空");
        }
        return map;
    }

    @RequestMapping("login")
    public Map<String, Object> jsonLogin(String username, String password) {
        Map<String, Object> map = new HashMap<>();
        if (username == null || username.equals("")) {
            map.put("nameError", "用户名不能留空");
        }
        if (password == null || password.equals("")) {
            map.put("padError", "密码不能留空");
        }
        return map;
    }

    @RequestMapping("managelogin")
    public Map<String, Object> managelogin(String username, String password) {
        Map<String, Object> map = new HashMap<>();
        if (username == null || username.equals("")) {
            map.put("nameError", "用户名不能留空");
        }
        if (password == null || password.equals("")) {
            map.put("padError", "密码不能留空");
        }
        return map;
    }

    //user删除
    @RequestMapping("userDelete")
    public Map<String, Object> userDelete(String id, Model model) {
        Map<String, Object> map = userService.deleteUser(Long.parseLong(id));
        System.out.println("delete:" + id);
        return map;
    }

    //user关键字搜索
    @RequestMapping("userDimCheck")
    public List<User> userDimCheck(String keyname) {
        System.out.println("关键字：" + keyname);
        User user = new User();
        user.setKeyname(keyname);
        List<User> users = userService.dim(user);
        System.out.println("模糊查询：" + users.toString());
        return users;
    }

    //user分页
    @RequestMapping("userPaging")
    public List<User> userPaging(String curPage) {
        user.setStartPageSize((Integer.parseInt(curPage) - 1) * 5);
        user.setEndPageSize(5);
        List<User> users = userService.paging(user);
        user.setEndPageSize(Integer.parseInt(curPage));
        users.add(user);
        return users;
    }

    //user上一页
    @RequestMapping("userOnTurning")
    public List<User> userOnTurning(String onTurning) {
        user.setStartPageSize((Integer.parseInt(onTurning) * 5) - 10 < 0 ? 0 : ((Integer.parseInt(onTurning) * 5) - 10));
        user.setEndPageSize(5);
        List<User> users = userService.paging(user);
        user.setEndPageSize(Integer.parseInt(onTurning) - 1 == 0 ? 1 : Integer.parseInt(onTurning) - 1);
        users.add(user);
        return users;
    }

    //user下一页
    @RequestMapping("userDownTurning")
    public List<User> userDownTurning(String downTurning) {
        List<User> counts = userService.findAll();
        int cunSize = ((counts.size() / 5 == 0 ? (counts.size() / 5) : ((counts.size() / 5) + 1)));
        System.out.println(cunSize);
        user.setStartPageSize(Integer.parseInt(downTurning) == cunSize  ? (Integer.parseInt(downTurning) - 1) * 5 : (Integer.parseInt(downTurning) * 5));
        user.setEndPageSize(5);
        List<User> users = userService.paging(user);
        user.setEndPageSize(Integer.parseInt(downTurning) >= cunSize ? (Integer.parseInt(downTurning)) : (Integer.parseInt(downTurning) + 1));
        users.add(user);

        return users;
    }

    //个人基本信息
    @PostMapping("user_center/add")
    public Map<Object, Object> user_center(String uid, String dateBirth, String phone, String email, String address, String synopsis) {
        Map<Object, Object> map = new HashMap<>();
        System.out.println(uid + "生日:" + dateBirth + "电话:" + phone + "邮箱:" + email + "地址:" + address + "内容:" + synopsis);
        map = userDetailsService.insert(uid, dateBirth, phone, address, synopsis);
        if ((boolean) map.get("ok")) {
            map.put("userDetails", map.get("userDetails"));
            System.out.println(map.get("userDetails"));
            return map;
        }
        return map;
    }

    //上传头像图片
    @RequestMapping("uploadImg")
    public Map<String, Object> uploadImg(String finalImg, String id) {
        Map<String, Object> map = userService.updateImg(finalImg, id);
        return map;
    }

    @RequestMapping("course_add/add")
    public Map<Object, Object> course_Add(String college,String series,String major, String grade,String course){
        Map<Object, Object> map = courseService.create(college, series, major, grade, course);
        return map;
    }
}
