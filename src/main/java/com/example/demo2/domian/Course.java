package com.example.demo2.domian;

import lombok.Data;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_course")
@Data
@Repository
public class Course {
    @Id //主键注解
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键生成策略
    private Long Cid;

    private String Cname;
    private String series;

    private String major;

    @Column(name = "grade")
    private String grade;

    @ManyToMany(mappedBy = "courseList",cascade=CascadeType.MERGE)
    private List<Student> studentList;

    @OneToMany(mappedBy = "course",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private List<Teacher> CteacherList;
}
