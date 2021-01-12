package com.peterica.swagger.util;

import com.peterica.swagger.util.slack.SlackSenderUtil;
import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import com.ullink.slack.simpleslackapi.SlackChannel;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.impl.SlackSessionFactory;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class SlackTest {

    @Test
    public void sendSlackNotiByRest(){
        StringBuilder sb = new StringBuilder();
        sb.append("[Notification]")
                .append(System.getProperty("line.separator"))
                .append("[Name] : ").append("Tester")
                .append(System.getProperty("line.separator"))
                .append("[Message] : ")
                .append("테스트 메시지 !!");
        String contents = sb.toString();
        SlackSenderUtil slackSenderUtil = new SlackSenderUtil();
        slackSenderUtil.sendSlack(contents);
    }

    @Test
    public void sendSlackNotiBySDK(){
        SlackSession session = SlackSessionFactory.createWebSocketSlackSession("xoxx-3xxxxxxxxx-49xxxxxxxx-mA10xxxxxxxxxxxx");
        try {
            session.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SlackChannel channel = session.findChannelByName("#general");
        session.sendMessage(channel, "message" );
    }


    @Test
    public void test(){
        // https://slack.dev/java-slack-sdk/guides/web-api-basics
        try {
            Slack slack = Slack.getInstance();
            String token = "xoxx-3xxxxxxxxx-49xxxxxxxx-mA10xxxxxxxxxxxx";

            // Initialize an API Methods client with the given token
            MethodsClient methods = slack.methods(token);

            // Build a request object
            ChatPostMessageRequest request = ChatPostMessageRequest.builder()
                    .channel("#random") // Use a channel ID `C1234567` is preferrable
                    .text(":wave: Hi from a bot written in Java!")
                    .build();

            // Get a response as a Java object
            ChatPostMessageResponse response = methods.chatPostMessage(request);
        }catch (SlackApiException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }


    }
}
