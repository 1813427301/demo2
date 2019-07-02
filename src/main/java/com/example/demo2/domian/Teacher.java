package com.example.demo2.domian;

import lombok.Data;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "t_teacher")
@Data
@Repository
public class Teacher {
    @Id //主键注解
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键生成策略
    private Long Tid;

    @Column(name = "Tname", nullable = false , unique = true)
    private String Tname;

    private String jiao_course;//授课
    private String Teducation;//学历
    private Timestamp Tdate_time;//创建时间
    private int status;//状态

    @ManyToMany(cascade= CascadeType.PERSIST)
    @JoinTable(name = "t_tea_stu",
            joinColumns = {@JoinColumn(name = "teacher_Id")},
            inverseJoinColumns=@JoinColumn(name="student_Id"))
    private List<Student> TstudentList;

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
    @JoinColumn(name="Tcourse_id")
    private Course course;

    @OneToOne(mappedBy = "Uteacher", cascade = {
            CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    private User Tuser;


    @Transient
    private String keyname;
}
