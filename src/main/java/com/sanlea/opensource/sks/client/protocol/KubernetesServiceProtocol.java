package com.sanlea.opensource.sks.client.protocol;

import feign.FeignException;
import feign.RequestTemplate;
import feign.Response;
import feign.codec.Decoder;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Kubernetes service protocal
 *
 * @author kut
 */
public class KubernetesServiceProtocol implements Encoder, Decoder, ErrorDecoder {

    private final KubernetesServiceEncoder encoder;
    private final KubernetesServiceDecoder decoder;
    private final KubernetesServiceErrorDecoder errorDecoder;

    public KubernetesServiceProtocol(KubernetesServiceEncoder encoder,
                                     KubernetesServiceDecoder decoder,
                                     KubernetesServiceErrorDecoder errorDecoder) {
        this.encoder = encoder;
        this.decoder = decoder;
        this.errorDecoder = errorDecoder;
    }

    @Override
    public Object decode(Response response, Type type)
            throws IOException, FeignException {
        return this.decoder.decode(response, type);
    }

    @Override
    public void encode(Object object, Type bodyType, RequestTemplate template)
            throws EncodeException {
        this.encoder.encode(object, bodyType, template);
    }

    @Override
    public Exception decode(String methodKey, Response response) {
        return this.errorDecoder.decode(methodKey, response);
    }
}
