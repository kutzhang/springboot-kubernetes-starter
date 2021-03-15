package com.sanlea.opensource.sks.provider;

import com.alibaba.fastjson.JSONObject;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.codec.HttpMessageWriter;
import org.springframework.web.reactive.HandlerResult;
import org.springframework.web.reactive.accept.RequestedContentTypeResolver;
import org.springframework.web.reactive.result.method.annotation.ResponseBodyResultHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;


/**
 * API response body advice - webflux
 *
 * @author kut
 */
public class KubernetesServiceWebFluxResponseBodyResultHandler extends ResponseBodyResultHandler {

    private static MethodParameter param;

    static {
        try {
            param = new MethodParameter(KubernetesServiceWebFluxResponseBodyResultHandler.class
                    .getDeclaredMethod("methodForParams"), -1);
        } catch (NoSuchMethodException e) {
            // nothing to do
        }
    }

    private static Mono<JSONObject> methodForParams() {
        return null;
    }

    private final KubernetesServiceResponseWrapper kubernetesServiceResponseWrapper;

    public KubernetesServiceWebFluxResponseBodyResultHandler(
            List<HttpMessageWriter<?>> writers,
            RequestedContentTypeResolver resolver,
            KubernetesServiceResponseWrapper kubernetesServiceResponseWrapper) {
        super(writers, resolver);
        this.kubernetesServiceResponseWrapper = kubernetesServiceResponseWrapper;
    }

    @Override
    public boolean supports(HandlerResult result) {
        var parameter = result.getReturnTypeSource();
        return !parameter.hasMethodAnnotation(DisableKubernetesServiceResponse.class);
    }

    @Override
    public Mono<Void> handleResult(ServerWebExchange exchange, HandlerResult result) {
        Object data = result.getReturnValue();
        if (exchange.getResponse().getStatusCode() == HttpStatus.OK) {
            if (data instanceof Mono) {
                var mono = (Mono<?>) data;
                return writeBody(mono.map(this.kubernetesServiceResponseWrapper::wrap), param, exchange);
            } else {
                return writeBody(Mono.just(this.kubernetesServiceResponseWrapper.wrap(data)), param, exchange);
            }
        } else {
            return writeBody(Mono.just(Objects.requireNonNull(data)), param, exchange);
        }
    }
}
