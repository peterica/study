package com.peterica.swagger.util;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileReadWriteTest {


    @Test
    public void fileReadTest() throws FileNotFoundException {

        try(
            //파일 객체 생성
            FileReader file = new FileReader("test.csv");
            BufferedReader inFile = new BufferedReader(file);
        ){

            String sLine = null;
            List<String> list = new ArrayList<>();
            while( (sLine = inFile.readLine()) != null ){
                list.add(sLine);
            }

            System.out.println(list.toString());

        }catch ( IOException e ){
            e.printStackTrace();
        }

    }


    @Test
    public void fileWriteTest(){

        try(
            FileWriter fw = new FileWriter( "fileWrite.txt" ,true);
            BufferedWriter bw = new BufferedWriter( fw);
        ){
            bw.write("Java7 이전에, try-catch-finally 구문에서 자원을 해제하였다.");
            bw.newLine();
            bw.write("Java8에서는 AutoCloseable인테페이스로 구현된 자원은 자동으로 close를 해준다. ");

        }catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void autoCloseableTest() {

        try (makeAutoClose cr = new makeAutoClose()) {
            // 데모실행
            cr.checkTheAutoClose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class makeAutoClose implements AutoCloseable {
        public void checkTheAutoClose() {
            System.out.println("AutoCloseable 인터패이스로 구현되어 있다.");
        }

        @Override
        public void close() throws Exception {
            System.out.println("try()안에 객체생성되어 있다면 try 문을 벗어나면 close 메소트를 호출한다.");
       }
    }

}
