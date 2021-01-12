package com.peterica.swagger.util.slack;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class SlackSenderUtil {
    @Value("${notification.slack.enabled}")
    private boolean slackEnabled;
    @Value("${notification.slack.webhook.url}")
    private String webhookUrl;
    @Value("${notification.slack.channel}")
    private String channel;
    @Value("${notification.slack.botName}")
    private String botName;
    @Value("${notification.slack.icon.emoji}")
    private String iconEmoji;
    @Value("${notification.slack.icon.url}")
    private String iconUrl;

    public void sendSlack(String contents) {
        if (slackEnabled) {
            try {
                // create slack Message
                SlackMessage slackMessage = new SlackMessage(contents, channel, botName, iconEmoji, iconUrl);
                String payload = new Gson().toJson(slackMessage);

                log.info(channel);

                RestTemplate restTemplate = new RestTemplate();
                HttpHeaders headers = new HttpHeaders();
                headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);

                // send the post request
                HttpEntity<String> entity = new HttpEntity<>(payload, headers);
                restTemplate.postForEntity(webhookUrl, entity, String.class);
            } catch (Exception e) {
                log.error("Unhandled Exception occurred while send slack. [Reason] : ", e);
            }
        }
    }
}
