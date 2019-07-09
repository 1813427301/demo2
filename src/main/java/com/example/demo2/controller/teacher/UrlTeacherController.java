package com.example.demo2.controller.teacher;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teacher")
public class UrlTeacherController {

    @RequestMapping("index")
    public String tIndex(){
        return "teacher/index";
    }
    @RequestMapping("student_password")
    public String student_password(String id, Model model){
        model.addAttribute("id",id);
        return "studentHTML/password";
    }
}
