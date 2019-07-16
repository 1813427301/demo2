package com.example.demo2.service.serviceDao.impl;

import com.example.demo2.dao.ResultsRepository;
import com.example.demo2.dao.Stu_ResulRepository;
import com.example.demo2.dao.StudentRepository;
import com.example.demo2.domian.Resultss;
import com.example.demo2.domian.Stu_Resul;
import com.example.demo2.domian.Student;
import com.example.demo2.mapper.ResultsMapper;
import com.example.demo2.mapper.Stu_ResulMapper;
import com.example.demo2.mapper.StudentMapper;
import com.example.demo2.service.serviceDao.ResultsService2;
import com.example.demo2.service.serviceDao.StudentService2;
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
    private Stu_ResulRepository stu_resulRepository;

    @Autowired
    private ResultsMapper resultsMapper;

    @Autowired
    private Stu_ResulMapper stu_resulMapper;


    @Override
    public List<Resultss> findAll() {
        List<Resultss> results = (List<Resultss>) resultsRepository.findAll();
        return results;
    }

    @Override
    public Map<String, Object> create(String sid, String results, String rname) {
        Map<String, Object> map = new HashMap<>();
        map.put("ok", false);
        map.put("error", "添加成绩失败！");
        Resultss resultss1 = new Resultss();
        resultss1.setResults(Double.valueOf(results));
        resultss1.setRdate_time(new Timestamp(new Date().getTime()));
        resultss1.setRname(rname);
        Resultss resultss2 = resultsRepository.save(resultss1);
        Stu_Resul stu_resul = new Stu_Resul();
        stu_resul.setStudent_id(Long.parseLong(sid));
        stu_resul.setResultss_id(resultss2.getRid());
        Stu_Resul save = stu_resulRepository.save(stu_resul);
        if (save != null) {
            map.put("ok", true);
            map.put("error", "添加成绩成功！");
        }
        return map;
    }

    @Override
    public Resultss findByKey(String key) {
        return resultsMapper.findByKey(Long.parseLong(key));
    }

    @Override
    public int delete(Resultss resultss) {
        int row = 1;
        try {
            List<Stu_Resul> by = stu_resulMapper.findBy(resultss.getRid());
            for(Stu_Resul stu_resul:by){
                stu_resulRepository.delete(stu_resul);
            }
            resultsRepository.delete(resultss);
        } catch (Exception e) {
            row = 0;
        }
        return row;
    }

    @Override
    public Resultss findById(Long rid) {
        return resultsMapper.findById(rid);
    }

    @Override
    public int update(String sid,String results) {
        Resultss resu = new Resultss();
        resu.setRid(Long.valueOf(sid));
        resu.setResults(Double.valueOf(results));
        return resultsMapper.update(resu);
    }
}
