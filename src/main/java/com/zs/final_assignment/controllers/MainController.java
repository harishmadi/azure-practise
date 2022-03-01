package com.zs.final_assignment.controllers;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/*")
public class MainController {
    private final static Logger logger = Logger.getLogger(MainController.class);

    public MainController(){

    }

    protected Logger getLogger(){
        return logger;
    }
}

//
//@Controller
//@ResponseStatus(HttpStatus.NOT_FOUND)
//class ResourceNotFoundException extends RuntimeException {
//
//    @RequestMapping("/404.html")
//    public String request404(){
//        return "404";
//    }
//
//    public ResourceNotFoundException(){
//
//    }
//}