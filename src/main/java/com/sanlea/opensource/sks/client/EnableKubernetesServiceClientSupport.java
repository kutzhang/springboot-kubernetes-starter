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
@Import({KubernetesServiceClientRegistrar.class, KubernetesServiceClientConfiguration.class})
public @interface EnableKubernetesServiceClientSupport {
    // base packages
    String[] basePackages() default {"*"};
}
