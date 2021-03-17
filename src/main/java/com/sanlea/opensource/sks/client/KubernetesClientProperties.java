package com.sanlea.opensource.sks.client;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Kubernetes client properties
 *
 * @author kut
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@ConfigurationProperties("spring.kubernetes.service.client")
public class KubernetesClientProperties {

    // in mock mode
    private boolean inMockMode;
}
