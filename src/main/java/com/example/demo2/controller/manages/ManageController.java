package com.example.demo2.controller.manages;

import com.example.demo2.dao.TestRegex;
import com.example.demo2.domian.*;
import com.example.demo2.service.*;
import com.example.demo2.service.serviceDao.ResultsService2;
import com.example.demo2.service.serviceDao.StudentService2;
import com.example.demo2.service.serviceDao.TeacherService2;
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
import java.util.ArrayList;
import java.util.HashMap;
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

    @Autowired
    private TeacherService2 teacherService2;

    @Autowired
    private ResultsService2 resultsService2;

    @Autowired
    private Course2Service course2Service;

    @Autowired
    private Stu_ResulService stu_resulService;

    @Autowired
    private Tea_stuService tea_stuService;

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
            User user =(User) map.get("user");
            if(user.getType()==2){
                session.setAttribute("user", map.get("user"));
                return "afters/index";
            }else {
                return "login";

            }
        } else {
            model.addAttribute("error2", map.get("error"));
        }
        model.addAttribute("status", "managelogin");
        return "login";
    }

    //后台管理员添加账号
    @PostMapping("addadmin")
    public String addadmin(String username, String email, String pass, String repass, String city, Model model, HttpServletResponse response) throws IOException {
        Map<String, Object> map = userService.adminCreate(username, pass, repass,email,city);
        model.addAttribute("error3", map.get("error"));
        if ((boolean) map.get("ok") == false) {
            return "afters/adminuser";
        }
        PrintWriter out = response.getWriter();
        out.print("<script>window.parent.location.href='/afterss/userlist';</script>");
        out.flush();
        out.close();
        return "afters/userlist";
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
        Map<String, Object> map = userService.teacherCreate(teacher_id,username, pass, repass, email, city);
        model.addAttribute("error3", map.get("error"));
        if ((boolean) map.get("ok") == false) {
            return "afters/teacheruser";
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
    @PostMapping("teacher_add")
    public String teacher_add(String Tname, String jiao_course, String Teducation, String Cid,Model model, HttpServletResponse response) throws IOException{
        System.out.println( Tname+jiao_course+Teducation+Cid);
        if ((Tname == null || Tname.equals("")) || (jiao_course == null || jiao_course.equals("")) || (Teducation == null || Teducation.equals("")) || (Cid == null || Cid.equals(""))) {
            model.addAttribute("error","不可留空!");
            List<Course> courseList = courseService.findAll();
            List<Course2> course2List = course2Service.findAll();
            model.addAttribute("courseList",courseList);
            model.addAttribute("course2List",course2List);
            return "afters/teacher_add";
        }
        Map<Object, Object> map = teacherService2.createRepository(Tname, jiao_course, Teducation, Cid);
        if((boolean)map.get("ok")){
            model.addAttribute("error","添加老师成功!");
        }else {
            List<Course> courseList = courseService.findAll();
            List<Course2> course2List = course2Service.findAll();
            model.addAttribute("courseList",courseList);
            model.addAttribute("course2List",course2List);
            model.addAttribute("error","添加老师失败!");
        }
        PrintWriter out = response.getWriter();
        out.print("<script>window.parent.location.href='/afterss/teacherlist';</script>");
        out.flush();
        out.close();
        return "afters/teacherlist";
    }

    //更新老师数据
    @PostMapping("update_teacher")
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
    @PostMapping("student_add")
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
    @PostMapping("updatestudent")
    public String updatestudent(String id,String cid,String sname,String sage,String sgender,String sid_card,String saddr,Model model,HttpServletResponse response) throws IOException{
        List<Course> courseList = courseService.findAll();//专业与课程
        Student student = new Student();
        student.setSid(Long.parseLong(id));
        Stu_cour S = studentService.findById2(Long.parseLong(id));


        if(Integer.valueOf(sage)>150 || Integer.valueOf(sage)<15){
            model.addAttribute("error","修改学生失败,年龄不符合！");
            model.addAttribute("courseList", courseList);
            model.addAttribute("student", S);
            return "afters/updatestudent";
        }
        if((new TestRegex().isCardId(sid_card))==false){
            model.addAttribute("error","修改学生失败,身份证错误！");
            model.addAttribute("courseList", courseList);
            model.addAttribute("student", S);
            return "afters/updatestudent";
        }
       int row = studentService.update(id,cid,sname, sage, sgender, sid_card, saddr);
        System.out.println(row);
        if(row <= 0){
            model.addAttribute("error","修改学生失败！");
            model.addAttribute("courseList", courseList);
            model.addAttribute("student", S);
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

    //添加学生成绩
    @RequestMapping("student_results_add")
    public String student_results_add(String sid,String results,String rname,Model model,HttpServletResponse response)throws IOException{
        System.out.println(sid+":"+results+":"+rname+":");
        Map<String, Object> map = resultsService2.create(sid, results,rname);
        List<Stu_cour> courList = new ArrayList<>();
        model.addAttribute("error",map.get("error"));
        if((boolean)map.get("ok")){
            List<Resultss> resultssList = resultsService2.findAll();
            for (Resultss resultss1 : resultssList){
                Stu_cour byId2 = studentService.findById2(resultss1.getRid());
                courList.add(byId2);
            }
            model.addAttribute("resultsList", resultssList);
            model.addAttribute("courList",courList);
            PrintWriter out = response.getWriter();
            out.print("<script>window.parent.location.href='/afterss/student_results';</script>");
            out.flush();
            out.close();
            return "afters/student_results";
        }
        return "afters/student_results_add";
    }

    //修改学生成绩
    @RequestMapping("student_results_update")
    public String student_results_update(String id,String sid,String results,String rname,Model model,HttpServletResponse response)throws IOException{
        System.out.println(sid+":"+results+":"+rname+":");
       int row = resultsService2.update(sid,results);
        if(row>0){

            PrintWriter out = response.getWriter();
            out.print("<script>window.parent.location.href='/afterss/student_results';</script>");
            out.flush();
            out.close();
            return "afters/student_results";
        }

        List<Tea_stu> studentAll = tea_stuService.findStudentAll(Long.parseLong(id));
        for(Tea_stu tea_stu:studentAll){
            Teacher teacher = new Teacher();
            teacher.setTid(tea_stu.getTeacher_id());
            Teacher teacher1 = teacherService.findById(teacher);
            if(teacher1.getJiao_course().equals(rname)){
                model.addAttribute("teacher", teacher1);
                break;
            }
        }
        Resultss service2ById = resultsService2.findById(Long.parseLong(sid));
        Stu_cour stu_cour = studentService.findById2(Long.parseLong(id));
        model.addAttribute("student", stu_cour);
        model.addAttribute("service2ById", service2ById);

        return "afters/student_results_update";
    }


    //课程添加
    @RequestMapping("couser_INSERT")
    public String couser_INSERT(String c2name,String series2,String cid,Model model,HttpServletResponse response) throws IOException{
        Map<Object, Object> map = course2Service.create(series2, c2name,cid);
        if((boolean)map.get("ok")){
            List<Course2> course2List = course2Service.findAll();
            model.addAttribute("course2List",course2List);
            PrintWriter out = response.getWriter();
            out.print("<script>window.parent.location.href='/afterss/couserlist';</script>");
            out.flush();
            out.close();
            return "afters/couserlist";
        }

        return "afters/couser_ad";
    }

    @RequestMapping("couser_UPDATE")
    public String couser_UPDATE(String c2id,String c2name,String series2,Model model,HttpServletResponse response) throws IOException{


        if((c2id==null ||c2id.equals(""))&&(c2name==null ||c2name.equals("")) && (series2==null ||series2.equals(""))){
            model.addAttribute("error","不能留空");
            return "afters/couser_update";
        }
            Course2 course2 = new Course2();
            course2.setC2id(Long.parseLong(c2id));
            course2.setC2name(c2name);
            course2.setSeries2(series2);
            int row = course2Service.update(course2);
            if(row > 0){
                List<Course2> course2List = course2Service.findAll();
                model.addAttribute("course2List",course2List);
                PrintWriter out = response.getWriter();
                out.print("<script>window.parent.location.href='/afterss/couserlist';</script>");
                out.flush();
                out.close();
                return "afters/couserlist";
            }
            return "afters/couser_update";

    }
}
