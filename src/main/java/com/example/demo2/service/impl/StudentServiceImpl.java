package com.example.demo2.service.impl;

import com.example.demo2.domian.Student;
import com.example.demo2.mapper.StudentMapper;
import com.example.demo2.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Map<Object, Object> create(String grade,String sname,String sage,String sgender,String sid_card,String saddr,String smoajr ) {
        Map<Object, Object> map = new HashMap<>();
        map.put("ok",false);
        if((grade == null || grade.equals("")) ||(sname == null || sname.equals("")) || (sage == null || sage.equals("")) || (sgender == null || sgender.equals("")) || (saddr == null || saddr.equals("")) || (smoajr == null || smoajr.equals("")) || (sid_card ==null || sid_card.equals(""))){
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

    @Override
    public List<Student> findAll() {
        return studentMapper.findAll();
    }

    @Override
    public Student findById(Student student){
        return studentMapper.findById(student);
    }

    @Override
    public int update(String id,String grade,String sname,String sage,String sgender,String sid_card,String saddr,String smoajr) {
        int row = 0;
        if((grade == null || grade.equals("")) ||(sname == null || sname.equals("")) || (sage == null || sage.equals("")) || (sgender == null || sgender.equals("")) || (saddr == null || saddr.equals("")) || (smoajr == null || smoajr.equals("")) || (sid_card ==null || sid_card.equals(""))){
            return row;
        }else {
            Student student = new Student();
            student.setSid(Long.parseLong(id));
            student.setSgender(sgender);
            student.setGrade(grade);
            student.setSname(sname);
            student.setSaddr(saddr);
            student.setSage(Integer.parseInt(sage));
            student.setSidCard(sid_card);
            student.setSmoajr(smoajr);
            row=studentMapper.update(student);
        }

        return row;
    }

    @Override
    public int delete(Student student) {
        return studentMapper.delete(student);
    }
}
