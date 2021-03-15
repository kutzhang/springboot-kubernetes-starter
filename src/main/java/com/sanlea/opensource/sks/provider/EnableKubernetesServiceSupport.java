package com.sanlea.opensource.sks.provider;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(KubernetesServiceConfiguration.class)
public @interface EnableKubernetesServiceSupport {
}
