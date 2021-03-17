package com.sanlea.opensource.sks.client;

import com.sanlea.opensource.sks.client.protocol.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;

/**
 * Kubernetes service client configuration
 *
 * @author kut
 */
@ConfigurationPropertiesScan(basePackages = "com.sanlea.opensource.sks.client")
public class KubernetesServiceClientConfiguration {

    @Bean
    @ConditionalOnMissingBean(KubernetesServiceErrorDecoder.class)
    public KubernetesServiceErrorDecoder buildErrorDecoder() {
        return new DefaultKubernetesServiceClientErrorDecoder();
    }

    @Bean
    @ConditionalOnMissingBean(KubernetesServiceDecoder.class)
    public KubernetesServiceDecoder buildDecoder() {
        return new DefaultKubernetesServiceClientResponseDecoder();
    }

    @Bean
    @ConditionalOnMissingBean(KubernetesServiceEncoder.class)
    public KubernetesServiceEncoder buildEncoder() {
        return new DefaultKubernetesServiceClientRequestEncoder();
    }

    @Bean
    public KubernetesServiceProtocol buildKubernetesServiceProtocol(KubernetesServiceEncoder encoder,
                                                                    KubernetesServiceDecoder decoder,
                                                                    KubernetesServiceErrorDecoder errorDecoder) {
        return new KubernetesServiceProtocol(encoder, decoder, errorDecoder);
    }
}
