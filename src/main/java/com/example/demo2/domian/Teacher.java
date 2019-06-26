package com.example.demo2.domian;

import lombok.Data;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_teacher")
@Data
@Repository
public class Teacher {
    @Id //主键注解
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键生成策略
    private Long Tid;
    private String Tname;

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

}
