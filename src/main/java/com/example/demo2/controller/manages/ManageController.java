package com.example.demo2.controller.manages;

import com.example.demo2.dao.TestRegex;
import com.example.demo2.domian.Course;
import com.example.demo2.domian.Stu_cour;
import com.example.demo2.domian.Student;
import com.example.demo2.domian.Teacher;
import com.example.demo2.service.CourseService;
import com.example.demo2.service.serviceDao.StudentService2;
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

    @Autowired
    private StudentService2 studentService2;

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

    //后台用户添加学生账号
    @PostMapping("adduser")
    public String adduser(String student_id,String username, String email, String pass, String repass, String city, Model model, HttpServletResponse response) throws IOException {
        Map<String, Object> map = userService.create(student_id,username, pass, repass, email, city);
        model.addAttribute("error3", map.get("error"));
        if ((boolean) map.get("ok") == false) {
            return "afters/adduser";
        }
        PrintWriter out = response.getWriter();
        out.print("<script>window.parent.location.href='/afterss/userlist';</script>");
        out.flush();
        out.close();
        return "afters/userlist";
    }

    //后台用户添加老师账号
    @PostMapping("teacheruser")
    public String teacheruser(String teacher_id,String username, String email, String pass, String repass, String city, Model model, HttpServletResponse response) throws IOException {
        Map<String, Object> map = userService.create2(teacher_id,username, pass, repass, email, city);
        model.addAttribute("error3", map.get("error"));
        if ((boolean) map.get("ok") == false) {
            return "afters/adduser";
        }
        PrintWriter out = response.getWriter();
        out.print("<script>window.parent.location.href='/afterss/userlist';</script>");
        out.flush();
        out.close();
        return "afters/userlist";
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

    //添加老师
    @PostMapping("/teacher_add")
    public String teacher_add(String Tname, String jiao_course, String Teducation, String Cid,Model model, HttpServletResponse response) throws IOException{
        System.out.println( Tname+jiao_course+Teducation+Cid);
        if ((Tname == null || Tname.equals("")) || (jiao_course == null || jiao_course.equals("")) || (Teducation == null || Teducation.equals("")) || (Cid == null || Cid.equals(""))) {
            model.addAttribute("error","不可留空!");
            List<Course> courseList = courseService.findAll();
            model.addAttribute("courseList",courseList);
            return "afters/teacher_add";
        }
        Map<Object, Object> map = teacherService.create(Tname, jiao_course, Teducation, Cid);
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
    public String update_teacher(String Tname, String jiao_course, String Teducation, String Cid,String tid,Model model, HttpServletResponse response) throws IOException{
        if ((Tname == null || Tname.equals("")) || (jiao_course == null || jiao_course.equals("")) || (Teducation == null || Teducation.equals("")) || (Cid == null || Cid.equals(""))) {
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
        teacher.setJiao_course(jiao_course);
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
    public String student_add(String sid,String sname,String sage,String sgender,String sid_card,String saddr ,Model model,HttpServletResponse response) throws IOException{
        List<Teacher> teacherList = teacherService.findAll();//班级
        List<Course> courseList = courseService.findAll();//专业与课程
        if(Integer.valueOf(sage)>150 || Integer.valueOf(sage)<15){
            model.addAttribute("error","添加学生失败,年龄不符合！");
            model.addAttribute("teacherList",teacherList);
            model.addAttribute("courseList",courseList);
            return "afters/student_add";
        }
        if((new TestRegex().isCardId(sid_card))==false){
            model.addAttribute("error","添加学生失败,身份证错误！");
            model.addAttribute("teacherList",teacherList);
            model.addAttribute("courseList",courseList);
            return "afters/student_add";
        }

        Map<Object, Object> map = studentService2.create(sid,sname, sage, sgender, sid_card, saddr);
        if((Boolean) map.get("ok")==false){
            model.addAttribute("error","添加学生失败！");
            model.addAttribute("teacherList",teacherList);
            model.addAttribute("courseList",courseList);
            return "afters/student_add";
        }
        List<Stu_cour> studentList = studentService.findAll();
        model.addAttribute("studentList",studentList);
        model.addAttribute("error","添加学生成功！");
        PrintWriter out = response.getWriter();
        out.print("<script>window.parent.location.href='/afterss/studentlist';</script>");
        out.flush();
        out.close();
        return "afters/studentlist";
    }

    //修改学生
    @PostMapping("/updatestudent")
    public String updatestudent(String id,String cid,String sname,String sage,String sgender,String sid_card,String saddr ,Model model,HttpServletResponse response) throws IOException{
        List<Teacher> teacherList = teacherService.findAll();//班级
        List<Course> courseList = courseService.findAll();//专业与课程
        if(Integer.valueOf(sage)>150 || Integer.valueOf(sage)<15){
            model.addAttribute("error","修改学生失败,年龄不符合！");
            model.addAttribute("teacherList",teacherList);
            model.addAttribute("courseList",courseList);
            return "afters/updatestudent";
        }
        if((new TestRegex().isCardId(sid_card))==false){
            model.addAttribute("error","修改学生失败,身份证错误！");
            model.addAttribute("teacherList",teacherList);
            model.addAttribute("courseList",courseList);
            return "afters/updatestudent";
        }
       int row = studentService.update(id,cid,sname, sage, sgender, sid_card, saddr);
        if(row <= 0){
            model.addAttribute("error","修改学生失败！");
            model.addAttribute("teacherList",teacherList);
            model.addAttribute("courseList",courseList);
            return "afters/updatestudent";
        }
        List<Stu_cour> studentList = studentService.findAll();
        model.addAttribute("studentList",studentList);
        model.addAttribute("error","修改学生成功！");
        PrintWriter out = response.getWriter();
        out.print("<script>window.parent.location.href='/afterss/studentlist';</script>");
        out.flush();
        out.close();
        return "afters/studentlist";
    }
}
