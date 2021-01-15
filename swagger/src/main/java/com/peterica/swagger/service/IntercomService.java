package com.peterica.swagger.service;

import com.peterica.swagger.util.CSVUtil;
import com.peterica.swagger.vo.ExportVO;
import io.intercom.api.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class IntercomService {

    private final CSVUtil csvUtil;

    @Value("${intercom.manager.token}")
    private String managerToken;

    @PostConstruct
    public void init(){
        Intercom.setToken(managerToken);
    }

    public void getAllListConversation(){

        // 모든 대화내용을 가져온다.
        ConversationCollection conversations = Conversation.list();
        while (conversations.hasNext()) {

            // 대화방 가져오기
            Conversation conversation = conversations.next();
            log.info("conversation");

            // 대화들 가져오기
            ConversationPartCollection parts = conversation.getConversationPartCollection();
            List<ConversationPart> partList = parts.getPage();

            // 대화목록
            for (ConversationPart part : partList) {
                System.out.println(part.getId());
            }
        }

    }

    // 모든 conversationId 가져오기
    public void getAllConversationIds(){

        try{

            //파일 객체 생성
            File file = new File("conversationIds.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

            if(file.isFile() && file.canWrite()){

                // 모든 대화내용을 가져온다.
                ConversationCollection conversations = Conversation.list();
                while (conversations.hasNext()) {

                    // 대화방 가져오기
                    Conversation conversation = conversations.next();
                    System.out.println(conversation.getId());

                    //쓰기
                    bufferedWriter.write(conversation.getId());
                    bufferedWriter.newLine();//개행문자쓰기

                }

                bufferedWriter.close();
            }

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 파일에서 conversationID 목록 가져오기
    public void readFile(){
        try{
            //파일 객체 생성
            File file = new File("2020_cnvList.txt");

            //입력 스트림 생성
            BufferedReader inFile = new BufferedReader(new FileReader(file));

            String sLine = null;
            List<String> list = new ArrayList<>();
            while( (sLine = inFile.readLine()) != null ){
                list.add(sLine);
            }
            inFile.close();

            // 파일 라인이 많아 분산
            makeCSV(list.subList(0,4999),"2020_1-5000.csv");
            makeCSV(list.subList(5000,9999),"2019_5001-10000.csv");
            makeCSV(list.subList(10000,14999),"2019_10001-15000.csv");
            makeCSV(list.subList(15000,19999),"2019_15001-20000.csv");
            makeCSV(list.subList(20000,list.size()),"2019_20001.csv");

        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch(IOException e){
            System.out.println(e);
        }

    }

    // CSV 파일 만들기
    public void makeCSV(List<String> target, String fileName){
        List list = new ArrayList();
        Long start = System.currentTimeMillis();

        target.parallelStream().forEach(id->{
            System.out.println(id+ "_" + list.size());
            try {
                final Conversation conversation = Conversation.find(id);
                if(conversation != null){
                    ConversationPartCollection parts = conversation.getConversationPartCollection();
                    List<ConversationPart> partList = parts.getPage();

                    for (ConversationPart part : partList) {
                        ExportVO exportVO = new ExportVO(conversation.getId(), part.getBody());
                        list.add(exportVO);
                    }
                }

            }catch (Exception e){
                e.printStackTrace();
            }

        });

        // 걸린 시간체크
        Long end = System.currentTimeMillis();
        long takeTime =end-start;
        System.out.println("the time:"+takeTime);

        System.out.println("csv Row List size:" + list.size());
        File csvFile = new File(fileName);
        csvUtil.makeCsvFile(ExportVO.class,csvFile,list);
    }
}
