package com.sanlea.opensource.sks.client.protocol;

import com.alibaba.fastjson.JSON;
import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;

import java.lang.reflect.Type;

/**
 * Remote service request encoder
 *
 * @author kut
 */
public class DefaultKubernetesServiceClientRequestEncoder implements KubernetesServiceEncoder {
    @Override
    public void encode(Object o, Type type, RequestTemplate requestTemplate) throws EncodeException {
        requestTemplate.body(
                JSON.toJSONString(o)
        );
    }
}
