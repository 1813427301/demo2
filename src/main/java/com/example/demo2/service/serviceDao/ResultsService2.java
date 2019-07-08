package com.example.demo2.service.serviceDao;

import com.example.demo2.domian.Results;

import java.util.List;
import java.util.Map;

public interface ResultsService2 {

    List<Results> findAll();
    Map<String, Object> create(String sid, String results);
}
