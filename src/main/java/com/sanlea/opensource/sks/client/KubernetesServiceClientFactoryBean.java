package com.sanlea.opensource.sks.client;

import com.sanlea.opensource.sks.client.protocol.KubernetesServiceProtocol;
import com.sanlea.opensource.sks.client.protocol.KubernetesServiceProtocolAware;
import com.sanlea.opensource.sks.constant.KubernetesServiceClientMode;
import feign.ExceptionPropagationPolicy;
import feign.Feign;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationTargetException;

import static java.lang.String.format;

/**
 * Remote service factory bean
 *
 * @author kut
 */
public class KubernetesServiceClientFactoryBean implements FactoryBean<Object>, KubernetesServiceProtocolAware {

    private final String name;
    private final String namespace;
    private final String cluster;
    private final int port;
    private final Class<?> clazz;
    private final KubernetesServiceClientMode mode;
    private final Class<?> mockClass;
    private KubernetesServiceProtocol kubernetesServiceProtocol;

    public KubernetesServiceClientFactoryBean(String name,
                                              String namespace,
                                              String cluster,
                                              int port,
                                              Class<?> clazz,
                                              KubernetesServiceClientMode mode,
                                              Class<?> mockClass) {
        this.name = name;
        this.namespace = namespace;
        this.cluster = cluster;
        this.port = port;
        this.clazz = clazz;
        this.mode = mode;
        this.mockClass = mockClass;
    }

    @Override
    public Object getObject() {
        if (mode == KubernetesServiceClientMode.MOCK && this.mockClass != void.class) {
            try {
                return this.mockClass.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else  {
            String url = format(
                    "http://%s.%s.svc.%s:%d",
                    this.name,
                    this.namespace,
                    this.cluster,
                    this.port
            );
            return Feign.builder()
                    .encoder(this.kubernetesServiceProtocol)
                    .decoder(this.kubernetesServiceProtocol)
                    .errorDecoder(this.kubernetesServiceProtocol)
                    .exceptionPropagationPolicy(ExceptionPropagationPolicy.UNWRAP)
                    .target(this.clazz, url);
        }
    }

    @Override
    public Class<?> getObjectType() {
        return clazz;
    }

    @Override
    public void setKubernetesServiceProtocol(KubernetesServiceProtocol kubernetesServiceProtocol) {
        this.kubernetesServiceProtocol = kubernetesServiceProtocol;
    }
}
