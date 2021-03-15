package com.sanlea.opensource.sks.provider.config;

import com.sanlea.opensource.sks.provider.KubernetesServiceWebFluxResponseBodyResultHandlerFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;

@ConditionalOnClass(name = "org.springframework.web.reactive.accept.RequestedContentTypeResolver")
public class KubernetesServiceWebfluxConfiguration {
    @Bean
    public KubernetesServiceWebFluxResponseBodyResultHandlerFactoryBean
    buildKubernetesServiceResponseBodyResultHandler() {
        return new KubernetesServiceWebFluxResponseBodyResultHandlerFactoryBean();
    }
}
