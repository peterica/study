package com.peterica.swagger.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CryptTest {
    @Autowired
    CryptUtil cryptUtil;

    @Test
    void cryptEncTest(){
        String encStr = cryptUtil.generateEncryptedKey("암호할 대상");
        System.out.println(encStr);
        // 1243B4EE1CEB166505A52813487E1D325BE39F417D10D57A07A102DE7F01E0DB
    }

    @Test
    void cryptDecTest(){
        System.out.println(cryptUtil.generateKeyDecrypt("1243B4EE1CEB166505A52813487E1D325BE39F417D10D57A07A102DE7F01E0DB"));
        // 암호화 대상
    }

}
