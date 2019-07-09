package com.example.demo2.mapper;

import com.example.demo2.domian.Resultss;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultsMapper {

    @Select("SELECT * FROM t_results WHERE rid=#{rid} ")
    Resultss findById(Long rid);

    @Select("SELECT * FROM t_results where rid=#{rid};")
    Resultss findByKey(Long rid);
}
