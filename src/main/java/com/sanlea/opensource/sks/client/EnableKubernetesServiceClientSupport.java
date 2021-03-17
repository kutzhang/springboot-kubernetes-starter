package com.sanlea.opensource.sks.client;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Enable remote service annotation
 *
 * @author kut
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({KubernetesServiceClientConfiguration.class, KubernetesServiceClientRegistrar.class})
public @interface EnableKubernetesServiceClientSupport {
    // service packages
    String[] servicePackages() default {};

    // mock packages
    String[] mockPackages() default {};
}
