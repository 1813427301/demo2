package com.example.demo2.domian;

import lombok.Data;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "t_results")
@Data
@Repository
public class Resultss {

    @Id //主键注解
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键生成策略
    private Long rid;

    private Double Results;

    private String rname;//课程名

    private Timestamp rdate_time;

}
