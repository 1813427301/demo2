package com.example.demo2.service.serviceDao.impl;

import com.example.demo2.dao.StudentRepository;
import com.example.demo2.domian.Course;
import com.example.demo2.domian.Student;
import com.example.demo2.mapper.CourseMapper;
import com.example.demo2.service.serviceDao.StudentService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;

@Repository
@Transactional
public class StudentServiceImpl2 implements StudentService2 {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public Map<Object, Object> create(String sid, String sname, String sage, String sgender, String sid_card, String saddr) {
        Map<Object, Object> map = new HashMap<>();
        map.put("ok", false);
        if ((sid == null || sid.equals("")) || (sname == null || sname.equals("")) || (sage == null || sage.equals("")) || (sgender == null || sgender.equals("")) || (saddr == null || saddr.equals("")) || (sid_card == null || sid_card.equals(""))) {
            return map;
        } else {
            Course course = new Course();
            course.setCid(Long.parseLong(sid));
            Course course1 = courseMapper.findById(course);
            List<Course> list = new ArrayList<>();
            list.add(course1);
            Student student = new Student();
            student.setCourseList(list);
            student.setSname(sname);
            student.setSage(Integer.parseInt(sage));
            student.setSaddr(saddr);
            student.setSgender(sgender);
            student.setSidCard(sid_card);
            student.setSdate_time(new Timestamp(new Date().getTime()));
            student.setStatus(1);
            Student save = studentRepository.save(student);
            if (save != null) {
                map.put("ok", true);
            }

        }
        return map;
    }

    @Override
    public List<Student> findAll() {
        List<Student> studentList = (List<Student>)studentRepository.findAll();
        System.out.println(studentList.toString());
        return studentList;
    }

    @Override
    public Student findById(Student student) {
        return null;
    }

    @Override
    public int update(String id, String sid, String sname, String sage, String sgender, String sid_card, String saddr) {

        if ((sid == null || sid.equals("")) || (sname == null || sname.equals("")) || (sage == null || sage.equals("")) || (sgender == null || sgender.equals("")) || (saddr == null || saddr.equals("")) || (sid_card == null || sid_card.equals(""))) {
            return 0;
        } else {
            Course course = new Course();
            course.setCid(Long.parseLong(sid));
            Course course1 = courseMapper.findById(course);
            List<Course> list = new ArrayList<>();
            list.add(course1);
            Student student = new Student();
            student.setCourseList(list);
            student.setSid(Long.parseLong(id));
            student.setSname(sname);
            student.setSage(Integer.parseInt(sage));
            student.setSaddr(saddr);
            student.setSgender(sgender);
            student.setSidCard(sid_card);
            student.setSdate_time(new Timestamp(new Date().getTime()));
            student.setStatus(1);
            Student save = studentRepository.save(student);
            if (save != null) {
                return 1;
            }

        }
        return 0;
    }

    @Override
    public int delete(Student student) {
        return 0;
    }
}
