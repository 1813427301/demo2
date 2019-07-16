package com.example.demo2.controller;

import com.example.demo2.domian.*;
import com.example.demo2.service.*;
import com.example.demo2.service.StudentService;
import com.example.demo2.service.serviceDao.ResultsService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Json")
public class JsonController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private User user;

    @Autowired
    private CourseService courseService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private ResultsService2 resultsService2;

    @Autowired
    private Course2Service course2Service;

    @Autowired
    private Tea_stuService tea_stuService;

    @RequestMapping("login")
    public Map<String, Object> jsonLogin(String username, String password) {
        Map<String, Object> map = new HashMap<>();
        if (username == null || username.equals("")) {
            map.put("nameError", "用户名不能留空");
        }
        if (password == null || password.equals("")) {
            map.put("padError", "密码不能留空");
        }
        return map;
    }

    @RequestMapping("managelogin")
    public Map<String, Object> managelogin(String username, String password) {
        Map<String, Object> map = new HashMap<>();
        if (username == null || username.equals("")) {
            map.put("nameError", "用户名不能留空");
        }
        if (password == null || password.equals("")) {
            map.put("padError", "密码不能留空");
        }
        return map;
    }

    //user删除
    @RequestMapping("userDelete")
    public Map<String, Object> userDelete(String id, Model model) {
        Map<String, Object> map = userService.deleteUser(Long.parseLong(id));
        System.out.println("delete:" + id);
        return map;
    }

    //user关键字搜索
    @RequestMapping("userDimCheck")
    public List<User> userDimCheck(String keyname) {
        if(keyname==null  || keyname.equals("")){
            return null;
        }
        System.out.println("关键字：" + keyname);
        User user = new User();
        user.setKeyname(keyname);
        List<User> users = userService.dim(user);
        System.out.println("模糊查询：" + users.toString());
        return users;
    }

    //student关键字搜索
    @RequestMapping("studentDimCheck")
    public List<Stu_cour> studentDimCheck(String keyname) {
        if(keyname==null || keyname.equals("")){
            return null;
        }
        System.out.println("关键字：" + keyname);
        List<Stu_cour> stu_cour = studentService.findByNameKey(keyname);
        System.out.println("模糊查询：" + stu_cour.toString());
        return stu_cour;
    }

    //user分页
    @RequestMapping("userPaging")
    public List<User> userPaging(String curPage) {
        user.setStartPageSize((Integer.parseInt(curPage) - 1) * 5);
        user.setEndPageSize(5);
        List<User> users = userService.paging(user);
        user.setEndPageSize(Integer.parseInt(curPage));
        users.add(user);
        return users;
    }

    //user上一页
    @RequestMapping("userOnTurning")
    public List<User> userOnTurning(String onTurning) {
        user.setStartPageSize((Integer.parseInt(onTurning) * 5) - 10 < 0 ? 0 : ((Integer.parseInt(onTurning) * 5) - 10));
        user.setEndPageSize(5);
        List<User> users = userService.paging(user);
        user.setEndPageSize(Integer.parseInt(onTurning) - 1 == 0 ? 1 : Integer.parseInt(onTurning) - 1);
        users.add(user);
        return users;
    }

    //user下一页
    @RequestMapping("userDownTurning")
    public List<User> userDownTurning(String downTurning) {
        List<User> counts = userService.findAll();
        String value=(BigDecimal.valueOf(counts.size()).divide( BigDecimal.valueOf(Integer.valueOf(5)))).toString();
        int cunSize =0;
        if(value.contains(".")){
            String[] splits = value.split("\\.");
            cunSize = (Integer.valueOf(splits[0])+1);
        }else {
            cunSize = (Integer.valueOf(value));
        }
        user.setStartPageSize(Integer.parseInt(downTurning) == cunSize ? (Integer.parseInt(downTurning) - 1) * 5 : (Integer.parseInt(downTurning) * 5));
        user.setEndPageSize(5);
        List<User> users = userService.paging(user);
        user.setEndPageSize(Integer.parseInt(downTurning) >= cunSize ? (Integer.parseInt(downTurning)) : (Integer.parseInt(downTurning) + 1));
        users.add(user);

        return users;
    }

    //个人基本信息
    @PostMapping("user_center/add")
    public Map<Object, Object> user_center(String uid, String dateBirth, String phone, String email, String address, String synopsis) {
        Map<Object, Object> map = new HashMap<>();
        map = userDetailsService.insert(uid, dateBirth, phone, address, synopsis , email);
        if ((boolean) map.get("ok")) {
            map.put("userDetails", map.get("userDetails"));
            map.put("ok", true);
            return map;
        }map.put("ok", false);

        return map;
    }

    //上传头像图片
    @RequestMapping("uploadImg")
    public Map<String, Object> uploadImg(String finalImg, String id) {
        Map<String, Object> map = userService.updateImg(finalImg, id);
        return map;
    }

    //课程添加
    @RequestMapping("course_add/add")
    public Map<Object, Object> course_Add(String series, String major, String grade, String cid) {
        Map<Object, Object> map = courseService.create( series, major, grade, cid);
        return map;
    }

    //课程删除
    @RequestMapping("course_add/delete")
    public Map<Object, Object> course_Add(String cid) {
        Map<Object, Object> map = new HashMap<>();
        map.put("ok", false);
        Course course = new Course();
        course.setCid(Long.parseLong(cid));
        int row = courseService.delete(course);
        if(row>0){
            map.put("ok", true);
        }

        return map;
    }

    //老师删除
    @RequestMapping("teacherDelete")
    public Map<Object, Object> teacherDelete(String id) {
        Map<Object, Object> map = new HashMap<>();
        Teacher teacher = new Teacher();
        teacher.setStatus(0);
        teacher.setTid(Long.parseLong(id));
        int row = teacherService.updateDelete(teacher);
        if (row > 0) {
            map.put("ok", true);
        } else {
            map.put("ok", false);
        }
        return map;
    }

    //teacher关键字搜索
    @RequestMapping("teacherDimCheck")
    public List<Teacher> teacherDimCheck(String keyname) {
        if(keyname==null || keyname.equals("")){
            return null;
        }
        System.out.println("关键字：" + keyname);
        Teacher teacher = new Teacher();
        teacher.setKeyname(keyname);
        List<Teacher> teacherList = teacherService.dim(teacher);
        System.out.println("模糊查询：" + teacherList.toString());
        return teacherList;
    }

    //teacherPaging分页
    @RequestMapping("teacherPaging")
    public List<Teacher> teacherPaging(String curPage) {
        Teacher teacher = new Teacher();
        teacher.setEndPageSize(5);
        teacher.setStartPageSize((Integer.parseInt(curPage) - 1) * 5);
        List<Teacher> teachers = teacherService.paging(teacher);
        teacher.setEndPageSize(Integer.parseInt(curPage));
        teachers.add(teacher);
        return teachers;
    }

    //teacher上一页
    @RequestMapping("teacherOnTurning")
    public List<Teacher> teacherOnTurning(String onTurning) {
        Teacher teacher = new Teacher();
        teacher.setStartPageSize((Integer.parseInt(onTurning) * 5) - 10 < 0 ? 0 : ((Integer.parseInt(onTurning) * 5) - 10));
        teacher.setEndPageSize(5);
        List<Teacher> teachers = teacherService.paging(teacher);
        teacher.setEndPageSize(Integer.parseInt(onTurning) - 1 == 0 ? 1 : Integer.parseInt(onTurning) - 1);
        teachers.add(teacher);
        return teachers;
    }

    //teacher下一页
    @RequestMapping("teacherDownTurning")
    public List<Teacher> teacherDownTurning(String downTurning) {
        Teacher teacher = new Teacher();
        List<Teacher> counts = teacherService.findAll();
        String value=(BigDecimal.valueOf(counts.size()).divide( BigDecimal.valueOf(Integer.valueOf(5)))).toString();
        int cunSize =0;
        if(value.contains(".")){
            String[] splits = value.split("\\.");
            cunSize = (Integer.valueOf(splits[0])+1);
        }else {
            cunSize = (Integer.valueOf(value));
        }
        teacher.setStartPageSize(Integer.parseInt(downTurning) == cunSize ? (Integer.parseInt(downTurning) - 1) * 5 : (Integer.parseInt(downTurning) * 5));
        teacher.setEndPageSize(5);
        List<Teacher> teachers = teacherService.paging(teacher);
        teacher.setEndPageSize(Integer.parseInt(downTurning) >= cunSize ? (Integer.parseInt(downTurning)) : (Integer.parseInt(downTurning) + 1));
        teachers.add(teacher);
        return teachers;
    }

    //学生删除
    @RequestMapping("studentDelete")
    public Map<Object, Object> studentDelete(String id){
        Map<Object, Object> map = new HashMap<>();
        Student student = new Student();
        student.setSid(Long.parseLong(id));
        student.setStatus(0);
        int row = studentService.delete(student);
        if (row > 0) {
            map.put("ok", true);
        } else {
            map.put("ok", false);
        }
        return map;
    }

    //学生成绩删除
    @RequestMapping("resultsDelete")
    public Map<Object, Object> resultsDelete(String id){
        Map<Object, Object> map = new HashMap<>();
        Resultss resultss = new Resultss();
        resultss.setRid(Long.parseLong(id));
        int row = resultsService2.delete(resultss);
        if (row > 0) {
            map.put("ok", true);
        } else {
            map.put("ok", false);
        }
        return map;
    }

    //student分页
    @RequestMapping("studentPaging")
    public List<Stu_cour> studentPaging(String curPage) {
        Stu_cour stu_cour= new Stu_cour();
        Student student = new Student();
        student.setEndPageSize(5);
        student.setStartPageSize((Integer.parseInt(curPage) - 1) * 5);
        List<Stu_cour> stu_cours = studentService.paging(student);
        student.setEndPageSize(Integer.parseInt(curPage));
        stu_cour.setStudent_id(student);
        stu_cours.add(stu_cour);
        return stu_cours;
    }

    //student上一页
    @RequestMapping("studentOnTurning")
    public List<Stu_cour> studentOnTurning(String onTurning) {
        Stu_cour stu_cour = new Stu_cour();
        Student student = new Student();
        student.setStartPageSize((Integer.parseInt(onTurning) * 5) - 10 < 0 ? 0 : ((Integer.parseInt(onTurning) * 5) - 10));
        student.setEndPageSize(5);
        List<Stu_cour> stu_cours = studentService.paging(student);
        student.setEndPageSize(Integer.parseInt(onTurning) - 1 == 0 ? 1 : Integer.parseInt(onTurning) - 1);
        stu_cour.setStudent_id(student);
        stu_cours.add(stu_cour);
        return stu_cours;
    }

    //student下一页
    @RequestMapping("studentDownTurning")
    public List<Stu_cour> studentDownTurning(String downTurning) {
        Stu_cour stu_cour = new Stu_cour();
        Student student = new Student();
        List<Stu_cour> counts = studentService.findAll();
        String value=(BigDecimal.valueOf(counts.size()).divide( BigDecimal.valueOf(Integer.valueOf(5)))).toString();
        int cunSize =0;
        if(value.contains(".")){
            String[] splits = value.split("\\.");
            cunSize = (Integer.valueOf(splits[0])+1);
        }else {
            cunSize = (Integer.valueOf(value));
        }
        student.setStartPageSize(Integer.parseInt(downTurning) == cunSize ? (Integer.parseInt(downTurning) - 1) * 5 : (Integer.parseInt(downTurning) * 5));
        student.setEndPageSize(5);
        List<Stu_cour> stu_cours = studentService.paging(student);
        student.setEndPageSize(Integer.parseInt(downTurning) >= cunSize ? (Integer.parseInt(downTurning)) : (Integer.parseInt(downTurning) + 1));
        stu_cour.setStudent_id(student);
        stu_cours.add(stu_cour);
        return stu_cours;
    }

    //课程删除
    @RequestMapping("couser2_Delete")
    public Map<String,Object> couser2_Delete(String c2id){
        Map<String,Object> map = new HashMap<>();
        map.put("ok",false);
        Course2 course2 = new Course2();
        course2.setC2id(Long.parseLong(c2id));
        int delete = course2Service.delete(course2);
        if(delete>0){
            map.put("ok",true);
        }
        return map;
    }

    //课程模糊查询
    @RequestMapping("couser2_fallkey")
    public List<Course2> couser2_fallkey(String keyname){
        if(keyname==null ||keyname.equals("")){
            return null;
        }
        Course2 course2 = new Course2();
        course2.setKeyname(keyname);
        List<Course2> dim = course2Service.dim(course2);
        return dim;
    }

    //学生老师课程查询
    @RequestMapping("student_teacher_course")
    public List<Teacher> student_teacher_course(String student_id){
        List<Teacher> list = new ArrayList<>();
        List<Tea_stu> studentAll = tea_stuService.findStudentAll(Long.parseLong(student_id));
        for(Tea_stu tea_stu : studentAll){
            Teacher teacher = new Teacher();
            teacher.setTid(tea_stu.getTeacher_id());
            Teacher byId = teacherService.findById(teacher);
            list.add(byId);
        }
        return list;
    }
}
