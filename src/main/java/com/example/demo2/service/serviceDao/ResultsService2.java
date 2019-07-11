package com.example.demo2.service.serviceDao;

import com.example.demo2.domian.Resultss;

import java.util.List;
import java.util.Map;

public interface ResultsService2 {

    List<Resultss> findAll();
    Map<String, Object> create(String sid, String results);
    Resultss findByKey(String key);
    int delete(Resultss resultss);
}
