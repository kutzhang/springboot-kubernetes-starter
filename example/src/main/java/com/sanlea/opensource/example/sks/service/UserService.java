package com.sanlea.opensource.example.sks.service;

import com.sanlea.opensource.sks.client.KubernetesService;
import feign.RequestLine;

@KubernetesService(name = "user", namespace = "sanlea")
public interface UserService {

    @RequestLine("GET /current")
    String current();
}
