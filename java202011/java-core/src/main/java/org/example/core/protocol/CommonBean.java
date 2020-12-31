package org.example.core.protocol;

import java.io.Serializable;

/**
 * @author tjstj
 * @description TODO
 * @date 2020/11/22 17:28
 */
public class CommonBean implements Serializable {
    private String code;
    private String message;

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
}
