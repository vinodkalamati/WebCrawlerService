package com.knowably.controller;

import com.knowably.model.Input;
import com.knowably.model.WebCrawl;
import com.knowably.service.WebCrawler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "api/v1")
public class WebCrawlerController {

    private WebCrawler webCrawler;
    private Input input;
    private ResponseEntity responseEntity;

    @Autowired
    public WebCrawlerController(WebCrawler webCrawler) {
        this.webCrawler = webCrawler;
    }


    @GetMapping("content")
    public ResponseEntity<?> getContent(){
        try{
            List<WebCrawl> lists=webCrawler.getContent(input);
            responseEntity = new ResponseEntity<List<WebCrawl>>(lists, HttpStatus.OK);
        }
        catch (Exception e){
            responseEntity = new ResponseEntity<String>("No Content Found",HttpStatus.NOT_FOUND);

        }
        return responseEntity;
    }

    @PostMapping("content")
    public ResponseEntity<String> domainUpload(@RequestBody Input input1)
    {
        try {
            input = new Input();
            input.setDomain(input1.getDomain());
            String concept = input1.getConcept();
            input.setConcept(concept);
            input.setUserId(input1.getUserId());
            input.setUrl(input1.getUrl());
            input.setId(input1.getId());
            responseEntity = new ResponseEntity<String>("Uploaded Succesfully", HttpStatus.OK);
        } catch (Exception e)
        {
            responseEntity = new ResponseEntity<String>("Error in Uploading",HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

}
