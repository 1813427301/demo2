package com.example.demo2.service.serviceDao;

import com.example.demo2.domian.Student;

import java.util.List;
import java.util.Map;

public interface StudentService2 {
    Map<Object,Object> create(String sid,String sname,String sage,String sgender,String sid_card,String saddr);
    List<Student> findAll();
    Student findById(Student student);
    int update(String id,String sid,String sname,String sage,String sgender,String sid_card,String saddr);
    int delete(Student student);

}
