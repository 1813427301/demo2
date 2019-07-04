package com.example.demo2.domian;

import lombok.Data;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "t_results")
@Data
@Repository
public class Results {

    @Id //主键注解
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键生成策略
    private Long rid;

    @OneToOne(mappedBy = "results", cascade = CascadeType.MERGE)
    private Student student;

    private Double Results;

}
