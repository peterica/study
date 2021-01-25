package com.peterica.swagger.controller;

import com.peterica.swagger.service.TestService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DemoController {

    @Value("${base.module.elementToSearch}")
    private String[] elementToSearch;

    @Value("#{'${base.module.elementToSearch2}'.split(';')}")
    private String[] elementToSearch2;

    @GetMapping("/elementAsArray")
    @ApiOperation(httpMethod = "GET", value = "properties Array element Test", notes = "properties Test", tags = "테스트")
    public String elementAsArray(){

        // 1. 기본 쉼표로 Array 가져오기
        for (String element : elementToSearch){
            System.out.println(element);
        }

        // 2. ';'로 Array 가져오기
        for (String element : elementToSearch2){
            System.out.println(element);
        }

        return "suc";
    }

    private final TestService testService;

    @GetMapping("/")
    @ApiOperation(httpMethod = "GET", value = "pingPong", notes = "pingPong",
            response = String.class, tags = "테스트")
    public String pong(){
        return "pong";
    }

    @GetMapping("/dbTest")
    @ApiOperation(httpMethod = "GET", value = "Mybatis Test", notes = "Mybatis Test", tags = "테스트")
    public List<String> dbTest(){
        return testService.getTest();
    }


}
