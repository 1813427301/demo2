package com.example.demo2.controller;

import com.example.demo2.domian.User;
import com.example.demo2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("login")
    public String login(){
        return "login";
    }

    @PostMapping("regist")
    public String regist(String username, String password, String password2, String email, Model model){
        if(username.equals("")||password.equals("")||password2.equals("")||email.equals("")){
            model.addAttribute("error3","不能留空");
            return "login";
        }
        Map<String, Object> map = userService.create("",username, password, password2, email,"0");
        model.addAttribute("error3",map.get("error"));
        if((boolean)map.get("ok")==false){
            return "login";
        }
        return "login";
    }
    @PostMapping("login")
    public String login(String username , String password , Model model, HttpSession session) throws IOException {

        Map<String, Object> map = userService.login(username, password);
        if((boolean)map.get("ok")){
            User user =(User) map.get("user");
            session.setAttribute("user",map.get("user"));
            if (user.getType()==0){
                return "studentHTML/index";
            }else  if(user.getType()==1){
                return "teacher/index";
            }
        }else {
            model.addAttribute("error1",map.get("error"));
        }
        return "login";
    }

    @GetMapping("exit")
    public String exit(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        return "login";
    }

}
