package com.sanlea.opensource.sks.client;

import com.sanlea.opensource.sks.client.protocol.KubernetesServiceProtocol;
import com.sanlea.opensource.sks.client.protocol.KubernetesServiceProtocolAware;
import feign.ExceptionPropagationPolicy;
import feign.Feign;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import static java.lang.String.format;

/**
 * Remote service factory bean
 *
 * @author kut
 */
public class KubernetesServiceClientFactoryBean
        implements FactoryBean<Object>, KubernetesServiceProtocolAware, ApplicationContextAware {

    private final String name;
    private final String namespace;
    private final String cluster;
    private final int port;
    private final Class<?> serviceClass;
    private boolean mockEnable;
    private final KubernetesMockServiceInfo mockInfo;
    private KubernetesServiceProtocol kubernetesServiceProtocol;

    public KubernetesServiceClientFactoryBean(String name,
                                              String namespace,
                                              String cluster,
                                              int port,
                                              Class<?> serviceClass,
                                              KubernetesMockServiceInfo mockServiceInfo) {
        this.name = name;
        this.namespace = namespace;
        this.cluster = cluster;
        this.port = port;
        this.serviceClass = serviceClass;
        this.mockInfo = mockServiceInfo;
    }

    @Override
    public Object getObject() {
        if (mockEnable) {
            if (mockInfo.getType().equals(KubernetesMockType.MOCK_CLASS)) {
                return Feign.builder()
                        .encoder(this.kubernetesServiceProtocol)
                        .decoder(this.kubernetesServiceProtocol)
                        .errorDecoder(this.kubernetesServiceProtocol)
                        .exceptionPropagationPolicy(ExceptionPropagationPolicy.UNWRAP)
                        .target(this.serviceClass, mockInfo.getMockUrl());
            } else {
                try {
                    return this.mockInfo.getMockClass().getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
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
                    .target(this.serviceClass, url);
        }
    }

    @Override
    public Class<?> getObjectType() {
        return serviceClass;
    }

    @Override
    public void setKubernetesServiceProtocol(KubernetesServiceProtocol kubernetesServiceProtocol) {
        this.kubernetesServiceProtocol = kubernetesServiceProtocol;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        var properties = applicationContext.getBean(KubernetesClientProperties.class);
        this.mockEnable = properties.isMockEnable();
    }
}
