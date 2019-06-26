package com.example.demo2.domian;

import lombok.Data;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Entity
@Table(name = "t_user_details")
@Data
@Repository
public class UserDetails {
    @Id //主键注解
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键生成策略
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    private String dateBirth;
    private String phone;
    private String address;
    private String synopsis;
}
