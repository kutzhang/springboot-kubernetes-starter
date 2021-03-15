package com.sanlea.opensource.sks.client.protocol;

import com.alibaba.fastjson.JSON;
import com.sanlea.opensource.sks.exception.KubernetesServiceException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.BeanClassLoaderAware;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.nio.charset.StandardCharsets;

/**
 * Remote service error decoder
 *
 * @author kut
 */
public class DefaultKubernetesServiceClientErrorDecoder implements KubernetesServiceErrorDecoder, BeanClassLoaderAware {

    // default decoder
    private final ErrorDecoder.Default defaultDecoder = new ErrorDecoder.Default();
    private ClassLoader beanClassLoader;

    @Override
    @SuppressWarnings("unchecked")
    public Exception decode(String methodKey, Response response) {
        // only 418 status is right
        if (response.status() != 418) {
            return defaultDecoder.decode(methodKey, response);
        }

        var body = response.body();
        if (body == null) {
            return defaultDecoder.decode(methodKey, response);
        }

        Exception result;
        try (InputStream inputStream = body.asInputStream()) {
            var jsonString = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            var json = JSON.parseObject(jsonString);
            var exceptionClassName = json.getString("exception");
            var exceptionMessage = json.getString("message");
            var exceptionClass = (Class<? extends Exception>) beanClassLoader.loadClass(exceptionClassName);

            Constructor<? extends Exception> constructor;
            try {
                constructor = exceptionClass.getConstructor(String.class);
                result = constructor.newInstance(exceptionMessage);
            } catch (NoSuchMethodException e) {
                constructor = exceptionClass.getConstructor();
                result = constructor.newInstance();
            }
        } catch (Exception e) {
            result = new KubernetesServiceException(e.getMessage());
        }
        return result;
    }

    @Override
    public void setBeanClassLoader(ClassLoader beanClassLoader) {
        this.beanClassLoader = beanClassLoader;
    }
}
