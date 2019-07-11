package com.example.demo2.service.serviceDao.impl;

import com.example.demo2.dao.ResultsRepository;
import com.example.demo2.domian.Resultss;
import com.example.demo2.domian.Student;
import com.example.demo2.mapper.ResultsMapper;
import com.example.demo2.mapper.StudentMapper;
import com.example.demo2.service.serviceDao.ResultsService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class ResultsServiceImpl2 implements ResultsService2 {

    @Autowired
    private ResultsRepository resultsRepository;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private ResultsMapper resultsMapper;

    @Override
    public List<Resultss> findAll() {
        List<Resultss> results=(List<Resultss>) resultsRepository.findAll();
        return results;
    }

    @Override
    public Map<String, Object> create(String sid, String results) {
        Map<String,Object> map = new HashMap<>();
        map.put("ok",false);
        map.put("error","添加成绩失败！");
        Resultss resultss1 = new Resultss();
        resultss1.setRid(Long.parseLong(sid));
        resultss1.setResults(Double.valueOf(results));
        resultss1.setRdate_time(new Timestamp(new Date().getTime()));
        Resultss resultss2 = resultsRepository.save(resultss1);
        if(resultss2 !=null){
            Student student = new Student();
            student.setSid(resultss2.getRid());
            student.setResults(resultss2);
            int row = studentMapper.updateResults(student);
            if(row>0){
                map.put("ok",true);
                map.put("error","添加成绩成功！");
            }else {
                resultsRepository.delete(resultss2);
            }

        }
        return map;
    }

    @Override
    public Resultss findByKey(String key) {
        return resultsMapper.findByKey(Long.parseLong(key));
    }

    @Override
    public int delete(Resultss resultss) {
        int row =1;
        try {
            resultsRepository.delete(resultss);
        }catch (Exception e){
            row=0;
        }
        return row;
    }
}
