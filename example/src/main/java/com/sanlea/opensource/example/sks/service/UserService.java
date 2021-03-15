package com.sanlea.opensource.example.sks.service;

import com.sanlea.opensource.sks.client.KubernetesServiceClient;
import feign.RequestLine;

@KubernetesServiceClient(name = "user", namespace = "sanlea")
public interface UserService {

    @RequestLine("GET /current")
    String current();
}
