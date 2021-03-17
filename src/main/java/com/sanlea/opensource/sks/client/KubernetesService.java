package com.sanlea.opensource.sks.client;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * Kubernetes service client annotation
 *
 * @author kut
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component // pretty for idea IDE
public @interface KubernetesService {
    // service name
    String name();

    // kubernetes namespace
    String namespace() default "default";

    // cluster
    String cluster() default "cluster.local";

    // port
    int port() default 8080;
}
