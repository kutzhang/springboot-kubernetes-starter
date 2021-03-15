package com.sanlea.opensource.sks.client.protocol;

import org.springframework.beans.factory.Aware;

/**
 * Kubernetes service protocol aware
 *
 * @author kut
 */
public interface KubernetesServiceProtocolAware extends Aware {
    /**
     * set kubernetes service protocol object
     *
     * @param kubernetesServiceProtocol kubernetes service protocol object
     */
    void setKubernetesServiceProtocol(KubernetesServiceProtocol kubernetesServiceProtocol);
}
