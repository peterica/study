package com.peterica.swagger.util;

import com.peterica.swagger.vo.ExportVO;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class CSVFileMakeTest {

    @Test
    public void makeCSVfile(){
        // 파일의 내용
        ExportVO exportVO = new ExportVO("conversationId","talkText");

        // 파일의 row생성
        List<ExportVO> list = new ArrayList<>();
        list.add(exportVO);

        // 저장할 CSV파일 객체
        File csvFile = new File("test.csv");

        CSVUtil csvUtil = new CSVUtil();
        csvUtil.makeCsvFile(ExportVO.class, csvFile, list);

    }
}
