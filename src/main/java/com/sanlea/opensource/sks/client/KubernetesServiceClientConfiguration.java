package com.sanlea.opensource.sks.client;

import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.codec.ErrorDecoder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * Kubernetes service client configuration
 *
 * @author kut
 */
public class KubernetesServiceClientConfiguration {

    @Bean
    @ConditionalOnMissingBean(ErrorDecoder.class)
    public ErrorDecoder buildErrorDecoder() {
        return new DefaultKubernetesServiceClientErrorDecoder();
    }

    @Bean
    @ConditionalOnMissingBean(Decoder.class)
    public Decoder buildDecoder() {
        return new DefaultKubernetesServiceClientResponseDecoder();
    }

    @Bean
    @ConditionalOnMissingBean(Encoder.class)
    public Encoder buildEncoder() {
        return new DefaultKubernetesServiceClientRequestEncoder();
    }
}
