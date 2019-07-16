package com.example.demo2.service.impl;

import com.example.demo2.domian.Cour_Cour2;
import com.example.demo2.domian.Course;
import com.example.demo2.domian.Course2;
import com.example.demo2.domian.Teacher;
import com.example.demo2.mapper.Cour_Cour2Mapper;
import com.example.demo2.mapper.Course2Mapper;
import com.example.demo2.mapper.TeacherMapper;
import com.example.demo2.service.Course2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class Course2ServiceImpl implements Course2Service {

    @Autowired
    private Course2Mapper course2Mapper;

    @Autowired
    private Cour_Cour2Mapper cour_cour2Mapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public Map<Object, Object> create(String series2, String c2name,String cid) {
        Map<Object, Object> map = new HashMap<>();
        map.put("ok",false);
        if((series2==null ||series2.equals("")) && (c2name==null ||c2name.equals("")) && (cid==null ||cid.equals(""))){
            map.put("error","不能留空");
            return map;
        }else {
            Course2 course2 = new Course2();
            course2.setC2name(c2name);
            course2.setSeries2(series2);

            int row = course2Mapper.create(course2);
            if(row>0){
                Cour_Cour2 cour_cour2 = new Cour_Cour2();
                cour_cour2.setCourse2_id(course2.getC2id());
                cour_cour2.setCourse_id(Long.parseLong(cid));
                 row = cour_cour2Mapper.create(cour_cour2);
                 if(row>0){
                     map.put("course2",course2);
                     map.put("ok",true);
                 }else {
                     course2Mapper.delete(course2);
                 }
            }
        }
        return map;
    }

    @Override
    public List<Course2> findAll() {
        return course2Mapper.findAll();
    }

    @Override
    public int update(Course2 course2) {
        return course2Mapper.update(course2);
    }

    @Override
    public int delete(Course2 course2) {
        Cour_Cour2 cour_cour2 = new Cour_Cour2();
        cour_cour2.setCourse2_id(course2.getC2id());
        List<Cour_Cour2> byCourse_id = cour_cour2Mapper.findByCourse2_id(cour_cour2);
        cour_cour2Mapper.delete(cour_cour2);
        Teacher teacher = new Teacher();
        Course course = new Course();
        course.setCid(byCourse_id.get(0).getCourse_id());
        teacher.setCourse(course);
        teacherMapper.updatet_course2(teacher);
        return course2Mapper.delete(course2);
    }

    @Override
    public Course2 findById(Course2 course2) {
        return course2Mapper.findById(course2);
    }

    @Override
    public List<Course2> dim(Course2 course2) {
        return course2Mapper.dim(course2);
    }
}
