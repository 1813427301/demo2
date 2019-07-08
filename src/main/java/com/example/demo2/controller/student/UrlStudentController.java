package com.example.demo2.controller.student;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class UrlStudentController {


    @RequestMapping("index")
    public String sIndex(){
        return "studentHTML/index";
    }

    @RequestMapping("student_password")
    public String student_password(String id, Model model){
        model.addAttribute("id",id);
        return "studentHTML/password";
    }

    @RequestMapping("student_center")
    public String student_center(){
        return "studentHTML/student_center";
    }
}
