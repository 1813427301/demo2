package com.example.demo2.service.impl;

import com.example.demo2.domian.Course;
import com.example.demo2.domian.Teacher;
import com.example.demo2.mapper.CourseMapper;
import com.example.demo2.mapper.TeacherMapper;
import com.example.demo2.service.CourseService;
import com.example.demo2.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public List<Teacher> findAll() {
        return teacherMapper.findAll();
    }

    /*@Override
    public Map<Object, Object> create(String Tname, String jiao_course, String Teducation, String Cid) {
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
        int row = teacherMapper.create(teacher);
        if (row > 0) {
            map.put("ok", true);
        } else {
            map.put("ok", false);
        }
        return map;
    }*/

    @Override
    public Teacher findById(Teacher teacher) {
        return teacherMapper.findById(teacher);
    }

    @Override
    public int update(Teacher teacher) {
        int row = teacherMapper.update(teacher);
        return row;
    }

    @Override
    public int updateDelete(Teacher teacher) {
        return teacherMapper.updateDelete(teacher);
    }

    @Override
    public List<Teacher> dim(Teacher keyname) {
        return teacherMapper.dim(keyname);
    }

    @Override
    public List<Teacher> paging(Teacher teacher) {
        return teacherMapper.paging(teacher);
    }
}
