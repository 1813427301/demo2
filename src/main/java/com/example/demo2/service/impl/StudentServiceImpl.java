package com.example.demo2.service.impl;

import com.example.demo2.domian.Student;
import com.example.demo2.mapper.StudentMapper;
import com.example.demo2.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Map<Object, Object> create(String grade,String sname,String sage,String sgender,int sid_card,String saddr,String smoajr ) {
        Map<Object, Object> map = new HashMap<>();
        map.put("ok",false);
        if((grade == null || grade.equals("")) ||(sname == null || sname.equals("")) || (sage == null || sage.equals("")) || (sgender == null || sgender.equals("")) || (saddr == null || saddr.equals("")) || (smoajr == null || smoajr.equals("")) || (sid_card == 0 || sid_card==0)){
            return map;
        }else {
            Student student = new Student();
            student.setGrade(grade);
            student.setSname(sname);
            student.setSage(Integer.parseInt(sage));
            student.setSaddr(saddr);
            student.setSmoajr(smoajr);
            student.setSgender(sgender);
            student.setSidCard(sid_card);
            student.setSdate_time(new Timestamp(new Date().getTime()));
            student.setStatus(1);
            int row = studentMapper.create(student);
            if (row>0){
                map.put("ok",true);
            }
        }
        return map;
    }
}
