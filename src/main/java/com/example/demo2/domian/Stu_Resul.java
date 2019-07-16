package com.example.demo2.domian;

import lombok.Data;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Entity
@Table(name = "t_stu_resul")
@Data
@Repository
public class Stu_Resul {

    @Id //主键注解
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键生成策略
    private Long s_r_id;

    private Long student_id;

    private Long resultss_id;
}
