package com.example.demo2;

import com.example.demo2.dao.Stu_courRepository;
import com.example.demo2.dao.StudentRepository;
import com.example.demo2.dao.TeacherRepository;
import com.example.demo2.domian.*;
import com.example.demo2.mapper.Stu_ResulMapper;
import com.example.demo2.mapper.TeacherMapper;
import com.example.demo2.mapper.UserMapper;
import com.example.demo2.shiro.md5.ShiroUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo2ApplicationTests {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private Stu_ResulMapper stu_resulMapper;

    @Autowired
    private User user;
    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testUser(){
    }


}
class Example{
    public static void main(String args[]){

    }
}


