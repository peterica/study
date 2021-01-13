package com.peterica.swagger.controller;

import com.peterica.swagger.service.IntercomService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IntercomController {
    @Autowired
    private IntercomService intercomService;

    /**
     * CSV test
     * @return
     */
    @GetMapping(value = "/allList")
    @ApiOperation(httpMethod = "GET", value = "intercom", notes = "intercom Test", tags = "intercom")
    public String getAllConversationList(){
//        intercomService.getAllListConversation();
        intercomService.getAllConversationIds();
        return "suc";
    }
}
