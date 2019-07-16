package com.example.demo2.mapper;

import com.example.demo2.domian.Resultss;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultsMapper {

    @Select("SELECT * FROM t_results WHERE rid=#{rid} ")
    Resultss findById(Long rid);

    @Select("SELECT * FROM t_results where rid=#{rid};")
    Resultss findByKey(Long rid);

    @Update("UPDATE t_results SET results=#{results} WHERE rid=#{rid};")
    int update(Resultss resultss);


}
