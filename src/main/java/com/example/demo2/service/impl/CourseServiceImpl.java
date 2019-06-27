package com.example.demo2.service.impl;

import com.example.demo2.domian.Course;
import com.example.demo2.mapper.CourseMapper;
import com.example.demo2.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> findAll() {
        return courseMapper.findAll();
    }

    @Override
    public Map<Object, Object> create(String college, String series, String major, String grade, String course1,String cid) {
        Map<Object, Object> map = new HashMap<>();
        map.put("ok",false);
        Course course = new Course();
        course.setCid(Long.parseLong(cid));
        course.setCollege(college);
        course.setMajor(major);
        course.setGrade(grade);
        course.setCname(course1);
        course.setSeries(series);
        Course byGrade = findByGrade(course);
        if(byGrade==null){
            try {
                courseMapper.create(course);
                map.put("ok",true);
            }catch (Exception e){
            }
        }else {
            try {
                if(!course.getCid().equals("0")||course.getCid()!=0){
                    int rom = update(course);
                    map.put("row",rom);
                }else {
                    int row = delete(course);
                    map.put("row",row);
                }
            }catch (Exception e){
                map.put("row","0");
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
        return courseMapper.delete(course);
    }
}
