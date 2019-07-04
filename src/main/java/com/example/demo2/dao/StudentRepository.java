package com.example.demo2.dao;

import com.example.demo2.domian.Course;
import com.example.demo2.domian.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student,Long> {
}
