package com.example.demo2.controller.manages;

import com.example.demo2.domian.*;
import com.example.demo2.service.*;
import com.example.demo2.service.StudentService;
import com.example.demo2.service.serviceDao.ResultsService2;
import com.example.demo2.service.serviceDao.StudentService2;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/afterss")
public class UrlController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentService2 studentService2;

    @Autowired
    private ResultsService2 resultsService2;

    @Autowired
    private User user;

    @Autowired
    private Course2Service course2Service;

    @Autowired
    private Tea_stuService tea_stuService;

    @Autowired
    private Stu_ResulService stu_resulService;

    //进入后台首页
    @GetMapping("index")
    public String index() {
        return "afters/index";
    }

    //进入欢迎页面
    @GetMapping("calendar")
    public String calendar() {
        return "afters/conn/calendar";
    }


    @GetMapping("add_role")
    public String add_role() {
        return "afters/add_role";
    }

    //管理员个人中心
    @GetMapping("user_center")
    public String user_center(String id, Model model) {
        User user = new User();
        user.setId(Long.parseLong(id));
        UserDetails userDetails = new UserDetails();
        userDetails.setUser(user);
        UserDetails userDetails1 = userDetailsService.findById(userDetails);
        model.addAttribute("userDetails", userDetails1);
        return "afters/user_center";
    }

    //用户列表
    @RequestMapping("userlist")
    public String rolelist(Model model) {
        List<User> users = userService.findAll();
        user.setStartPageSize(0);
        user.setEndPageSize(5);
        int count = (users.size() % 5) == 0 ? (users.size() / 5) : (users.size() / 5) + 1;
        List<User> userlist = userService.paging(user);
        List<Integer> counts = new ArrayList<Integer>();
        for (int i = 0; i < count; i++) {
            counts.add(i + 1);
        }
        model.addAttribute("users", userlist);
        model.addAttribute("counts", counts);
        return "afters/userlist";
    }

    /**
     * 管理添加管理员账号
     *
     * @param model
     * @return
     */
    @RequestMapping("adminuser")
    public String adminuser(Model model) {
        return "afters/adminuser";
    }


    /**
     * 管理添加学生账号
     *
     * @param model
     * @return
     */
    @RequestMapping("adduser")
    public String adduser(Model model) {
        List<Student> studentList = studentService2.findAll();
        Map<String, Student> map = new HashMap<>();
        List<Student> lists = new ArrayList<>();
        List<User> users = userService.findAll();
        for (Student student : studentList) {
            int a = 0;
            for (User user : users) {
                if (user.getUstudent() == null) {
                    continue;
                }
                if (student.getSid().equals(user.getUstudent().getSid()) || student.getSid() == (user.getUstudent().getSid())) {
                    a = 1;
                    break;
                }

            }
            if (a == 0) {
                map.put("student" + student.getSid(), student);
            }
        }
        for (Student value : map.values()) {
            lists.add(value);
        }
        model.addAttribute("studentList", lists);
        return "afters/adduser";
    }

    /**
     * 管理添加老师账号
     *
     * @param model
     * @return
     */
    @RequestMapping("teacheruser")
    public String teacheruser(Model model) {
        List<Teacher> teacherList = teacherService.findAll();
        Map<String, Teacher> map = new HashMap<>();
        List<Teacher> lists = new ArrayList<>();
        List<User> users = userService.findAll();
        for (Teacher teacher : teacherList) {
            int a = 0;
            for (User user : users) {
                if (user.getUteacher() == null) {
                    continue;
                }
                if (teacher.getTid().equals(user.getUteacher().getTid()) || teacher.getTid() == (user.getUteacher().getTid())) {
                    a = 1;
                    break;
                }

            }
            if (a == 0) {
                map.put("teacher" + teacher.getTid(), teacher);
            }
        }
        for (Teacher value : map.values()) {
            lists.add(value);
        }
        model.addAttribute("teacherList", lists);
        return "afters/teacheruser";
    }


    /**
     * 管理修改用户
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("updateuser")
    public String updateuser(String id, Model model) {
        User user_up = userService.findByid(Long.parseLong(id));
        model.addAttribute("user_up", user_up);
        return "afters/updateuser";
    }

    /**
     * 学生列表页面
     *
     * @return
     */
    @RequestMapping("studentlist")
    public String studentlist(Model model) {
        Student student = new Student();
        List<Stu_cour> stu_courList = studentService.findAll();
        student.setStartPageSize(0);
        student.setEndPageSize(5);
        int count = (stu_courList.size() % 5) == 0 ? (stu_courList.size() / 5) : (stu_courList.size() / 5) + 1;
        List<Stu_cour> studentList = studentService.paging(student);
        List<Integer> counts = new ArrayList<Integer>();
        for (int i = 0; i < count; i++) {
            counts.add(i + 1);
        }
        model.addAttribute("counts", counts);
        model.addAttribute("studentList", studentList);
        return "afters/studentlist";
    }

    //进入学生添加页面
    @RequestMapping("student_add")
    public String student_add(Model model) {
        List<Course> courseList = courseService.findAll();//专业与班级
        List<Teacher> teacherList = teacherService.findAll();
        Map<String, Course> map = new HashMap<>();
        List<Course> lists = new ArrayList<>();
        for (Teacher teacher : teacherList) {
            if (teacher.getCourse() == null || teacher.getCourse().equals("")) {
                continue;
            }
            for (Course course : courseList) {
                if (course.getCid().equals(teacher.getCourse().getCid()) || course.getCid() == (teacher.getCourse().getCid())) {
                    map.put("" + course.getCid(), course);
                    break;
                }
                System.out.println(course.toString());

            }
        }
        for (Course value : map.values()) {
            lists.add(value);
        }
        model.addAttribute("courseList", lists);
        return "afters/student_add";
    }

    /**
     * 修改学生页面
     */
    @RequestMapping("updatestudent")
    public String updatestudent(String id, Model model) {
        Student student = new Student();
        student.setSid(Long.parseLong(id));
        Stu_cour S = studentService.findById2(Long.parseLong(id));
        List<Course> courseList = courseService.findAll();//专业与班级

        model.addAttribute("courseList", courseList);
        model.addAttribute("student", S);
        return "afters/updatestudent";
    }

    /**
     * 进入学生查看课程页面
     *
     * @return
     */
    @RequestMapping("student_see")
    public String student_see(String id, Model model) {
        Stu_cour stu_cour = studentService.findById2(Long.parseLong(id));
        List<Teacher> teacherList = teacherService.findAll();
        List<Teacher> list = new ArrayList<>();
        for (Teacher teacher : teacherList) {

            if ((teacher.getCourse().getSeries().equals(stu_cour.getCourse_id().getSeries()))
                    && (teacher.getCourse().getMajor().equals(stu_cour.getCourse_id().getMajor())) &&
                    (teacher.getCourse().getGrade().equals(stu_cour.getCourse_id().getGrade()))) {
                list.add(teacher);
                continue;
            }
        }
        model.addAttribute("teacherList", list);
        model.addAttribute("student", stu_cour);
        return "afters/student_see";
    }

    /**
     * 学生成绩列表
     */
    @RequestMapping("student_results")
    public String student_results(Model model) {
        List<Teacher> teacherList = teacherService.findAll();
        List<Map<Stu_cour, Map<Resultss, Teacher>>> ss = new ArrayList<>();
        List<Stu_Resul> stu_resulList = stu_resulService.findALL();
        for (Stu_Resul stu_resul : stu_resulList) {
            Resultss byId = resultsService2.findById(stu_resul.getResultss_id());
            Stu_cour byId2 = studentService.findById2(stu_resul.getStudent_id());
            Map<Stu_cour, Map<Resultss, Teacher>> map = new HashMap<>();
            Map<Resultss, Teacher> map2 = new HashMap<>();
            for (Teacher teacher : teacherList) {
                if (byId2.getCourse_id().getCid().equals(teacher.getCourse().getCid()) && byId.getRname().equals(teacher.getJiao_course())) {
                    map2.put(byId, teacher);
                    map.put(byId2, map2);
                    ss.add(map);
                    break;
                }
            }

        }
        System.out.println(ss.toString());
        model.addAttribute("teacherList", teacherList);
        model.addAttribute("studentresulList", ss);
        return "afters/student_results";
    }

    /**
     * 学生成绩添加列表
     */
    @RequestMapping("student_results_add")
    public String student_results_add(Model model) {
        List<Teacher> teacherList = teacherService.findAll();
        List<Stu_cour> stu_courList = studentService.findAll();
        Map<Object, Map<Stu_cour, List<Teacher>>> map = new HashMap<>();
        Map<Stu_cour, List<Teacher>> map_stu_cour = new HashMap<>();
        List<Teacher> lists = new ArrayList<>();

        List<Stu_Resul> stu_resulServiceALL = stu_resulService.findALL();
        for (Stu_cour stu_cours : stu_courList) {
            if(stu_resulServiceALL.toString().equals("[]")){
                List<Tea_stu> tea_stuList = tea_stuService.findStudentAll(stu_cours.getStudent_id().getSid());
                for (Tea_stu tea_stu : tea_stuList) {
                    Teacher teacher = new Teacher();
                    teacher.setTid(tea_stu.getTeacher_id());
                    Teacher teacher1 = teacherService.findById(teacher);
                    lists.add(teacher1);
                }
                map_stu_cour.put(stu_cours, lists);
                map.put("stu", map_stu_cour);

            }else {
                List<Stu_Resul> byStudent_id = stu_resulService.findByStudent_id(stu_cours.getStudent_id().getSid());
                if(byStudent_id.toString().equals("[]")){
                    List<Tea_stu> tea_stuList = tea_stuService.findStudentAll(stu_cours.getStudent_id().getSid());
                    for(Tea_stu tea_stu:tea_stuList){
                        Teacher teacher = new Teacher();
                        teacher.setTid(tea_stu.getTeacher_id());
                        Teacher teacher1 = teacherService.findById(teacher);
                        lists.add(teacher1);
                    }
                    map_stu_cour.put(stu_cours, lists);
                    map.put("stu", map_stu_cour);
                }
            }

        }
        System.out.println(map.toString());
        model.addAttribute("map", map);
        return "afters/student_results_add";
    }

    /**
     * 进入课程添加页面
     *
     * @return
     */
    @RequestMapping("course_add")
    public String course_add(Model model) {
        List<Course> courseList = courseService.findAll();
        model.addAttribute("courseList", courseList);
        return "afters/course_add";
    }

    /**
     * 进入老师页面
     */
    @RequestMapping("teacherlist")
    public String teacherlist(Model model) {
        Teacher teacher = new Teacher();
        List<Teacher> teacherList = teacherService.findAll();
        teacher.setStartPageSize(0);
        teacher.setEndPageSize(5);
        int count = (teacherList.size() % 5) == 0 ? (teacherList.size() / 5) : (teacherList.size() / 5) + 1;
        List<Teacher> userlist = teacherService.paging(teacher);
        List<Integer> counts = new ArrayList<Integer>();
        for (int i = 0; i < count; i++) {
            counts.add(i + 1);
        }
        model.addAttribute("counts", counts);
        model.addAttribute("teacherList", userlist);
        return "afters/teacherlist";
    }

    /**
     * 进入老师添加页面
     */
    @RequestMapping("teacher_add")
    public String teacher_add(Model model) {
        List<Course> courseList = courseService.findAll();
        List<Course2> course2List = course2Service.findAll();
        model.addAttribute("courseList", courseList);
        model.addAttribute("course2List", course2List);
        return "afters/teacher_add";
    }

    /**
     * 进入老师修改页面
     */
    @RequestMapping("updateteacher")
    public String updateteacher(String id, Model model) {
        Teacher teacher = new Teacher();
        teacher.setTid(Long.parseLong(id));
        Teacher teacher1 = teacherService.findById(teacher);
        List<Course> courseList = courseService.findAll();
        List<Course2> course2List = course2Service.findAll();
        model.addAttribute("course2List", course2List);
        model.addAttribute("courseList", courseList);
        model.addAttribute("teacher", teacher1);
        return "afters/updateteacher";
    }

    /**
     * 学生成绩修改页面
     */

    @RequestMapping("student_results_update")
    public String student_results_update(String id,String rid,String rname,Model model) {
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
        Resultss service2ById = resultsService2.findById(Long.parseLong(rid));
        Stu_cour stu_cour = studentService.findById2(Long.parseLong(id));
        model.addAttribute("student", stu_cour);
        model.addAttribute("service2ById", service2ById);
        return "afters/student_results_update";
    }


    //课程添加页
    @RequestMapping("couser_ad")
    public String couser_ad(Model model) {
        List<Course> courseList = courseService.findAll();
        Map<Object, Object> map = new HashMap<>();
        for (Course course : courseList) {
            map.put(course.getSeries(), course);
        }
        model.addAttribute("courseList", map);
        return "afters/couser_ad";
    }

    //课程页面
    @RequestMapping("couserlist")
    public String couserlist(Model model) {
        List<Course2> course2List = course2Service.findAll();
        model.addAttribute("course2List", course2List);
        return "afters/couserlist";
    }

    //课程修改页面
    @RequestMapping("couser_update")
    public String couser_update(String c2id, Model model) {
        Course2 course2 = new Course2();
        course2.setC2id(Long.parseLong(c2id));
        Course2 byId = course2Service.findById(course2);

        List<Course> courseList = courseService.findAll();
        Map<Object, Object> map = new HashMap<>();
        for (Course course : courseList) {
            map.put(course.getSeries(), course.getSeries());
        }
        model.addAttribute("course2", byId);
        model.addAttribute("courseList", map);
        return "afters/couser_update";
    }
}
