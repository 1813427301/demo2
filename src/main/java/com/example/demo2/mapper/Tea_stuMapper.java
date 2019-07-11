package com.example.demo2.mapper;

import com.example.demo2.domian.Tea_stu;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Tea_stuMapper {
    @Select("SELECT * FROM t_tea_stu WHERE teacher_id=#{teacher_id}")
    List<Tea_stu> findAll(Long teacher_id);
}
