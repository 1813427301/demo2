package com.example.demo2.service;

import com.example.demo2.domian.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {
    Map<Object,Object> create(String grade,String sname,String sage,String sgender,String sid_card,String saddr,String smoajr );
    List<Student> findAll();
    Student findById(Student student);
    int update(String id,String grade,String sname,String sage,String sgender,String sid_card,String saddr,String smoajr);
    int delete(Student student);
}
