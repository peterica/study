package com.peterica.swagger.util;


import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvGenerator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

@Component
public class CSVUtil {

    // makeCsvFile
    public void makeCsvFile(Class<?> clazz, File csvFile, List<?> dataList) {
        try {
            CsvMapper csvMapper = new CsvMapper();
            CsvSchema csvSchema =
                    csvMapper.enable(CsvGenerator.Feature.ALWAYS_QUOTE_STRINGS)
                            .schemaFor(clazz)   // CSV 파일로 생성할 자바 객체의 클래스 정보
                            .withHeader()       // CSV 헤더 사용 여부
                            .withColumnSeparator(',')   // 컬럼 간 구분자
                            .withLineSeparator("\n");   // 개행

            ObjectWriter writer = csvMapper.writer(csvSchema);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(csvFile), "UTF-8");
            writer.writeValues(outputStreamWriter).writeAll(dataList);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}