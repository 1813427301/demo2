package com.example.demo2.mapper;

import com.example.demo2.domian.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentMapper {
    @Insert("INSERT INTO t_student ( sage, sgender, sname, sidCard,saddr,sdate_time,smoajr) VALUES (#{sage}, #{sgender}, #{sname},#{sidCard},#{saddr}, #{sdate_time}, #{smoajr});")
    int create(Student student);
}
