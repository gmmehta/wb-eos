package com.devtest.web.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * APIResponse - hold status (OK/ERROR), error message, and data
 */
public class APIResponse {

    private String status;
    private String error;
    private Map<String, Object> data = new HashMap<>();

    public void add(String key, Object value) {
        data.put(key, value);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> dataMap) {
        this.data = dataMap;
    }
}
