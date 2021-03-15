package com.sanlea.opensource.sks.provider.config;

import com.sanlea.opensource.sks.provider.DefaultKubernetesServiceResponseWrapper;
import com.sanlea.opensource.sks.provider.KubernetesServiceExceptionHandler;
import com.sanlea.opensource.sks.provider.KubernetesServiceResponseWrapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * Micro service configuration
 *
 * @author kut
 */
public class KubernetesServiceCommonConfiguration {

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
