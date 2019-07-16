package com.example.demo2.service;

import com.example.demo2.domian.Stu_Resul;

import java.util.List;

public interface Stu_ResulService {
    List<Stu_Resul> findALL();
    List<Stu_Resul> findByStudent_id(long student_id);
    List<Stu_Resul> findBy(long resultss_id);
}
