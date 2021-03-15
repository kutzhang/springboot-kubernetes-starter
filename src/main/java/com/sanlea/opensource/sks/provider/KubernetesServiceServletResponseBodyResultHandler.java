package com.sanlea.opensource.sks.provider;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * API response body advice - servlet
 *
 * @author kut
 */
@RestControllerAdvice
public class KubernetesServiceServletResponseBodyResultHandler implements ResponseBodyAdvice<Object> {

    private final KubernetesServiceResponseWrapper kubernetesServiceResponseWrapper;

    public KubernetesServiceServletResponseBodyResultHandler(
            KubernetesServiceResponseWrapper kubernetesServiceResponseWrapper) {
        this.kubernetesServiceResponseWrapper = kubernetesServiceResponseWrapper;
    }

    @Override
    public boolean supports(MethodParameter returnType,
                            Class<? extends HttpMessageConverter<?>> converterType) {
        return !returnType.hasMethodAnnotation(DisableKubernetesServiceResponse.class);
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {
        return this.kubernetesServiceResponseWrapper.wrap(body);
    }
}
