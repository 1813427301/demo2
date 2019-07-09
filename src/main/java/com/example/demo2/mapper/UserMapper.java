package com.example.demo2.mapper;

import com.example.demo2.domian.Course;
import com.example.demo2.domian.Student;
import com.example.demo2.domian.Teacher;
import com.example.demo2.domian.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper{
    @Select("SELECT * FROM t_user where status=1 ORDER BY create_time DESC")
    @Results({
            @Result(property = "id",  column = "id"),
            @Result(property = "username",  column = "uname"),
            @Result(property = "password", column = "upass"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "sex", column = "sex"),
            @Result(property = "urlHead", column = "url_head"),
            @Result(property = "type", column = "type"),
            @Result(property = "status", column = "status"),
            @Result(property = "salt", column = "salt"),
            @Result(property = "ustudent", column = "ustudent_id",javaType = Student.class,one = @One(select = "com.example.demo2.mapper.StudentMapper.findById")),
            @Result(property = "uteacher", column = "uteacher_id",javaType = Teacher.class,one = @One(select = "com.example.demo2.mapper.TeacherMapper.findById"))
    })
    List<User> findAll();
    @Select("SELECT * FROM t_user WHERE uname=#{username} and status=1 ")
    @Results({
            @Result(property = "id",  column = "id"),
            @Result(property = "username",  column = "uname"),
            @Result(property = "password", column = "upass"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "sex", column = "sex"),
            @Result(property = "urlHead", column = "url_head"),
            @Result(property = "type", column = "type"),
            @Result(property = "status", column = "status"),
            @Result(property = "salt", column = "salt"),
            @Result(property = "ustudent", column = "ustudent_id",javaType = Student.class,one = @One(select = "com.example.demo2.mapper.StudentMapper.findById")),
            @Result(property = "uteacher", column = "uteacher_id",javaType = Teacher.class,one = @One(select = "com.example.demo2.mapper.TeacherMapper.findById"))
    })
    User findByName(String username);

    @Insert("INSERT INTO t_user (id,create_time,email,upass,sex,status,type,url_head,uname,salt,ustudent_id) VALUES (null, #{createTime}, #{email}, #{password}, #{sex}, #{status}, #{type}, #{urlHead}, #{username},#{salt},#{ustudent.Sid});")
    int create(User user);

    @Insert("INSERT INTO t_user (id,create_time,email,upass,sex,status,type,url_head,uname,salt,uteacher_id) VALUES (null, #{createTime}, #{email}, #{password}, #{sex}, #{status}, #{type}, #{urlHead}, #{username},#{salt},#{uteacher.tid});")
    int teacherCreate(User user);

    @Insert("INSERT INTO t_user (id,create_time,email,upass,sex,status,type,url_head,uname,salt) VALUES (null, #{createTime}, #{email}, #{password}, #{sex}, #{status}, #{type}, #{urlHead}, #{username},#{salt});")
    int adminCreate(User user);

    @Select("SELECT * FROM t_user WHERE id=#{id} and status=1 ")
    @Results({
            @Result(property = "id",  column = "id"),
            @Result(property = "username",  column = "uname"),
            @Result(property = "password", column = "upass"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "sex", column = "sex"),
            @Result(property = "urlHead", column = "url_head"),
            @Result(property = "type", column = "type"),
            @Result(property = "status", column = "status"),
            @Result(property = "salt", column = "salt"),
            @Result(property = "ustudent", column = "ustudent_id",javaType = Student.class,one = @One(select = "com.example.demo2.mapper.StudentMapper.findById")),
            @Result(property = "uteacher", column = "uteacher_id",javaType = Teacher.class,one = @One(select = "com.example.demo2.mapper.TeacherMapper.findById"))
    })
    User findById(long id);

    @Update("UPDATE t_user SET create_time=#{createTime}, email=#{email}, upass=#{password}, salt=#{salt}, sex=#{sex}, status=#{status}, type=#{type}, url_head=#{urlHead}, uname=#{username} WHERE (id=#{id})")
    int update(User user);

    @Update("UPDATE t_user SET  status=#{status},ustudent_id=null,uteacher_id=null WHERE (id=#{id})")
    int update2(User user);

    @Update("UPDATE t_user SET url_head=#{urlHead} WHERE (id=#{id})")
    int updateImg(User user);

    @Select("SELECT * FROM t_user where uname like '%${keyname}%' and status=1;")
    @Results({
            @Result(property = "id",  column = "id"),
            @Result(property = "username",  column = "uname"),
            @Result(property = "password", column = "upass"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "sex", column = "sex"),
            @Result(property = "urlHead", column = "url_head"),
            @Result(property = "type", column = "type"),
            @Result(property = "status", column = "status"),
            @Result(property = "salt", column = "salt")
    })
    List<User> dim(User keyname);

    @Select("select * from t_user where status=1 ORDER BY create_time DESC limit #{startPageSize},#{endPageSize};")
    @Results({
            @Result(property = "id",  column = "id"),
            @Result(property = "username",  column = "uname"),
            @Result(property = "password", column = "upass"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "sex", column = "sex"),
            @Result(property = "urlHead", column = "url_head"),
            @Result(property = "type", column = "type"),
            @Result(property = "status", column = "status"),
            @Result(property = "salt", column = "salt")
    })
    List<User> paging(User user);
}
