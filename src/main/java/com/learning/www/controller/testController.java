package com.learning.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.www.service.UserMapperService;

@RestController
public class testController {
	
	
    @Autowired
    private UserMapperService userService;
    
    @RequestMapping("test")
    public String test() {    	
    	
    	return userService.getPasswordByUsername("joke").toString();
    }
}
