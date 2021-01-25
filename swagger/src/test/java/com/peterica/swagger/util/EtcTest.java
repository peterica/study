package com.peterica.swagger.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource("classpath:application-test.properties")
public class EtcTest {
    @Value("${base.module.elementToSearch}")
    private String[] elementToSearch;

    @Test
    public void getElementFromPropertyAsArray(){
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(elementToSearch);
    }




}
