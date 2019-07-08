package com.example.demo2.service.serviceDao.impl;

import com.example.demo2.dao.ResultsRepository;
import com.example.demo2.domian.Results;
import com.example.demo2.domian.Student;
import com.example.demo2.mapper.StudentMapper;
import com.example.demo2.service.StudentService;
import com.example.demo2.service.serviceDao.ResultsService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ResultsServiceImpl2 implements ResultsService2 {

    @Autowired
    private ResultsRepository resultsRepository;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Results> findAll() {
        List<Results> results=(List<Results>) resultsRepository.findAll();
        return results;
    }

    @Override
    public Map<String, Object> create(String sid, String results) {
        Map<String,Object> map = new HashMap<>();
        map.put("ok",false);
        map.put("error","添加成绩失败！");
        Results results1 = new Results();
        results1.setRid(Long.parseLong(sid));
        results1.setResults(Double.valueOf(results));
        results1.setRdate_time(new Timestamp(new Date().getTime()));
        Results results2 = resultsRepository.save(results1);
        if(results2!=null){
            Student student = new Student();
            student.setSid(results2.getRid());
            student.setResults(results2);
            int row = studentMapper.updateResults(student);
            if(row>0){
                map.put("ok",true);
                map.put("error","添加成绩成功！");
            }else {
                resultsRepository.delete(results2);
            }

        }
        return map;
    }
}
