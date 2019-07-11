package com.example.demo2.controller.teacher;

import com.example.demo2.domian.Stu_cour;
import com.example.demo2.domian.Student;
import com.example.demo2.domian.Tea_stu;
import com.example.demo2.domian.User;
import com.example.demo2.service.StudentService;
import com.example.demo2.service.Tea_stuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/teacher")
public class UrlTeacherController {

    @Autowired
    private Tea_stuService tea_stuService;

    @Autowired
    private StudentService studentService;

    @RequestMapping("index")
    public String tIndex(){
        return "teacher/index";
    }
    @RequestMapping("student_password")
    public String student_password(String id, Model model){
        model.addAttribute("id",id);
        return "teacher/password";
    }

    @RequestMapping("teacher_table")
    public String teacher_table(HttpSession session,Model model){
        List<Stu_cour> list = new ArrayList<>();
        User user = (User) session.getAttribute("user");
        List<Tea_stu> stuList = tea_stuService.findAll(user.getUteacher().getTid());
        for (Tea_stu stu:stuList){
            Stu_cour byId2 = studentService.findById2(stu.getStudent_id());
            list.add(byId2);
            model.addAttribute("course",byId2);
        }
        model.addAttribute("studentlist",list);
        return "teacher/teacher_table";
    }
}
