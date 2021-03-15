package com.sanlea.opensource.sks.provider.config;

import com.sanlea.opensource.sks.provider.KubernetesServiceResponseWrapper;
import com.sanlea.opensource.sks.provider.KubernetesServiceServletResponseBodyResultHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.List;

@ConditionalOnClass(name = "org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice")
public class KubernetesServiceServletConfiguration implements WebMvcConfigurer {
    @Bean
    public KubernetesServiceServletResponseBodyResultHandler buildKubernetesServiceServletResponseBodyResultHandler(
            KubernetesServiceResponseWrapper kubernetesServiceResponseWrapper
    ) {
        return new KubernetesServiceServletResponseBodyResultHandler(kubernetesServiceResponseWrapper);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        WebMvcConfigurer.super.configureMessageConverters(converters);
        converters.removeIf(converter -> converter instanceof StringHttpMessageConverter);
        converters.add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
    }
}
