package com.peterica.swagger.service;

import com.peterica.swagger.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepository testRepository;

    public List<String> getTest(){
        return testRepository.getTestList();
    }
}
