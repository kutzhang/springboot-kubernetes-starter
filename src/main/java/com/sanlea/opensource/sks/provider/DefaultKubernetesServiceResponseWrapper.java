package com.sanlea.opensource.sks.provider;

import java.util.HashMap;
import java.util.Map;

/**
 * Default kubernetes service response wrapper
 *
 * @author kut
 */
public class DefaultKubernetesServiceResponseWrapper implements KubernetesServiceResponseWrapper {
    @Override
    public Object wrap(Object data) {
        Map<String, Object> wrapper = new HashMap<>();
        wrapper.put("data", data);
        return wrapper;
    }
}
