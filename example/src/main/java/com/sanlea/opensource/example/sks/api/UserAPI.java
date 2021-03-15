package com.sanlea.opensource.example.sks.api;

import com.sanlea.opensource.example.sks.service.UserService;
import com.sanlea.opensource.sks.provider.DisableKubernetesServiceResponse;
import org.springframework.http.MediaType;
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

    @DisableKubernetesServiceResponse
    @GetMapping(value = "/detail", produces = {
            MediaType.TEXT_PLAIN_VALUE,
            MediaType.APPLICATION_JSON_VALUE
    })
    public String fetchDetail() {
        return "Hello";
    }

    @DisableKubernetesServiceResponse
    @GetMapping(value = "/detail2", produces = MediaType.TEXT_HTML_VALUE)
    public String fetchDetail2() {
        return "<h1>Hello1</h1>";
    }
}
