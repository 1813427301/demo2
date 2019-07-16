package com.example.demo2.service.impl;

import com.example.demo2.dao.Tea_stuRepository;
import com.example.demo2.domian.*;
import com.example.demo2.mapper.*;
import com.example.demo2.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private Tea_stuMapper tea_stuMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private Cour_Cour2Mapper cour_cour2Mapper;

    @Autowired
    private Course2Mapper course2Mapper;

    @Autowired
    private Stu_ResulMapper stu_resulMapper;

    @Autowired
    private ResultsMapper resultsMapper;

    @Override
    public Map<Object, Object> create(String sid, String sname, String sage, String sgender, String sid_card, String saddr) {
        Map<Object, Object> map = new HashMap<>();
        map.put("ok", false);
        if ((sid == null || sid.equals("")) || (sname == null || sname.equals("")) || (sage == null || sage.equals("")) || (sgender == null || sgender.equals("")) || (saddr == null || saddr.equals("")) || (sid_card == null || sid_card.equals(""))) {
            return map;
        } else {
            Student student = new Student();
            student.setSname(sname);
            student.setSage(Integer.parseInt(sage));
            student.setSaddr(saddr);
            student.setSgender(sgender);
            student.setSidCard(sid_card);
            student.setSdate_time(new Timestamp(new Date().getTime()));
            student.setStatus(1);
            int row = studentMapper.create(student);
            if (row > 0) {
                map.put("ok", true);
            }
        }
        return map;
    }

    @Override
    public List<Stu_cour> findAll() {
        return studentMapper.findAll();
    }

    @Override
    public Student findById(Student student) {
        return studentMapper.findById(student);
    }

    @Override
    public Stu_cour findById2(Long student_id) {
        return studentMapper.findById2(student_id);
    }

    @Override
    public int update(String id, String sid, String sname, String sage, String sgender, String sid_card, String saddr) {
        int row = 0;

            if ((sid == null || sid.equals("")) || (sname == null || sname.equals("")) || (sage == null || sage.equals("")) || (sgender == null || sgender.equals("")) || (saddr == null || saddr.equals("")) || (sid_card == null || sid_card.equals(""))) {
                return row;
            } else {
                Student student = new Student();
                student.setCourse_id(Long.parseLong(sid));
                student.setSid(Long.parseLong(id));
                student.setSgender(sgender);
                student.setSname(sname);
                student.setSaddr(saddr);
                student.setSage(Integer.parseInt(sage));
                student.setSidCard(sid_card);
                row = studentMapper.update(student);
                Tea_stu tea_stu = new Tea_stu();
                tea_stu.setStudent_id(student.getSid());
                if (row > 0) {
                    tea_stuMapper.delete(tea_stu);
                    Course course = new Course();
                    course.setCid(Long.parseLong(sid));
                    Teacher teacher = new Teacher();
                    teacher.setCourse(course);
                    List<Teacher> teacherList = teacherMapper.findByCourse(teacher);
                    for (Teacher t : teacherList) {
                        tea_stu.setTeacher_id(t.getTid());
                        row = tea_stuMapper.add(tea_stu);

                    }
                    stu_resulMapper.delete(Long.parseLong(id));
                }
            }
        return row;
    }

    @Override
    public int delete(Student student) {
        User user = new User();
        user.setUstudent(student);
        userMapper.updateStudent(user);
        return studentMapper.delete(student);
    }

    @Override
    public List<Stu_cour> findByNameKey(String studentDimCheck) {
        return studentMapper.findByNameKey(studentDimCheck);
    }

    @Override
    public List<Stu_cour> paging(Student student) {
        return studentMapper.paging(student);
    }
}
