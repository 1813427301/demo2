package com.example.demo2.mapper;

import com.example.demo2.domian.Course;
import com.example.demo2.domian.Teacher;
import com.example.demo2.domian.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherMapper {

    @Select("SELECT * FROM t_teacher where status=1 ORDER BY tdate_time DESC")
    @Results({
            @Result(property = "tid",  column = "tid"),
            @Result(property = "Tname",  column = "tname"),
            @Result(property = "tgrade", column = "tgrade"),
            @Result(property = "teducation", column = "teducation"),
            @Result(property = "tdate_time", column = "tdate_time"),
            @Result(property = "status", column = "status"),
            @Result(property = "course", column = "tcourse_id",javaType = Course.class,many = @Many(
                    select="com.example.demo2.mapper.CourseMapper.findById"))
    })
    List<Teacher> findAll();

    @Insert("INSERT INTO t_teacher (tname,tcourse_id,tgrade,teducation,tdate_time,status) VALUES (#{tname}, #{course.cid}, #{tgrade}, #{teducation}, #{tdate_time}, #{status});")
    int create(Teacher teacher);

    @Select("SELECT * FROM t_teacher WHERE tid=#{tid} and status=1")
    @Results({
            @Result(property = "tid",  column = "tid"),
            @Result(property = "Tname",  column = "tname"),
            @Result(property = "tgrade", column = "tgrade"),
            @Result(property = "teducation", column = "teducation"),
            @Result(property = "tdate_time", column = "tdate_time"),
            @Result(property = "status", column = "status"),
            @Result(property = "course", column = "tcourse_id",javaType = Course.class,many = @Many(
                    select="com.example.demo2.mapper.CourseMapper.findById"))
    })
    Teacher findById(Teacher teacher);

    @Update("UPDATE t_teacher SET tname=#{tname}, tcourse_id=#{course.Cid}, tgrade=#{tgrade}, teducation=#{teducation} WHERE tid=#{tid};")
    int update(Teacher teacher);
    @Update("UPDATE t_teacher SET  status=#{status} WHERE tid=#{tid};")
    int updateDelete(Teacher teacher);

    @Select("SELECT * FROM t_teacher where tname like '%${keyname}%' and status=1;")
    @Results({
            @Result(property = "tid",  column = "tid"),
            @Result(property = "Tname",  column = "tname"),
            @Result(property = "tgrade", column = "tgrade"),
            @Result(property = "teducation", column = "teducation"),
            @Result(property = "tdate_time", column = "tdate_time"),
            @Result(property = "status", column = "status"),
            @Result(property = "course", column = "tcourse_id",javaType = Course.class,many = @Many(
                    select="com.example.demo2.mapper.CourseMapper.findById"))
    })
    List<Teacher> dim(Teacher keyname);
}
