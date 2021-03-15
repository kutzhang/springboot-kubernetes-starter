package com.sanlea.opensource.sks.provider.config;

import com.sanlea.opensource.sks.provider.KubernetesServiceResponseWrapper;
import com.sanlea.opensource.sks.provider.KubernetesServiceServletResponseBodyResultHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;

@ConditionalOnClass(name = "org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice")
public class KubernetesServiceServletConfiguration {
    @Bean
    public KubernetesServiceServletResponseBodyResultHandler buildKubernetesServiceServletResponseBodyResultHandler(
            KubernetesServiceResponseWrapper kubernetesServiceResponseWrapper
    ) {
        return new KubernetesServiceServletResponseBodyResultHandler(kubernetesServiceResponseWrapper);
    }
}
