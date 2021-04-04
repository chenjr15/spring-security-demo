package dev.chenjr.demo.springsecurity.controller;

import dev.chenjr.demo.springsecurity.service.JwtAuthService;
import dev.chenjr.demo.springsecurity.service.dto.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    JwtAuthService authService;

    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest.getUsername(), loginRequest.getPassword());
    }
}
