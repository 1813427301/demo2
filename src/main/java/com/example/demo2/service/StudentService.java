package com.example.demo2.service;

import com.example.demo2.domian.Stu_cour;
import com.example.demo2.domian.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {
    Map<Object,Object> create(String sid, String sname, String sage, String sgender, String sid_card, String saddr);
    List<Stu_cour> findAll();
    Student findById(Student student);
    Stu_cour findById2(Long student_id);
    int update(String id, String sid, String sname, String sage, String sgender, String sid_card, String saddr);
    int delete(Student student);
    List<Stu_cour> findByNameKey(String studentDimCheck);
    List<Stu_cour> paging(Student student);
}
