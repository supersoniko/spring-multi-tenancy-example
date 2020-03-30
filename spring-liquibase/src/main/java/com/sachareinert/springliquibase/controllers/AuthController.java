package com.sachareinert.springliquibase.controllers;

import com.sachareinert.springliquibase.dtos.JwtRequest;
import com.sachareinert.springliquibase.dtos.JwtResponse;
import com.sachareinert.springliquibase.utils.JwtUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@Transactional
@RequestMapping(path = "/auth")
public class AuthController {
    private JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public JwtResponse auth(@RequestBody JwtRequest jwtRequest){
        String token = jwtUtil.generateToken(jwtRequest.getTenant());

        return new JwtResponse(token);
    }
}
