package com.example.demo2.controller.teacher;

import com.example.demo2.domian.*;
import com.example.demo2.service.Stu_ResulService;
import com.example.demo2.service.StudentService;
import com.example.demo2.service.Tea_stuService;
import com.example.demo2.service.serviceDao.ResultsService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/teacher")
public class UrlTeacherController {

    @Autowired
    private Tea_stuService tea_stuService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private Stu_ResulService resulService;

    @Autowired
    private ResultsService2 resultsService2;

    @RequestMapping("index")
    public String tIndex(){
        return "teacher/index";
    }

    @RequestMapping("student_password")
    public String student_password(String id, Model model){
        model.addAttribute("id",id);
        return "teacher/password";
    }

    //老师查看学生成绩

    @RequestMapping("chengji")
    public String chengji(String sid,Model model){
        List<Stu_Resul> byStudent_id = resulService.findByStudent_id(Long.parseLong(sid));
        Stu_cour stuCour = studentService.findById2(Long.parseLong(sid));
        List<Resultss> list = new ArrayList<>();
        for (Stu_Resul stu_resul:byStudent_id){
            Resultss resultss = resultsService2.findById(stu_resul.getResultss_id());
            list.add(resultss);
        }
        model.addAttribute("stuCour",stuCour);
        model.addAttribute("list",list);

        return "teacher/chengji";
    }

    @RequestMapping("teacher_table")
    public String teacher_table(HttpSession session,Model model){
        List<Resultss> list = new ArrayList<>();
        Map<Stu_cour,List<Resultss>> map = new HashMap<>();
        User user = (User) session.getAttribute("user");
        List<Tea_stu> stuList = tea_stuService.findAll(user.getUteacher().getTid());
        for (Tea_stu stu:stuList){
            Stu_cour byId2 = studentService.findById2(stu.getStudent_id());// 获取学生与班级

            List<Stu_Resul> byStudent_id = resulService.findByStudent_id(byId2.getStudent_id().getSid());
            for(Stu_Resul stu_resul:byStudent_id){
                Resultss resultss = resultsService2.findById(stu_resul.getResultss_id());//获取学生成绩
               list.add(resultss);
            }
            map.put(byId2,list);
            model.addAttribute("course",byId2);
        }
        model.addAttribute("map",map);
        return "teacher/teacher_table";
    }
}
