package com.example.demo2.controller.manages;

import com.example.demo2.domian.*;
import com.example.demo2.service.*;
import com.example.demo2.service.StudentService;
import com.example.demo2.service.serviceDao.ResultsService2;
import com.example.demo2.service.serviceDao.StudentService2;
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
        Map<String,Student> map =new HashMap<>();
        List<Student> lists = new ArrayList<>();
        List<User> users = userService.findAll();
        for (Student student:studentList){
            int a=0;
            for (User user:users){
                if(user.getUstudent()==null){
                    continue;
                }
                if(student.getSid().equals(user.getUstudent().getSid())||student.getSid()==(user.getUstudent().getSid())){
                    a=1;
                    break;
                }

            }
            if(a==0){
                map.put("student"+student.getSid(),student);
            }
        }
        for (Student value : map.values())
        {
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
        Map<String,Teacher> map =new HashMap<>();
        List<Teacher> lists = new ArrayList<>();
        List<User> users = userService.findAll();
        for (Teacher teacher:teacherList){
            int a=0;
            for (User user:users){
                if(user.getUteacher()==null){
                    continue;
                }
                if(teacher.getTid().equals(user.getUteacher().getTid())||teacher.getTid()==(user.getUteacher().getTid())){
                    a=1;
                    break;
                }

            }
            if(a==0){
                map.put("teacher"+teacher.getTid(),teacher);
            }
        }
        for (Teacher value : map.values())
        {
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
        Map<String,Course> map =new HashMap<>();
        List<Course> lists = new ArrayList<>();
        for (Teacher teacher:teacherList){
            if(teacher.getCourse()==null || teacher.getCourse().equals("")){
                continue;
            }
            for (Course course : courseList){
                if(course.getCid().equals(teacher.getCourse().getCid())|| course.getCid()==(teacher.getCourse().getCid())){
                    map.put(""+course.getCid(),course);
                    break;
                }
                System.out.println(course.toString());

            }
        }
        for (Course value : map.values())
        {
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
        for (Teacher teacher : teacherList) {

            if ((teacher.getCourse().getSeries().equals(stu_cour.getCourse_id().getSeries()))
                    && (teacher.getCourse().getMajor().equals(stu_cour.getCourse_id().getMajor())) &&
                    (teacher.getCourse().getGrade().equals(stu_cour.getCourse_id().getGrade()))) {

                model.addAttribute("teacher", teacher);
                break;
            }
        }
        model.addAttribute("student", stu_cour);
        return "afters/student_see";
    }

    /**
     * 学生成绩列表
     */
    @RequestMapping("student_results")
    public String student_results(Model model) {
        List<Teacher> teacherList = teacherService.findAll();
        List<Resultss> resultssList = resultsService2.findAll();
        List<Stu_cour> courList = new ArrayList<>();
        for (Resultss resultss1 : resultssList){
            Stu_cour byId2 = studentService.findById2(resultss1.getRid());
            courList.add(byId2);
        }
        model.addAttribute("teacherList", teacherList);
        model.addAttribute("resultsList", resultssList);
        model.addAttribute("courList",courList);
        return "afters/student_results";
    }

    /**
     * 学生成绩添加列表
     */
    @RequestMapping("student_results_add")
    public String student_results_add(Model model) {
        List<Teacher> teacherList = teacherService.findAll();
        List<Stu_cour> stu_courList = studentService.findAll();
        Map<String,Stu_cour> map =new HashMap<>();
        List<Stu_cour> lists = new ArrayList<>();
        List<Resultss> resultssList = resultsService2.findAll();
        for (Stu_cour stu_cour:stu_courList){
            if(stu_cour.getStudent_id().getResults()==null){
                map.put("stu_cour"+stu_cour.getStudent_id().getSid(),stu_cour);
                continue;
            }
            for (Resultss resultss : resultssList){
                if(resultss.getRid().equals(stu_cour.getStudent_id().getResults().getRid())|| resultss.getRid()==(stu_cour.getStudent_id().getResults().getRid())){
                    break;
                }

            }
        }
        for (Stu_cour value : map.values())
        {
            lists.add(value);
        }
        System.out.println(lists.toString());
        model.addAttribute("teacherList", teacherList);
        model.addAttribute("stu_courList", lists);
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
        model.addAttribute("courseList", courseList);
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
        model.addAttribute("courseList", courseList);
        model.addAttribute("teacher", teacher1);
        return "afters/updateteacher";
    }

    /**
     * 学生成绩修改页面
     */

    @RequestMapping("student_results_update")
    public String student_results_update(String id, Model model) {
        Student student = new Student();
        student.setSid(Long.parseLong(id));
        Student student1 = studentService.findById(student);
        Stu_cour byId2 = studentService.findById2(student1.getSid());
        Teacher teacher = new Teacher();
        teacher.setCourse(byId2.getCourse_id());
        List<Teacher> teacherList = teacherService.findAll();
        for(Teacher teacher1 : teacherList){
            if(teacher1.getCourse()==null){
                continue;
            }
            if(byId2.getCourse_id().getCid().equals(teacher1.getCourse().getCid())){
                model.addAttribute("teacher", teacher1);
                break;
            }
        }
        model.addAttribute("student", student1);
        model.addAttribute("courList", byId2);

        return "afters/student_results_update";
    }

}
