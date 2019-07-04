package com.example.demo2.dao;

import com.example.demo2.domian.Stu_cour;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface Stu_courRepository extends CrudRepository<Stu_cour, Long> {
}
