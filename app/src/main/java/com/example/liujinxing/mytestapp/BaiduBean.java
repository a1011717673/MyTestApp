package com.example.liujinxing.mytestapp;

import java.io.Serializable;

public class BaiduBean implements Serializable {
    private String ptotocol;
    private String code;
    private String message;
    private String url;

    public String getPtotocol() {
        return ptotocol;
    }

    public void setPtotocol(String ptotocol) {
        this.ptotocol = ptotocol;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
