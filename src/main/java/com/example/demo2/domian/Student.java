package com.example.demo2.domian;

import lombok.Data;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "t_student")
@Data
@Repository
public class Student {
    @Id //主键注解
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键生成策略
    private Long Sid;

    private String Sname;//姓名
    private int Sage;//年龄
    private String Sgender;//性别
    private String SidCard;//身份证
    private String Saddr; //家庭地址
    private String Smoajr;//专业
    private String grade;//班级
    private Timestamp Sdate_time;//创建时间
    private int status;

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
