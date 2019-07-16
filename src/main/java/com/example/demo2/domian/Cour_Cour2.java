package com.example.demo2.domian;

import lombok.Data;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Entity
@Table(name = "t_cour_cour2")
@Data
@Repository
public class Cour_Cour2 {
    @Id //主键注解
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键生成策略
    private Long c_cid;

    private Long course_id;

    private Long course2_id;
}
