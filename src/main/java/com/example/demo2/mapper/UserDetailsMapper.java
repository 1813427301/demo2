package com.example.demo2.mapper;

import com.example.demo2.domian.User;
import com.example.demo2.domian.UserDetails;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsMapper {
    @Select("INSERT INTO t_user_details (address,date_birth,phone,synopsis,user_id) VALUES ( #{address}, #{dateBirth},#{phone},#{synopsis},#{user.id})")
     void create(UserDetails userDetails);

    @Select("DELETE FROM t_user_details WHERE user_id=#{user.id}")
    void delete(UserDetails userDetails);

    @Select("SELECT * FROM t_user_details WHERE user_id=#{user.id}")
    @Results({
            @Result(property = "id",  column = "id"),
            @Result(property = "address",  column = "address"),
            @Result(property = "dateBirth", column = "date_birth"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "synopsis", column = "synopsis"),
            @Result(property = "user", column = "user_id",javaType = User.class,one = @One(
                    select="com.example.demo2.mapper.UserMapper.findById"))
    })
    UserDetails findById(UserDetails userDetails);

}
