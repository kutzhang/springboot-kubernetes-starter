package com.sanlea.opensource.sks.client;

import com.alibaba.fastjson.JSON;
import feign.FeignException;
import feign.Response;
import feign.codec.Decoder;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

import static feign.Util.ensureClosed;

/**
 * Remote service response decoder
 *
 * @author kut
 */
public class DefaultKubernetesServiceClientResponseDecoder implements Decoder {
    @Override
    public Object decode(Response response, Type type) throws IOException, FeignException {
        var body = response.body();
        if (body == null) {
            return null;
        }

        var inputStream = response.body().asInputStream();
        var jsonString = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        var json = JSON.parseObject(jsonString);
        ensureClosed(inputStream);

        return json.getObject("data", type);
    }
}
