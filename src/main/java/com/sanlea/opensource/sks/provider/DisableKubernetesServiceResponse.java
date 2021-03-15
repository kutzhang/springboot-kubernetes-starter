package com.sanlea.opensource.sks.provider;

import java.lang.annotation.*;

/**
 * disable service response
 *
 * @author kut
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DisableKubernetesServiceResponse {
}
