package com.sanlea.opensource.sks.provider;

import com.alibaba.fastjson.JSONObject;
import com.sanlea.opensource.sks.exception.KubernetesServiceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * API exception handler
 *
 * @author kut
 */
@RestControllerAdvice
public class KubernetesServiceExceptionHandler {

    @ExceptionHandler(KubernetesServiceException.class)
    public ResponseEntity<JSONObject> handle(KubernetesServiceException e) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var data = new JSONObject();
        data.put("exception", e.getClass().getName());
        data.put("message", e.getMessage());
        return new ResponseEntity<>(data, headers, HttpStatus.I_AM_A_TEAPOT);
    }
}
