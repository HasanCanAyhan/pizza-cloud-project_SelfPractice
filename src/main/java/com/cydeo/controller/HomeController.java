package com.cydeo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @GetMapping({"/", "/home"}) // localhost:8080 , localhost:8080/, localhost:8080 /home
    public String homePage(){

        return "home.html";
    }



}
