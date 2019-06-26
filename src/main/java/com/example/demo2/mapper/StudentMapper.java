package com.example.demo2.mapper;

import com.example.demo2.domian.Student;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentMapper {
    @Select("INSERT INTO t_student (sid,age, gender, name, phone) VALUES (#{sid},#{age}, #{gender}, #{name}, #{phone});")
    void create(Student student);
}
