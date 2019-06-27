package com.example.demo2.mapper;

import com.example.demo2.domian.Course;
import com.example.demo2.domian.Student;
import com.example.demo2.domian.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface CourseMapper {

    @Select("SELECT * FROM t_course")
    List<Course> findAll();

    @Select("INSERT INTO t_course (cname,college,grade,major,series) VALUES (#{cname}, #{college}, #{grade}, #{major}, #{series});")
    void create(Course course);

    @Select("SELECT * FROM t_course WHERE grade=#{grade}")
    Course findByMajor(Course course);

    @Select("UPDATE t_course SET cname=#{cname}, college=#{college}, major=#{major},series=#{series} WHERE grade=#{grade};")
    void update(Course course);

    @Select("DELETE FROM t_course WHERE cid=#{cid}")
    void delete(Course course);
}
