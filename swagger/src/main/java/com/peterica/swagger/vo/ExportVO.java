package com.peterica.swagger.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExportVO {
    private String conversationId;
    private String talk;

    public ExportVO(String conversationId, String talk) {
        this.conversationId = conversationId;
        this.talk = talk;
    }
}
