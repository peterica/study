package com.peterica.swagger.repository;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TestRepository {

    private final SqlSession sqlSession;

    public List<String> getTestList(){
        return sqlSession.selectList("test.getTest");

    }
}
