package com.example.demo2.controller.manages;

import com.example.demo2.domian.Course;
import com.example.demo2.domian.Teacher;
import com.example.demo2.mapper.TeacherMapper;
import com.example.demo2.service.CourseService;
import com.example.demo2.service.StudentService;
import com.example.demo2.service.TeacherService;
import com.example.demo2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/manage")
public class ManageController {
    @Autowired
    private UserService userService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    @GetMapping("login")
    public String managelogin(Model model) {
        model.addAttribute("status", "managelogin");
        return "login";
    }

    //登录
    @PostMapping("login")
    public String login(String username, String password, Model model, HttpSession session) throws IOException {
        Map<String, Object> map = userService.login(username, password);
        if ((boolean) map.get("ok")) {
            session.setAttribute("user", map.get("user"));
            return "afters/index";
        } else {
            model.addAttribute("error2", map.get("error"));
        }
        model.addAttribute("status", "managelogin");
        return "login";
    }

    //后台用户添加
    @PostMapping("adduser")
    public String adduser(String username, String email, String pass, String repass, String city, Model model, HttpServletResponse response) throws IOException {
        Map<String, Object> map = userService.create(username, pass, repass, email, city);
        model.addAttribute("error3", map.get("error"));
        if ((boolean) map.get("ok") == false) {
            return "afters/adduser";
        }
        PrintWriter out = response.getWriter();
        out.print("<script>window.parent.location.href='/afterss/userlist';</script>");
        out.flush();
        return "/afterss/userlist";
    }

    //修改用户
    @PostMapping("updateuser")
    public String updateuser(String id, String username, String email, String pass, String repass, String city, String date, Model model, HttpServletResponse response) throws IOException {
        Map<String, Object> map = userService.updateUser(Long.parseLong(id), username, pass, repass, email, city, date);
        model.addAttribute("error3", map.get("error"));
        if ((boolean) map.get("ok") == false) {
            return "afters/updateuser";
        }
        PrintWriter out = response.getWriter();
        out.print("<script>window.parent.location.href='/afterss/userlist';</script>");
        out.flush();
        out.close();
        return "afters/userlist";
    }

    //添加用户
    @PostMapping("/teacher_add")
    public String teacher_add(String Tname, String Tgrade, String Teducation, String Cid,Model model, HttpServletResponse response) throws IOException{
        if ((Tname == null || Tname.equals("")) || (Tgrade == null || Tgrade.equals("")) || (Teducation == null || Teducation.equals("")) || (Cid == null || Cid.equals(""))) {
            model.addAttribute("error","不可留空!");
            List<Course> courseList = courseService.findAll();
            model.addAttribute("courseList",courseList);
            return "afters/teacher_add";
        }
        Map<Object, Object> map = teacherService.create(Tname, Tgrade, Teducation, Cid);
        if((boolean)map.get("ok")){
            model.addAttribute("error","添加老师成功!");
        }else {
            model.addAttribute("error","添加老师失败!");
        }
        PrintWriter out = response.getWriter();
        out.print("<script>window.parent.location.href='/afterss/teacherlist';</script>");
        out.flush();
        out.close();
        return "afters/teacherlist";
    }

    //更新老师数据
    @PostMapping("/update_teacher")
    public String update_teacher(String Tname, String Tgrade, String Teducation, String Cid,String tid,Model model, HttpServletResponse response) throws IOException{
        if ((Tname == null || Tname.equals("")) || (Tgrade == null || Tgrade.equals("")) || (Teducation == null || Teducation.equals("")) || (Cid == null || Cid.equals(""))) {
            model.addAttribute("error","不可留空!");
            List<Course> courseList = courseService.findAll();
            model.addAttribute("courseList",courseList);
            return "afterss/updateteacher";
        }
        Teacher teacher = new Teacher();
        Course course = new Course();
        teacher.setTid(Long.parseLong(tid));
        course.setCid(Long.parseLong(Cid));
        teacher.setTname(Tname);
        teacher.setTgrade(Tgrade);
        teacher.setTeducation(Teducation);
        teacher.setCourse(course);
        int row = teacherService.update(teacher);
        if(row>0){
            model.addAttribute("error","更新老师成功!");
        }else {
            model.addAttribute("error","更新老师失败!");
        }
        PrintWriter out = response.getWriter();
        out.print("<script>window.parent.location.href='/afterss/teacherlist';</script>");
        out.flush();
        out.close();
        return "afters/teacherlist";
    }

    //添加学生
    @PostMapping("/student_add")
    public String student_add(String grade,String sname,String sage,String sgender,int sid_card,String saddr,String smoajr ,Model model,HttpServletResponse response) throws IOException{
        if((Integer.valueOf(sage))<150||(Integer.valueOf(sage))>15){
            model.addAttribute("error","添加学生失败,年龄不符合！");
            return "afters/student_add";
        }
        Map<Object, Object> map = studentService.create(grade,sname, sage, sgender, sid_card, saddr, smoajr);
        if((Boolean) map.get("ok")){
            model.addAttribute("error","添加学生失败！");
            return "afters/student_add";
        }
        model.addAttribute("error","添加学生成功！");
        PrintWriter out = response.getWriter();
        out.print("<script>window.parent.location.href='/afterss/studentlist';</script>");
        out.flush();
        out.close();
        return "afters/studentlist";
    }
}
