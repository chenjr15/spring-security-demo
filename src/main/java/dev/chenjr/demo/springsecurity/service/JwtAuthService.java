package dev.chenjr.demo.springsecurity.service;

import dev.chenjr.demo.springsecurity.jwt.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtAuthService {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtTokenUtils jwtTokenUtils;


    public String login(String username, String password) {
        Authentication authentication = null;
        UserDetails userDetails = userService.loadUserByUsername(username);

//        String encoded = passwordEncoder.encode(password);
//        System.out.println(encoded,);
        if (userDetails.getPassword().equals(userDetails.getPassword())) {
            return jwtTokenUtils.generateToken(userDetails);
        }
        throw new BadCredentialsException("用户名或密码错误");

    }
}
