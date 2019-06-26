package com.example.demo2.dao;

import com.example.demo2.domian.UserDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



public interface UserDetailsRepository extends CrudRepository<UserDetails, Long> {
}
