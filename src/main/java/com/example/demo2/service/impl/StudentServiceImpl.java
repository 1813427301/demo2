package com.example.demo2.service.impl;

import com.example.demo2.domian.Stu_cour;
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
    public Map<Object, Object> create(String sid,String sname,String sage,String sgender,String sid_card,String saddr ) {
        Map<Object, Object> map = new HashMap<>();
        map.put("ok",false);
        if((sid == null || sid.equals("")) ||(sname == null || sname.equals("")) || (sage == null || sage.equals("")) || (sgender == null || sgender.equals("")) || (saddr == null || saddr.equals("")) || (sid_card ==null || sid_card.equals(""))){
            return map;
        }else {
            Student student = new Student();
            student.setSname(sname);
            student.setSage(Integer.parseInt(sage));
            student.setSaddr(saddr);
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
    public List<Stu_cour> findAll() {
        return studentMapper.findAll();
    }

    @Override
    public Student findById(Student student){
        return studentMapper.findById(student);
    }

    @Override
    public Stu_cour findById2(Long student_id) {
        return studentMapper.findById2(student_id);
    }

    @Override
    public int update(String id,String sid,String sname,String sage,String sgender,String sid_card,String saddr) {
        int row = 0;
        if((sid == null || sid.equals("")) ||(sname == null || sname.equals("")) || (sage == null || sage.equals("")) || (sgender == null || sgender.equals("")) || (saddr == null || saddr.equals(""))  || (sid_card ==null || sid_card.equals(""))){
            return row;
        }else {
            Student student = new Student();
            student.setCourse_id(Long.parseLong(sid));
            student.setSid(Long.parseLong(id));
            student.setSgender(sgender);
            student.setSname(sname);
            student.setSaddr(saddr);
            student.setSage(Integer.parseInt(sage));
            student.setSidCard(sid_card);
            row=studentMapper.update(student);
        }
        return row;
    }

    @Override
    public int delete(Student student) {
        return studentMapper.delete(student);
    }

    @Override
    public List<Stu_cour> findByNameKey(String studentDimCheck) {
        return studentMapper.findByNameKey(studentDimCheck);
    }

    @Override
    public List<Stu_cour> paging(Student student) {
        return studentMapper.paging(student);
    }
}
