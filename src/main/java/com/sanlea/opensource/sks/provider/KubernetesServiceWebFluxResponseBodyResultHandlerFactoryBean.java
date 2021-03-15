package com.sanlea.opensource.sks.provider;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.accept.RequestedContentTypeResolver;

public class KubernetesServiceWebFluxResponseBodyResultHandlerFactoryBean
        implements FactoryBean<KubernetesServiceWebFluxResponseBodyResultHandler> {

    @Autowired(required = false)
    private ServerCodecConfigurer serverCodecConfigurer;

    @Autowired(required = false)
    private RequestedContentTypeResolver resolver;

    @Autowired
    private KubernetesServiceResponseWrapper kubernetesServiceResponseWrapper;

    @Override
    public KubernetesServiceWebFluxResponseBodyResultHandler getObject() {
        return new KubernetesServiceWebFluxResponseBodyResultHandler(
                serverCodecConfigurer.getWriters(),
                resolver,
                kubernetesServiceResponseWrapper
        );
    }

    @Override
    public Class<?> getObjectType() {
        return KubernetesServiceWebFluxResponseBodyResultHandler.class;
    }
}
