package com.sanlea.opensource.sks.client;

import java.lang.annotation.*;

/**
 * Kubernetes service client annotation
 *
 * @author kut
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface KubernetesServiceClientMock {
    // mock class
    Class<?> value();
}
