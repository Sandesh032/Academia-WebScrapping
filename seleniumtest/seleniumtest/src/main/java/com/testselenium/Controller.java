package com.testselenium;

import com.testselenium.Services.ScrappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    ScrappingService scrappingService;

    @GetMapping("/test")
    public String test() {
        return "Successfully connected";
    }

    @PostMapping("/login")
    public UserInfo login(@RequestBody Login details) throws InterruptedException {
        UserInfo data = new UserInfo();
        boolean loginChk = scrappingService.login(details);
        System.out.println((loginChk ? "Login Successfull!!":"Login Unsuccessfull"));
        if(loginChk) {
            data = scrappingService.scrapeData();
        }
        return data;
    }
}