package com.example.demo2.domian;

import lombok.Data;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Entity
@Table(name = "t_course2")
@Data
@Repository
public class Course2 {

    @Id //主键注解
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键生成策略
    private Long c2id;

    private String c2name;

    private String series2;

    @Transient
    private String keyname;
}
