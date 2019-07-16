package com.example.demo2.service;

import com.example.demo2.domian.Course;
import com.example.demo2.domian.Teacher;
import com.example.demo2.domian.User;

import java.util.List;
import java.util.Map;

public interface TeacherService {
    List<Teacher> findAll();
    Teacher findById(Teacher teacher);
    List<Teacher> findByCourse(Teacher teacher);
    int update(Teacher teacher);
    int updateDelete(Teacher teacher);
    List<Teacher> dim(Teacher keyname);
    List<Teacher> paging(Teacher teacher);
}
