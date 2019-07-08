package com.example.demo2.mapper;

import com.example.demo2.domian.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultsMapper {

    @Select("SELECT * FROM t_results WHERE rid=#{rid} ")
    Results findById(Long rid);
}
