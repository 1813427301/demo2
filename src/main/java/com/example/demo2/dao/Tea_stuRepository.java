package com.example.demo2.dao;

import com.example.demo2.domian.Tea_stu;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface Tea_stuRepository extends CrudRepository<Tea_stu, Long> {
}
