package com.example.demo2.dao;

import com.example.demo2.domian.Stu_Resul;
import com.example.demo2.domian.Tea_stu;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Repository
public interface Stu_ResulRepository extends CrudRepository<Stu_Resul, Long> {

}
