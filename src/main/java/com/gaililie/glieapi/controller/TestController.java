package com.gaililie.glieapi.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/glie/test")
public class TestController {

    @PostMapping("test")
    public String test() {
        return "hello world";
    }


}
