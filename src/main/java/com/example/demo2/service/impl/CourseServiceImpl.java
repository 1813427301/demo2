package com.example.demo2.service.impl;

import com.example.demo2.domian.Cour_Cour2;
import com.example.demo2.domian.Course;
import com.example.demo2.domian.Course2;
import com.example.demo2.domian.Teacher;
import com.example.demo2.mapper.Cour_Cour2Mapper;
import com.example.demo2.mapper.Course2Mapper;
import com.example.demo2.mapper.CourseMapper;
import com.example.demo2.mapper.TeacherMapper;
import com.example.demo2.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private Course2Mapper course2Mapper;

    @Autowired
    private Cour_Cour2Mapper cour_cour2Mapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public List<Course> findAll() {
        return courseMapper.findAll();
    }

    @Override
    public Map<Object, Object> create( String series, String major, String grade,String cid) {
        Map<Object, Object> map = new HashMap<>();
        map.put("ok",false);
            Course course = new Course();
            course.setCid(Long.parseLong(cid));
            course.setMajor(major);
            course.setGrade(grade);
            course.setSeries(series);
            Course byGrade = findByGrade(course);
            if (byGrade == null) {
                try {
                    courseMapper.create(course);
                    map.put("ok", true);
                } catch (Exception e) {
                }
            } else {
                try {
                    if (!course.getCid().equals("0") || course.getCid() != 0) {
                        int rom = update(course);
                        map.put("row", rom);
                    } else {
                        int row = delete(course);
                        map.put("row", row);
                    }
                } catch (Exception e) {
                    map.put("row", "0");
                }
            }
        return map;
    }

    @Override
    public Course findByGrade(Course course) {
        return courseMapper.findByMajor(course);
    }

    @Override
    public int update(Course course) {

        int row = courseMapper.update(course);

        return row;
    }

    @Override
    public int delete(Course course) {
        Course2 course2 = new Course2();

        Cour_Cour2 cour_cour2 = new Cour_Cour2();
        cour_cour2.setCourse_id(course.getCid());
        List<Cour_Cour2> cour_cour2List = cour_cour2Mapper.findByCourse_id(cour_cour2);
        for( Cour_Cour2 courCour2 :cour_cour2List){
            course2.setC2id(courCour2.getCourse2_id());
            course2Mapper.delete(course2);

            cour_cour2.setCourse2_id(course2.getC2id());
            cour_cour2Mapper.delete(cour_cour2);
        }
        Teacher teacher = new Teacher();
        teacher.setCourse(course);
        teacherMapper.updatet_course(teacher);
        return courseMapper.delete(course);
    }
}
