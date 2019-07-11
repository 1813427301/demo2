package com.example.demo2.service.impl;

import com.example.demo2.domian.User;
import com.example.demo2.domian.UserDetails;
import com.example.demo2.mapper.UserDetailsMapper;
import com.example.demo2.mapper.UserMapper;
import com.example.demo2.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;


@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDetailsMapper userDetailsMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Map<Object,Object> insert(String uid, String dateBirth, String phone, String address, String synopsis, String email) {
        Map<Object,Object> map = new HashMap<>();
            map.put("ok",false);
            UserDetails userDetails = new UserDetails();
            userDetails.setId(Long.parseLong(uid));
            User user = new User();
            user.setId(Long.parseLong(uid));
            user.setEmail(email);
            userDetails.setUser(user);
            userDetails.setDateBirth(dateBirth);
            userDetails.setPhone(phone);
            userDetails.setAddress(address);
            userDetails.setSynopsis(synopsis);
            int row = userDetailsMapper.delete(userDetails);
             row = userDetailsMapper.create(userDetails);
            UserDetails us = userDetailsMapper.findById(userDetails);
            row = userMapper.updateEmail(user);
        if(row>0){
                map.put("ok",true);
                map.put("userDetails",us);
            }

        return map;
    }

    @Override
    public int delete(UserDetails userDetails) {
        return userDetailsMapper.delete(userDetails);
    }

    @Override
    public UserDetails findById(UserDetails userDetails) {
        return userDetailsMapper.findById(userDetails);
    }

}
