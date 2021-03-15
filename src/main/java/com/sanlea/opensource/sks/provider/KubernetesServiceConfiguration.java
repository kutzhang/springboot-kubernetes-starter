package com.sanlea.opensource.sks.provider;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.accept.RequestedContentTypeResolver;

/**
 * Micro service configuration
 *
 * @author kut
 */
public class KubernetesServiceConfiguration {
    @Bean
    public KubernetesServiceResponseBodyResultHandler buildKubernetesServiceResponseBodyResultHandler(
            DefaultKubernetesServiceResponseWrapper kubernetesServiceResponseWrapper,
            ServerCodecConfigurer serverCodecConfigurer,
            RequestedContentTypeResolver resolver) {
        return new KubernetesServiceResponseBodyResultHandler(serverCodecConfigurer.getWriters(), resolver, kubernetesServiceResponseWrapper);
    }

    @Bean
    public KubernetesServiceExceptionHandler buildKubernetesServiceExceptionHandler() {
        return new KubernetesServiceExceptionHandler();
    }

    @Bean
    @ConditionalOnMissingBean(KubernetesServiceResponseWrapper.class)
    public DefaultKubernetesServiceResponseWrapper buildDefaultKubernetesServiceResponseWrapper() {
        return new DefaultKubernetesServiceResponseWrapper();
    }
}
