package com.sanlea.opensource.sks.client;

import com.sanlea.opensource.sks.constant.KubernetesServiceClientMode;
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
        var mode = (KubernetesServiceClientMode) attrs.get("mode");
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
                    var clientAnnotation =
                            clazz.getAnnotation(KubernetesServiceClient.class);
                    var mockAnnotation =
                            clazz.getAnnotation(KubernetesServiceClientMock.class);
                    Class<?> mockClass = mockAnnotation == null ? void.class : mockAnnotation.value();

                    var builder = BeanDefinitionBuilder.genericBeanDefinition(
                            KubernetesServiceClientFactoryBean.class
                    );
                    builder.addConstructorArgValue(clientAnnotation.name());
                    builder.addConstructorArgValue(clientAnnotation.namespace());
                    builder.addConstructorArgValue(clientAnnotation.cluster());
                    builder.addConstructorArgValue(clientAnnotation.port());
                    builder.addConstructorArgValue(clazz);
                    builder.addConstructorArgValue(mode);
                    builder.addConstructorArgValue(mockClass);
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
