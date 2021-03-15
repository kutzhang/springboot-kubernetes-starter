package com.sanlea.opensource.sks.provider.config;

import com.sanlea.opensource.sks.provider.DefaultKubernetesServiceResponseWrapper;
import com.sanlea.opensource.sks.provider.KubernetesServiceExceptionHandler;
import com.sanlea.opensource.sks.provider.KubernetesServiceResponseWrapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Micro service configuration
 *
 * @author kut
 */
public class KubernetesServiceCommonConfiguration implements WebMvcConfigurer {

    @Bean
    public KubernetesServiceExceptionHandler buildKubernetesServiceExceptionHandler() {
        return new KubernetesServiceExceptionHandler();
    }

    @Bean
    @ConditionalOnMissingBean(KubernetesServiceResponseWrapper.class)
    public DefaultKubernetesServiceResponseWrapper buildDefaultKubernetesServiceResponseWrapper() {
        return new DefaultKubernetesServiceResponseWrapper();
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        WebMvcConfigurer.super.configureMessageConverters(converters);

        // remove StringHttpMessageConverters, then add them to the end of converter list
        converters.removeIf(converter -> converter instanceof StringHttpMessageConverter);
        converters.add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
        converters.add(new StringHttpMessageConverter(StandardCharsets.ISO_8859_1));
    }
}
