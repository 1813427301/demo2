package com.example.demo2.service;

import com.example.demo2.domian.Course;

import java.util.Map;

public interface CourseService {
    Map<Object,Object> create(String college,String series,String major, String grade,String course);
    Course findByGrade(Course course);
    int update(Course course);
}
