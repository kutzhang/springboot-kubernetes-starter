package com.sanlea.opensource.example.sks.api;

import com.sanlea.opensource.example.sks.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserAPI {

    private final UserService userService;

    public UserAPI(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/current")
    public String current() {
        return userService.current();
    }

    @GetMapping("/detail")
    public String fetchDetail() {
        return "Hello";
    }
}
