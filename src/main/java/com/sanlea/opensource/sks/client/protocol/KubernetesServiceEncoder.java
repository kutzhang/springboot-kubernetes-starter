package com.sanlea.opensource.sks.client.protocol;

import feign.RequestTemplate;
import feign.codec.EncodeException;

import java.lang.reflect.Type;

/**
 * Kubernetes service encoder
 *
 * @author kut
 */
public interface KubernetesServiceEncoder {
    /**
     * encode
     *
     * @param object   object
     * @param bodyType body type
     * @param template template
     * @throws EncodeException encode exception
     */
    void encode(Object object, Type bodyType, RequestTemplate template)
            throws EncodeException;
}
