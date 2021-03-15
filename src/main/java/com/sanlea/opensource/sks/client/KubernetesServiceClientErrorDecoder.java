package com.sanlea.opensource.sks.client;

import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.BeanClassLoaderAware;

/**
 * Kubernetes service client error decoder interface
 *
 * @author kut
 */
public abstract class KubernetesServiceClientErrorDecoder implements BeanClassLoaderAware, ErrorDecoder {

    // bean class loader
    protected ClassLoader beanClassLoader;

    @Override
    public void setBeanClassLoader(ClassLoader beanClassLoader) {
        this.beanClassLoader = beanClassLoader;
    }
}
