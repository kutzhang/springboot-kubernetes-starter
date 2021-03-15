package com.sanlea.opensource.sks.provider;

/**
 * Kubernetes service response wrapper
 *
 * @author kut
 */
public interface KubernetesServiceResponseWrapper {
    /**
     * wrap
     *
     * @param data data
     * @return result
     */
    Object wrap(Object data);
}
