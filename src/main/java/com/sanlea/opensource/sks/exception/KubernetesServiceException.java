package com.sanlea.opensource.sks.exception;

/**
 * Kubernetes service exception
 *
 * @author kut
 */
public class KubernetesServiceException extends Exception {
    private static final long serialVersionUID = -1837142784166177097L;

    public KubernetesServiceException(String message) {
        super(message);
    }
}
