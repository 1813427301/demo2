package com.example.demo2.service.serviceDao.impl;

import com.example.demo2.dao.ResultsRepository;
import com.example.demo2.domian.Results;
import com.example.demo2.service.serviceDao.ResultsService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultsServiceImpl2 implements ResultsService2 {

    @Autowired
    private ResultsRepository resultsRepository;

    @Override
    public List<Results> findAll() {
        List<Results> results=(List<Results>) resultsRepository.findAll();
        return results;
    }
}
