package com.example.demo2.mapper;

import com.example.demo2.domian.Course;
import com.example.demo2.domian.Student;
import com.example.demo2.domian.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface CourseMapper {

    @Select("SELECT * FROM t_course ORDER BY cid DESC")
    List<Course> findAll();

    @Insert("INSERT INTO t_course (cname,college,grade,major,series) VALUES (#{cname}, #{college}, #{grade}, #{major}, #{series});")
    int create(Course course);

    @Select("SELECT * FROM t_course WHERE grade=#{grade} ORDER BY cid DESC")
    Course findByMajor(Course course);

    @Select("SELECT * FROM t_course WHERE cid=#{cid} ORDER BY cid DESC")
    Course findById(Course course);

    @Update("UPDATE t_course SET cname=#{cname}, college=#{college}, major=#{major},series=#{series} WHERE grade=#{grade};")
    int update(Course course);

    @Delete("DELETE FROM t_course WHERE cid=#{cid}")
    int delete(Course course);
}
