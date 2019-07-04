package com.example.demo2.dao;

import com.example.demo2.domian.Results;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultsRepository extends CrudRepository<Results,Long> {
}
