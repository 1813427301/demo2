package com.example.demo2.controller.student;

import com.example.demo2.domian.User;
import com.example.demo2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/student")
public class UrlStudentController {

    @Autowired
    private UserService userService;

    @RequestMapping("league_table")
    public String league_table(HttpSession session,Model model){
        User user = (User) session.getAttribute("user");
        User users = userService.findByid(user.getId());
        model.addAttribute("users",users);
        return "studentHTML/league_table";
    }

    @RequestMapping("index")
    public String sIndex(){
        return "studentHTML/index";
    }

    @RequestMapping("student_password")
    public String student_password(String id, Model model){
        model.addAttribute("id",id);
        return "studentHTML/password";
    }

    @RequestMapping("df")
    public String df(){
        return "studentHTML/df";
    }
}
