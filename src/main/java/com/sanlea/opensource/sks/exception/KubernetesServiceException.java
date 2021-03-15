package com.sanlea.opensource.sks.exception;

/**
 * Kubernetes service exception
 *
 * @author kut
 */
public class KubernetesServiceException extends Exception {
    public KubernetesServiceException(String message) {
        super(message);
    }
}
