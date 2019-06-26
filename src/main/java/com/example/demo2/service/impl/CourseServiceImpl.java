package com.example.demo2.service.impl;

import com.example.demo2.domian.Course;
import com.example.demo2.mapper.CourseMapper;
import com.example.demo2.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public Map<Object, Object> create(String college, String series, String major, String grade, String course1) {
        Map<Object, Object> map = new HashMap<>();
        map.put("ok",false);
        Course course = new Course();
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
            int row = update(course);
            map.put("row",row);
        }
        return map;
    }

    @Override
    public Course findByGrade(Course course) {
        return courseMapper.findByMajor(course);
    }

    @Override
    public int update(Course course) {
        int row = 0;
        try {
            courseMapper.update(course);
            row = 1;
        }catch (Exception e){
        }
        return row;
    }
}
