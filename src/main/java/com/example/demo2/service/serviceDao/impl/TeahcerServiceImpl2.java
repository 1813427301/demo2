package com.example.demo2.service.serviceDao.impl;

import com.example.demo2.dao.TeacherRepository;
import com.example.demo2.domian.Course;
import com.example.demo2.domian.Teacher;

import com.example.demo2.mapper.CourseMapper;
import com.example.demo2.service.CourseService;
import com.example.demo2.service.serviceDao.TeacherService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class TeahcerServiceImpl2 implements TeacherService2 {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public Map<Object, Object> createRepository(String Tname, String jiao_course, String Teducation, String Cid) {
        Map<Object, Object> map = new HashMap<>();
        Teacher teacher = new Teacher();
        Course course = new Course();
        course.setCid(Long.parseLong(Cid));
        teacher.setStatus(1);
        teacher.setTname(Tname);
        teacher.setJiao_course(jiao_course);
        teacher.setTeducation(Teducation);
        teacher.setCourse(course);
        teacher.setTdate_time(new Timestamp(new Date().getTime()));
        Teacher save = teacherRepository.save(teacher);
        if (save !=null) {
            Calendar calendar = Calendar.getInstance();//日历对象
            calendar.setTime(new Date());
            String yearStr = calendar.get(Calendar.YEAR)+"";//获取年份
            int month = calendar.get(Calendar.MONTH) + 1;//获取月份
            String monthStr = month < 10 ? "0" + month : month + "";
            int day = calendar.get(Calendar.DATE);//获取日
            String dayStr = day < 10 ? "0" + day : day + "";
            String xueNumberId="T"+yearStr+monthStr+dayStr+save.getTid();
            Course byGrade = courseMapper.findById(course);
            teacher.setCourse(byGrade);
            teacher.setTid(save.getTid());
            teacher.setXueNumberId(xueNumberId);
            teacherRepository.save(teacher);
            map.put("ok", true);
        } else {
            map.put("ok", false);
        }
        return map;
    }
}
