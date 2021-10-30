package com.sanlea.opensource.example.sks;

import com.sanlea.opensource.sks.client.EnableKubernetesServiceClientSupport;
import com.sanlea.opensource.sks.provider.EnableKubernetesServiceProviderSupport;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableKubernetesServiceClientSupport(
        servicePackages = "com.sanlea.opensource.example.sks.service",
        mockPackages = "com.sanlea.opensource.example.sks.service"
)
@EnableKubernetesServiceProviderSupport
public class SpringBootKubernetesStarterApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootKubernetesStarterApplication.class, args);
    }
}
