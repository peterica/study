package com.peterica.swagger.controller;

import com.peterica.swagger.service.AWSService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequiredArgsConstructor
public class S3Controller {

    private final AWSService awsService;


    @PostMapping("/s3/download")
    public String download(@RequestParam(required = true) String fileName, @RequestParam(required = false) String downloadFileName, HttpServletRequest request, HttpServletResponse response){
        String result = "suc";

        awsService.download(fileName, downloadFileName, request,response);
        return result;
    }

    @PostMapping(value = "/s3/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String upload(@RequestParam("multipartFile") MultipartFile multipartFile){
        String result = "suc";
        awsService.uploadToAWS(multipartFile);
        return result;
    }


    @PostMapping(value = "/s3/delete")
    public String delete(@RequestParam(required = true) String fileName){
        String result = "suc";
        awsService.delete(fileName);
        return result;
    }


    @PostMapping(value = "/s3/rename")
    public String rename(@RequestParam(required = true) String oriFileName, @RequestParam(required = true) String chgFileName){
        String result = "suc";
        awsService.rename(oriFileName,chgFileName);
        return result;
    }
}
