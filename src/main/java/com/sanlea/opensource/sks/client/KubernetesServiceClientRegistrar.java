package com.sanlea.opensource.sks.client;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.HashMap;
import java.util.HashSet;

import static org.springframework.beans.factory.config.AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE;

/**
 * Remote service registrar
 *
 * @author kut
 */
public class KubernetesServiceClientRegistrar
        implements ImportBeanDefinitionRegistrar, BeanClassLoaderAware {
    private ClassLoader beanClassLoader;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata,
                                        BeanDefinitionRegistry registry) {
        var attrs =
                annotationMetadata.getAnnotationAttributes(EnableKubernetesServiceClientSupport.class.getName());
        if (attrs == null) {
            return;
        }

        var servicePackages = (String[]) attrs.get("servicePackages");
        var mockPackages = (String[]) attrs.get("mockPackages");

        var beanScanner = new KubernetesServiceClientScanner();
        var mockScanner = new KubernetesMockServiceScanner();

        var serviceDefinitions = new HashSet<BeanDefinition>();
        var mockMapping = new HashMap<Class<?>, Class<?>>();
        for (var servicePackage : servicePackages) {
            serviceDefinitions.addAll(beanScanner.findCandidateComponents(servicePackage));
        }
        for (var mockPackage : mockPackages) {
            var definitions = mockScanner.findCandidateComponents(mockPackage);
            definitions.forEach(definition -> {
                try {
                    var mockClassName = definition.getBeanClassName();
                    var mockClass = this.beanClassLoader.loadClass(mockClassName);
                    var mockServiceAnnotation =
                            mockClass.getAnnotation(KubernetesMockService.class);
                    mockMapping.put(mockServiceAnnotation.value(), mockClass);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        for (var definition : serviceDefinitions) {
            try {
                var beanClassName = definition.getBeanClassName();
                if (beanClassName == null) {
                    continue;
                }

                var serviceClazz = this.beanClassLoader.loadClass(beanClassName);
                var serviceAnnotation =
                        serviceClazz.getAnnotation(KubernetesService.class);
                Class<?> mockClass = mockMapping.get(serviceClazz);
                if (mockClass == null) {
                    mockClass = void.class;
                }

                var builder = BeanDefinitionBuilder.genericBeanDefinition(
                        KubernetesServiceClientFactoryBean.class
                );
                builder.addConstructorArgValue(serviceAnnotation.name());
                builder.addConstructorArgValue(serviceAnnotation.namespace());
                builder.addConstructorArgValue(serviceAnnotation.cluster());
                builder.addConstructorArgValue(serviceAnnotation.port());
                builder.addConstructorArgValue(serviceClazz);
                builder.addConstructorArgValue(mockClass);
                builder.setAutowireMode(AUTOWIRE_BY_TYPE);
                var beanDefinition = builder.getBeanDefinition();

                registry.registerBeanDefinition(beanClassName, beanDefinition);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.beanClassLoader = classLoader;
    }
}
