package com.sanlea.opensource.sks.client;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Set;

import static org.springframework.beans.factory.config.AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE;

/**
 * Remote service registrar
 *
 * @author kut
 */
public class KubernetesServiceClientRegistrar implements ImportBeanDefinitionRegistrar, BeanClassLoaderAware {
    private ClassLoader beanClassLoader;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata,
                                        BeanDefinitionRegistry registry) {
        var attrs =
                annotationMetadata.getAnnotationAttributes(EnableKubernetesServiceClientSupport.class.getName());
        if (attrs == null) {
            return;
        }

        var basePackages = (String[]) attrs.get("basePackages");
        var beanScanner = new KubernetesServiceClientScanner();

        for (var basePackage : basePackages) {
            Set<BeanDefinition> serviceMetas = beanScanner.findCandidateComponents(basePackage);
            for (BeanDefinition meta : serviceMetas) {
                try {
                    var beanClassName = meta.getBeanClassName();
                    if (beanClassName == null) {
                        continue;
                    }

                    var clazz = this.beanClassLoader.loadClass(beanClassName);
                    var annotation = clazz.getAnnotation(KubernetesServiceClient.class);

                    var builder =
                            BeanDefinitionBuilder.genericBeanDefinition(KubernetesServiceClientFactoryBean.class);
                    builder.addConstructorArgValue(annotation.name());
                    builder.addConstructorArgValue(annotation.namespace());
                    builder.addConstructorArgValue(annotation.cluster());
                    builder.addConstructorArgValue(annotation.port());
                    builder.addConstructorArgValue(clazz);
                    builder.setAutowireMode(AUTOWIRE_BY_TYPE);
                    var beanDefinition = builder.getBeanDefinition();

                    registry.registerBeanDefinition(beanClassName, beanDefinition);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.beanClassLoader = classLoader;
    }
}
