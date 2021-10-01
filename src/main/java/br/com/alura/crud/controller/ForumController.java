package br.com.alura.crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ForumController {

    @RequestMapping("/")
    @ResponseBody
    public String teste(){
        return "Hello World";
    }
}
