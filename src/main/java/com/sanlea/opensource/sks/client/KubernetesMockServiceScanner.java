package com.sanlea.opensource.sks.client;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;

/**
 * Kubernetes service component provider
 *
 * @author kut
 */
public class KubernetesMockServiceScanner extends ClassPathScanningCandidateComponentProvider {

    public KubernetesMockServiceScanner() {
        super(false);
        this.addIncludeFilter(new AnnotationTypeFilter(KubernetesMockService.class));
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        AnnotationMetadata metadata = beanDefinition.getMetadata();
        return metadata.hasAnnotation(KubernetesMockService.class.getName());
    }
}
