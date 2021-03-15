package com.sanlea.opensource.sks.client.protocol;

import feign.FeignException;
import feign.Response;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Kubernetes service decoder
 *
 * @author kut
 */
public interface KubernetesServiceDecoder {
    /**
     * decode
     *
     * @param response response
     * @param type     type
     * @return response object
     */
    Object decode(Response response, Type type)
            throws IOException, FeignException;
}
