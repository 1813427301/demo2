package com.example.demo2.service;

import com.example.demo2.domian.UserDetails;

import java.sql.SQLException;
import java.util.Map;

public interface UserDetailsService {
     Map<Object,Object> insert(String uid, String dateBirth, String phone, String address, String synopsis);
     int delete(UserDetails userDetails);
     UserDetails findById(UserDetails userDetails);
}
