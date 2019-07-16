package com.example.demo2.mapper;

import com.example.demo2.domian.Cour_Cour2;
import com.example.demo2.domian.Stu_Resul;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Stu_ResulMapper {

    @Select("SELECT * FROM t_stu_resul where resultss_id=#{resultss_id}")
    List<Stu_Resul> findBy(long resultss_id);

    @Select("SELECT * FROM t_stu_resul where student_id=#{student_id}")
    List<Stu_Resul> findByStudent_id(long student_id);

    @Select("SELECT * FROM t_stu_resul")
    List<Stu_Resul> findALL();

    @Delete("DELETE t_stu_resul,t_results FROM t_stu_resul LEFT OUTER JOIN t_results ON t_stu_resul.resultss_id = t_results.rid WHERE t_stu_resul.student_id=#{student_id};")
    int delete(Long student_id);
}
