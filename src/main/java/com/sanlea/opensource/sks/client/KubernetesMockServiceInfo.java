package com.sanlea.opensource.sks.client;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public final class KubernetesMockServiceInfo {
    private KubernetesMockType type;
    private Class<?> mockClass;
    private String mockUrl;
}
