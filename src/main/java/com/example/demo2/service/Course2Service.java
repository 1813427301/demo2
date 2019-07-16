package com.example.demo2.service;

import com.example.demo2.domian.Course;
import com.example.demo2.domian.Course2;

import java.util.List;
import java.util.Map;

public interface Course2Service {
    Map<Object,Object> create(String series2, String c2name,String cid);
    List<Course2> findAll();
    int update(Course2 course2);
    int delete(Course2 course2);
    Course2 findById(Course2 course2);
    List<Course2> dim(Course2 course2);
}
