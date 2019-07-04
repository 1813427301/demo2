package com.example.demo2;

import com.example.demo2.dao.Stu_courRepository;
import com.example.demo2.dao.StudentRepository;
import com.example.demo2.dao.TeacherRepository;
import com.example.demo2.domian.*;
import com.example.demo2.mapper.UserMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo2ApplicationTests {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testUser() {
        Course course = new Course();
        course.setCname("dfsdf");
        course.setGrade("dfsf");
        course.setMajor("dsfdf");
        course.setSeries("dfsfd");
        Student student = new Student();
        student.setSage(15);
        student.setStatus(1);
        List list =new ArrayList();
        list.add(course);
        student.setCourseList(list);
        Student save = studentRepository.save(student);
        System.out.println(save);
    }
}
