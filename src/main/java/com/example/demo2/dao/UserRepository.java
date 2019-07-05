package com.example.demo2.dao;

import com.example.demo2.domian.Course;
import com.example.demo2.domian.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
}
