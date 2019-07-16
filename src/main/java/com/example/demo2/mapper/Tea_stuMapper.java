package com.example.demo2.mapper;

import com.example.demo2.domian.Tea_stu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Tea_stuMapper {
    @Select("SELECT * FROM t_tea_stu WHERE teacher_id=#{teacher_id}")
    List<Tea_stu> findAll(Long teacher_id);

    @Select("SELECT * FROM t_tea_stu WHERE student_id=#{student_id}")
    List<Tea_stu> findStudentAll(Long student_id);

    @Insert("INSERT INTO t_tea_stu (student_id,teacher_id) VALUES (#{student_id}, #{teacher_id});")
    int add(Tea_stu tea_stu);

    @Delete("DELETE FROM t_tea_stu WHERE student_id=#{student_id}")
    int delete(Tea_stu tea_stu);
}
