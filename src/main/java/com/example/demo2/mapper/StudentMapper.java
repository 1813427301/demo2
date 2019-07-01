package com.example.demo2.mapper;

import com.example.demo2.domian.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper {
    @Insert("INSERT INTO t_student ( sage, sgender, sname, sid_Card,saddr,sdate_time,smoajr,status,grade) VALUES (#{sage}, #{sgender}, #{sname},#{sidCard},#{saddr}, #{sdate_time}, #{smoajr},#{status},#{grade});")
    int create(Student student);

    @Select("SELECT * FROM t_student where status=1 ORDER BY sdate_time DESC")
    @Results({
            @Result(property = "id",  column = "id"),
            @Result(property = "sname",  column = "sname"),
            @Result(property = "sidCard", column = "sid_Card"),
            @Result(property = "saddr", column = "saddr"),
            @Result(property = "sgender", column = "sgender"),
            @Result(property = "sdate_time", column = "sdate_time"),
            @Result(property = "smoajr", column = "smoajr"),
            @Result(property = "status", column = "status"),
            @Result(property = "grade", column = "grade")
    })
    List<Student> findAll();

    @Select("SELECT * FROM t_student where sid=#{sid} and status=1 ORDER BY sdate_time DESC")
    @Results({
            @Result(property = "id",  column = "id"),
            @Result(property = "sname",  column = "sname"),
            @Result(property = "sidCard", column = "sid_Card"),
            @Result(property = "saddr", column = "saddr"),
            @Result(property = "sgender", column = "sgender"),
            @Result(property = "sdate_time", column = "sdate_time"),
            @Result(property = "smoajr", column = "smoajr"),
            @Result(property = "status", column = "status"),
            @Result(property = "grade", column = "grade")
    })
    Student findById(Student student);

    @Update("UPDATE t_student SET saddr=#{saddr},sage=#{sage},sgender=#{sgender},sid_card=#{sidCard},smoajr=#{smoajr},sname=#{sname},grade=#{grade} WHERE sid=#{sid};")
    int update(Student student);

    @Update("UPDATE t_student SET status=#{status} WHERE sid=#{sid};")
    int delete(Student student);
}
