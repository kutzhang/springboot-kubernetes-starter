package com.sanlea.opensource.sks.client;

import feign.ExceptionPropagationPolicy;
import feign.Feign;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.FactoryBean;

import static java.lang.String.format;

/**
 * Remote service factory bean
 *
 * @author kut
 */
public class KubernetesServiceClientFactoryBean implements FactoryBean<Object> {

    private final String name;
    private final String namespace;
    private final String cluster;
    private final int port;
    private final Class<?> clazz;
    private final Encoder encoder;
    private final Decoder decoder;
    private final ErrorDecoder errorDecoder;

    public KubernetesServiceClientFactoryBean(String name,
                                              String namespace,
                                              String cluster,
                                              int port,
                                              Class<?> clazz,
                                              Encoder encoder,
                                              Decoder decoder,
                                              ErrorDecoder errorDecoder) {
        this.name = name;
        this.namespace = namespace;
        this.cluster = cluster;
        this.port = port;
        this.clazz = clazz;
        this.encoder = encoder;
        this.decoder = decoder;
        this.errorDecoder = errorDecoder;
    }

    @Override
    public Object getObject() {
        String url = format(
                "http://%s.%s.svc.%s:%d",
                this.name,
                this.namespace,
                this.cluster,
                this.port
        );
        return Feign.builder()
                .encoder(encoder)
                .decoder(decoder)
                .errorDecoder(errorDecoder)
                .exceptionPropagationPolicy(ExceptionPropagationPolicy.UNWRAP)
                .target(this.clazz, url);
    }

    @Override
    public Class<?> getObjectType() {
        return clazz;
    }
}
