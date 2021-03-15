package com.sanlea.opensource.sks.provider;

import com.alibaba.fastjson.JSONObject;

/**
 * Default kubernetes service response wrapper
 *
 * @author kut
 */
public class DefaultKubernetesServiceResponseWrapper implements KubernetesServiceResponseWrapper {
    @Override
    public Object wrap(Object data) {
        var json = new JSONObject();
        json.put("data", data);
        return json;
    }
}
