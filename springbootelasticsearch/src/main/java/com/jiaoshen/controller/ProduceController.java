package com.jiaoshen.controller;


import lombok.extern.slf4j.Slf4j;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class ProduceController {
	@Autowired
	private TransportClient client;
   
    @GetMapping("/test")
    public String test() {
    	return "HelloWorld";
    }

    @GetMapping("/hell/{personId}")
    @ResponseBody
    public ResponseEntity sendKafka(@PathVariable("personId")String id) {
    	 GetResponse result = client.prepareGet("people","man",id).get();
    	 if(!result.isExists()){
    		 return new ResponseEntity(HttpStatus.NOT_FOUND);
    	 }
    	return new ResponseEntity(result.getSource(),HttpStatus.OK);
    }


}