package com.example.demo2.service;

import com.example.demo2.domian.Course;
import com.example.demo2.domian.Teacher;
import com.example.demo2.domian.User;

import java.util.List;
import java.util.Map;

public interface TeacherService {
    List<Teacher> findAll();
    Map<Object,Object> create(String Tname, String Tgrade, String Teducation, String Cid);
    Teacher findById(Teacher teacher);
    int update(Teacher teacher);
    int updateDelete(Teacher teacher);
    List<Teacher> dim(Teacher keyname);
}
