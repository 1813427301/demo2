package com.example.demo2.service.impl;

import com.example.demo2.mapper.StudentMapper;
import com.example.demo2.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Map<Object, Object> create() {
        Map<Object, Object> map = new HashMap<>();
        return map;
    }
}
