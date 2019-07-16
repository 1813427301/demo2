package com.example.demo2.mapper;

import com.example.demo2.domian.Course;
import com.example.demo2.domian.Course2;
import com.example.demo2.domian.Teacher;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Course2Mapper {

    @Select("SELECT * FROM t_course2 ORDER BY c2id DESC")
    List<Course2> findAll();

    @Insert("INSERT INTO t_course2 (c2name,series2) VALUES (#{c.c2name},#{c.series2});")
    @Options(useGeneratedKeys = true,keyProperty = "c.c2id",keyColumn = "c2id")
    int create(@Param("c")Course2 course2);

    @Select("SELECT * FROM t_course2 WHERE c2id=#{c2id} ORDER BY c2id DESC")
    Course2 findById(Course2 course2);

    @Update("UPDATE t_course2 SET c2name=#{c2name},series2=#{series2} WHERE c2id=#{c2id};")
    int update(Course2 course2);

    @Delete("DELETE FROM t_course2 WHERE c2id=#{c2id}")
    int delete(Course2 course2);

    @Select("SELECT * FROM t_course2 where series2 like '%${keyname}%';")
    List<Course2> dim(Course2 course2);
}
