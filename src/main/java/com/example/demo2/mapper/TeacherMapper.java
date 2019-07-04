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
            @Result(property = "jiao_course", column = "jiao_course"),
            @Result(property = "teducation", column = "teducation"),
            @Result(property = "tdate_time", column = "tdate_time"),
            @Result(property = "status", column = "status"),
            @Result(property = "course", column = "tcourse_id",javaType = Course.class,many = @Many(
                    select="com.example.demo2.mapper.CourseMapper.findById"))
    })
    List<Teacher> findAll();

    @Insert("INSERT INTO t_teacher (jiao_course,tname,tcourse_id,teducation,tdate_time,status) VALUES (#{jiao_course},#{tname}, #{course.cid}, #{teducation}, #{tdate_time}, #{status});")
    int create(Teacher teacher);

    @Select("SELECT * FROM t_teacher WHERE tid=#{tid} and status=1")
    @Results({
            @Result(property = "tid",  column = "tid"),
            @Result(property = "Tname",  column = "tname"),
            @Result(property = "jiao_course", column = "jiao_course"),
            @Result(property = "teducation", column = "teducation"),
            @Result(property = "tdate_time", column = "tdate_time"),
            @Result(property = "status", column = "status"),
            @Result(property = "course", column = "tcourse_id",javaType = Course.class,many = @Many(
                    select="com.example.demo2.mapper.CourseMapper.findById"))
    })
    Teacher findById(Teacher teacher);

    @Update("UPDATE t_teacher SET tname=#{tname}, tcourse_id=#{course.Cid}, jiao_course=#{jiao_course}, teducation=#{teducation} WHERE tid=#{tid};")
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


    @Select("select * from t_teacher where status=1 ORDER BY Tdate_time DESC limit #{startPageSize},#{endPageSize};")
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
    List<Teacher> paging(Teacher teacher);
}
