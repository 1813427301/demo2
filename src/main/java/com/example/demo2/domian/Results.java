package com.example.demo2.domian;

import lombok.Data;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Map;

@Entity
@Table(name = "t_results")
@Data
@Repository
public class Results {

    @Id //主键注解
    private Long rid;

    @OneToOne(mappedBy = "results", cascade = CascadeType.MERGE)
    private Student student;

    private Double Results;

    private Timestamp rdate_time;

}
