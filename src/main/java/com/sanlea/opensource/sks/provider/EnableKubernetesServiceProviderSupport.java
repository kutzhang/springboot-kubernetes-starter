package com.sanlea.opensource.sks.provider;

import com.sanlea.opensource.sks.provider.config.KubernetesServiceCommonConfiguration;
import com.sanlea.opensource.sks.provider.config.KubernetesServiceServletConfiguration;
import com.sanlea.opensource.sks.provider.config.KubernetesServiceWebfluxConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({KubernetesServiceCommonConfiguration.class,
        KubernetesServiceServletConfiguration.class,
        KubernetesServiceWebfluxConfiguration.class})
public @interface EnableKubernetesServiceProviderSupport {
}
