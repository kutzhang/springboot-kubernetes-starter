package com.sanlea.opensource.example.sks;

import com.sanlea.opensource.sks.client.EnableKubernetesServiceClientSupport;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableKubernetesServiceClientSupport(basePackages = "com.sanlea.opensource.example.sks.service")
public class SpringbootKubernetesStarterApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootKubernetesStarterApplication.class, args);
    }
}
