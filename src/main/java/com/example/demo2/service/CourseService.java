package com.example.demo2.service;

import com.example.demo2.domian.Course;

import java.util.List;
import java.util.Map;

public interface CourseService {
    List<Course> findAll();
    Map<Object,Object> create(String series,String major, String grade,String course,String cid);
    Course findByGrade(Course course);
    int update(Course course);
    int delete(Course course);
}
