package com.sanlea.opensource.sks.client.protocol;

import feign.Response;

/**
 * Kubernetes service error decoder
 *
 * @author kut
 */
public interface KubernetesServiceErrorDecoder {
    /**
     * decode
     *
     * @param methodKey method key
     * @param response  response
     * @return error
     */
    Exception decode(String methodKey, Response response);
}
