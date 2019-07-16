package com.example.demo2.service.impl;

import com.example.demo2.domian.Tea_stu;
import com.example.demo2.mapper.Tea_stuMapper;
import com.example.demo2.service.Tea_stuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Tea_stuServiceImpl implements Tea_stuService {

    @Autowired
    private Tea_stuMapper tea_stuMapper;

    @Override
    public List<Tea_stu> findAll(Long teacher_id) {
        return tea_stuMapper.findAll(teacher_id);
    }

    @Override
    public List<Tea_stu> findStudentAll(Long student_id) {
        return tea_stuMapper.findStudentAll(student_id);
    }
}
