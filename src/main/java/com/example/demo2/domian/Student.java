package com.example.demo2.domian;

import lombok.Data;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_student")
@Data
@Repository
public class Student {
    @Id //主键注解
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键生成策略
    private Long Sid;

    private String Sonid;
    private String Sname;
    private int Sage;
    private String Sgender;

    @ManyToMany(cascade=CascadeType.PERSIST)
    @JoinTable(name = "t_stu_cour",
            joinColumns = {@JoinColumn(name = "studentId")},
            inverseJoinColumns=@JoinColumn(name="courseId"))
    private List<Course> courseList;

    @ManyToMany(mappedBy = "TstudentList")
    private List<Teacher> SteacherList;

    @OneToOne(mappedBy = "Ustudent", cascade = {
            CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    private User Suser;
}
