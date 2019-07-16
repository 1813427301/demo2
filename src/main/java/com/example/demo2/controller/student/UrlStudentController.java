package com.example.demo2.controller.student;

import com.example.demo2.domian.Resultss;
import com.example.demo2.domian.Stu_Resul;
import com.example.demo2.domian.Stu_cour;
import com.example.demo2.domian.User;
import com.example.demo2.service.Stu_ResulService;
import com.example.demo2.service.StudentService;
import com.example.demo2.service.UserService;
import com.example.demo2.service.serviceDao.ResultsService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/student")
public class UrlStudentController {

    @Autowired
    private Stu_ResulService resulService;

    @Autowired
    private StudentService studentService;


    @Autowired
    private ResultsService2 resultsService2;

    @RequestMapping("league_table")
    public String league_table(HttpSession session,Model model){
        User user = (User) session.getAttribute("user");

        List<Stu_Resul> byStudent_id = resulService.findByStudent_id(user.getId());
        Stu_cour stuCour = studentService.findById2(user.getId());
        List<Resultss> list = new ArrayList<>();
        for (Stu_Resul stu_resul:byStudent_id){
            Resultss resultss = resultsService2.findById(stu_resul.getResultss_id());
            list.add(resultss);
        }
        model.addAttribute("list",list);
        model.addAttribute("student",stuCour);
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
