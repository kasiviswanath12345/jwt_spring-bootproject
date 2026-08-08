package com.example.fusion.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    @GetMapping("/dashboard")
    public String manager() {
        return "Welcome MANAGER!";
    }
}