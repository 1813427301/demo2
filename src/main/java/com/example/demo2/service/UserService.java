package com.example.demo2.service;

import com.example.demo2.domian.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<User> findAll();
    User findByid(Long id);
    Map<String ,Object> create(String username,String password,String password2,String email,String city);
    Map<String ,Object> login(String username,String password);
    Map<String, Object>  updateUser(Long id,String username,String password,String password2,String email,String city,String date);
    Map<String, Object>  updateImg(String finalImg,String id);
    Map<String, Object>  deleteUser(Long id);
    List<User> dim(User keyname);//关键字查询
    List<User> paging(User user);//分页查询
}
