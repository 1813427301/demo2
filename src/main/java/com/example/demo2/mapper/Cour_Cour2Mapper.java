package com.example.demo2.mapper;

import com.example.demo2.domian.Cour_Cour2;
import com.example.demo2.domian.Course2;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Cour_Cour2Mapper {

    @Select("SELECT * FROM t_course2 ORDER BY c2id DESC")
    List<Cour_Cour2> findAll();


    @Insert("INSERT INTO t_cour_cour2 (course_id,course2_id) VALUES (#{course_id},#{course2_id});")
    int create(Cour_Cour2 cour_cour2);

    @Select("SELECT * FROM t_course2 WHERE c2id=#{c2id} ORDER BY c2id DESC")
    Cour_Cour2 findById(Cour_Cour2 cour_cour2);

    @Select("SELECT * FROM t_cour_cour2 WHERE course_id=#{course_id} ORDER BY c_cid DESC")
    List<Cour_Cour2> findByCourse_id(Cour_Cour2 cour_cour2);

    @Select("SELECT * FROM t_cour_cour2 WHERE course2_id=#{course2_id} ORDER BY c_cid DESC")
    List<Cour_Cour2> findByCourse2_id(Cour_Cour2 cour_cour2);

    @Update("UPDATE t_course2 SET c2name=#{c2name},series2=#{series2} WHERE c2id=#{c2id};")
    int update(Cour_Cour2 cour_cour2);

    @Delete("DELETE FROM t_cour_cour2 WHERE course2_id=#{course2_id}")
    int delete(Cour_Cour2 cour_cour2);

}
