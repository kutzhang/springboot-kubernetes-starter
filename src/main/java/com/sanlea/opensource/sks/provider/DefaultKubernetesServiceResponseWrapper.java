package com.sanlea.opensource.sks.provider;

import java.util.HashMap;

/**
 * Default kubernetes service response wrapper
 *
 * @author kut
 */
public class DefaultKubernetesServiceResponseWrapper implements KubernetesServiceResponseWrapper {
    @Override
    public Object wrap(Object data) {
        var wrapper = new HashMap<String, Object>();
        wrapper.put("data", data);
        return wrapper;
    }
}
