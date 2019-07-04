package com.example.demo2.domian;

import lombok.Data;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Entity
@Table(name = "t_course")
@Data
@Repository
public class Stu_cour {

    @Id //主键注解
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键生成策略
    private Long Cid;

    @Transient
    private Student student_id;

    @Transient
    private Course course_id;


}
