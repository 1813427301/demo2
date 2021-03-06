package com.example.demo2.domian;

import lombok.Data;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    private String xueNumberId;//学号

    @Column(name = "sid_card", unique = true, nullable = false)
    private String SidCard;//身份证

    private String Saddr; //家庭地址
    private Timestamp Sdate_time;//创建时间
    private int status;

    @ManyToMany(cascade=CascadeType.MERGE)
    @JoinTable(name = "t_stu_cour",
            joinColumns = {@JoinColumn(name = "studentId",referencedColumnName = "Sid")},
            inverseJoinColumns=@JoinColumn(name="courseId",referencedColumnName = "Cid"))
    private List<Course> courseList;


    @OneToOne(mappedBy = "ustudent", cascade = {
            CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    private User Suser;

    @Transient
    private Long course_id;

    @Transient
    private Long student_id;

    @Transient
    private String studentDimCheck;

    @Transient
    private int startPageSize;

    @Transient
    private int endPageSize;


    @Override
    public String toString() {
        return "Student{" +
                "Sid=" + Sid +
                ", Sname='" + Sname + '\'' +
                ", Sage=" + Sage +
                ", Sgender='" + Sgender + '\'' +
                ", SidCard='" + SidCard + '\'' +
                ", Saddr='" + Saddr + '\'' +
                ", Sdate_time=" + Sdate_time +
                ", status=" + status +
                ", course_id=" + course_id +
                ", student_id=" + student_id +
                ", xueNumberId=" + xueNumberId +
                ", studentDimCheck='" + studentDimCheck + '\'' +
                ", startPageSize=" + startPageSize +
                ", endPageSize=" + endPageSize +
                '}';
    }
}
