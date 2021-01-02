package com.peterica.swagger.controller;

import com.peterica.swagger.service.TestService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DemoController {

    private final TestService testService;

    @GetMapping("/")
    @ApiOperation(httpMethod = "GET", value = "pingPong", notes = "pingPong",
            response = String.class, tags = "국가 정보")
    public String pong(){
        return "pong";
    }

    @GetMapping("/dbTest")
    @ApiOperation(httpMethod = "GET", value = "Mybatis Test", notes = "Mybatis Test")
    public List<String> dbTest(){
        return testService.getTest();
    }
}
