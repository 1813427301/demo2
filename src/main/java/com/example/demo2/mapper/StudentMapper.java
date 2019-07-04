package com.example.demo2.mapper;

import com.example.demo2.domian.Stu_cour;
import com.example.demo2.domian.Student;
import com.example.demo2.domian.Teacher;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper {
    @Insert("INSERT INTO t_student ( sage, sgender, sname, sid_Card,saddr,sdate_time,status) VALUES (#{sage}, #{sgender}, #{sname},#{sidCard},#{saddr}, #{sdate_time},#{status});")
    int create(Student student);

    @Select("SELECT * FROM t_stu_cour ORDER BY student_id DESC")
    @Results({
            @Result(property = "student_id",  column = "student_id",many = @Many(select = "com.example.demo2.mapper.StudentMapper.findById")),
            @Result(property = "course_id",  column = "course_id",many = @Many(select = "com.example.demo2.mapper.CourseMapper.findById")),
    })
    List<Stu_cour> findAll();

    @Select("SELECT * FROM t_student where sid=#{sid} and status=1 ORDER BY sdate_time DESC")
    @Results({
            @Result(property = "sid",  column = "sid"),
            @Result(property = "sname",  column = "sname"),
            @Result(property = "sidCard", column = "sid_Card"),
            @Result(property = "saddr", column = "saddr"),
            @Result(property = "sgender", column = "sgender"),
            @Result(property = "sdate_time", column = "sdate_time"),
            @Result(property = "status", column = "status"),
    })
    Student findById(Student student);

    @Select("SELECT * FROM t_stu_cour where student_id=#{student_id}")
    @Results({
            @Result(property = "student_id",  column = "student_id",many = @Many(select = "com.example.demo2.mapper.StudentMapper.findById")),
            @Result(property = "course_id",  column = "course_id",many = @Many(select = "com.example.demo2.mapper.CourseMapper.findById")),
    })
    Stu_cour findById2(Long student_id);

    @Update("UPDATE t_stu_cour t, t_student tt SET tt.saddr=#{saddr},tt.sage=#{sage},tt.sgender=#{sgender},tt.sid_card=#{sidCard},tt.sname=#{sname},t.course_id=#{course_id} WHERE tt.sid=#{sid} and t.student_id=#{sid};")
    int update(Student student);

    @Delete("DELETE t_student,t_stu_cour FROM t_student LEFT JOIN t_stu_cour ON t_student.sid=t_stu_cour.student_id WHERE t_student.sid=#{sid};")
    int delete(Student student);

    @Select("SELECT * FROM t_stu_cour where student_id like '%${_parameter}%' ORDER BY student_id DESC")
    @Results({
            @Result(property = "student_id",  column = "student_id",many = @Many(select = "com.example.demo2.mapper.StudentMapper.findById")),
            @Result(property = "course_id",  column = "course_id",many = @Many(select = "com.example.demo2.mapper.CourseMapper.findById")),
    })
    List<Stu_cour> findByNameKey(String studentDimCheck);


    @Select("select * from t_stu_cour ORDER BY student_id DESC limit #{startPageSize},#{endPageSize};")
    @Results({
            @Result(property = "student_id",  column = "student_id",many = @Many(select = "com.example.demo2.mapper.StudentMapper.findById")),
            @Result(property = "course_id",  column = "course_id",many = @Many(select = "com.example.demo2.mapper.CourseMapper.findById")),
    })
    List<Stu_cour> paging(Student student);
}
