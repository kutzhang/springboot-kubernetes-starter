package com.sanlea.opensource.sks.client;

import com.sanlea.opensource.sks.constant.KubernetesServiceClientMode;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

import static com.sanlea.opensource.sks.constant.KubernetesServiceClientMode.STANDARD;

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
    // base packages
    String[] basePackages() default {"*"};

    // mode
    KubernetesServiceClientMode mode() default STANDARD;
}
