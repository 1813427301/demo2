package com.example.demo2.domian;

import lombok.Data;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.Transient;
import javax.xml.soap.Text;
import java.sql.Timestamp;

@Entity
@Table(name = "t_user")
@Data
@Repository
public class User {
    @Id //主键注解
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键生成策略
    private Long id;

    @Column(name = "uname", length = 32, unique = true, nullable = false)
    private String username;

    @Column(name = "upass", length = 32, nullable = false)
    private String password;

    @Column(name = "sex", length = 32, nullable = false)
    private String sex;

    @Column(name = "url_head", length = 16777216)
    private String urlHead;

    @Column(name = "create_time", nullable = false)
    private Timestamp createTime;

    @Column(name = "type", nullable = false)
    private int type;

    @Column(name = "status", nullable = false)
    private int status;

    @Column(name = "salt", nullable = false)
    private String salt;

    @Column(name = "email", nullable = false)
    private String email;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserDetails userDetails;

    @OneToOne(cascade = CascadeType.ALL)//People是关系的维护端，当删除 people，会级联删除 address
    @JoinColumn(name = "Ustudent_id", referencedColumnName = "Sid")//people中的address_id字段参考address表中的id字段
    private Student Ustudent;

    @OneToOne(cascade = CascadeType.ALL)//People是关系的维护端，当删除 people，会级联删除 address
    @JoinColumn(name = "Uteacher_id", referencedColumnName = "Tid")
    private Teacher Uteacher;

    @Transient
    private String keyname;

    @Transient
    private int startPageSize;

    @Transient
    private int endPageSize;


}