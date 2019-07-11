package com.example.demo2.service;

import com.example.demo2.domian.Tea_stu;

import java.util.List;

public interface Tea_stuService {
    List<Tea_stu> findAll(Long teacher_id);
}
