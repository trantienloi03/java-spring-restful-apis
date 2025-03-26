package com.trantienloi.jobhunter.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.trantienloi.jobhunter.demo.util.errors.IdInvalidException;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HelloController {
    @GetMapping("/")
    public String getHelloWorld() throws IdInvalidException {
        if (true)
            throw new IdInvalidException("check");
        return "Hello - Tran Tien Loi";
    }

}
