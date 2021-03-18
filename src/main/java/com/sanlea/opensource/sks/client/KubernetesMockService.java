package com.sanlea.opensource.sks.client;

import java.lang.annotation.*;

import static com.sanlea.opensource.sks.client.KubernetesMockType.MOCK_CLASS;

/**
 * Kubernetes service client annotation
 *
 * @author kut
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface KubernetesMockService {
    // target service class
    Class<?> targetServiceClass();

    // type
    KubernetesMockType type() default MOCK_CLASS;

    // mock url
    String mockUrl() default "";
}
