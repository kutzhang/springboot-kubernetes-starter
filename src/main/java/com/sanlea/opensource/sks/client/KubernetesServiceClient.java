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
public @interface KubernetesServiceClient {
    // service name
    String name();

    // kubernetes namespace
    String namespace() default "default";

    // cluster
    String cluster() default "cluster.local";

    // port
    int port() default 8080;

    // mock
    Class<?> mockClass() default void.class;
}
