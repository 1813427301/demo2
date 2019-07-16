package com.example.demo2.service.impl;

import com.example.demo2.domian.Stu_Resul;
import com.example.demo2.mapper.Stu_ResulMapper;
import com.example.demo2.service.Stu_ResulService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class Stu_ResulServiceImpl implements Stu_ResulService {

    @Autowired
    private Stu_ResulMapper stu_resulMapper;

    @Override
    public List<Stu_Resul> findALL() {
        return stu_resulMapper.findALL();
    }

    @Override
    public List<Stu_Resul> findByStudent_id(long student_id) {
        return stu_resulMapper.findByStudent_id(student_id);
    }

    @Override
    public List<Stu_Resul> findBy(long resultss_id) {
        return stu_resulMapper.findBy(resultss_id);
    }


}
